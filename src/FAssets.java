import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class FAssets {
    // This class contains every FAsset (Falcon Asset) class which are extended from the original components

    public static class FMenuBar extends JMenuBar {
        private JCheckBoxMenuItem folderView;

        private JMenu fileMenu;
        private JMenu editMenu;
        // TODO - Create View Menu

        public FMenuBar() {
            fileMenu = setupFileMenu();
            add(fileMenu);
            editMenu = setupEditMenu();
            add(editMenu);
        }

        private JMenu setupFileMenu() {
            JMenu file = new JMenu("File");

            JMenuItem newFile = file.add("New");
            newFile.addActionListener(new FListeners.NewFileListener());
            JMenuItem openFile = file.add("Open");
            openFile.addActionListener(new FListeners.OpenFileListener());
            file.addSeparator();
            JMenuItem saveFile = file.add("Save");
            saveFile.addActionListener(new FListeners.SaveFileListener());
            JMenuItem saveFileAs = file.add("Save As");
            saveFileAs.addActionListener(new FListeners.SaveAsFileListener());
            JMenuItem saveAllFiles = file.add("Save All");
            saveAllFiles.addActionListener(new FListeners.SaveAllFilesListener());
            //JCheckBoxMenuItem autoSave = new JCheckBoxMenuItem("Auto Save"); TODO <-------------
            //file.add(autoSave);
            file.addSeparator();
            file.add(setupProjectMenu());
            file.addSeparator();
            JMenuItem closeFile = file.add("Close");
            closeFile.addActionListener(new FListeners.CloseFileListener());

            return file;
        }

        private JMenu setupProjectMenu() {
            JMenu project = new JMenu("Project");

            // TODO - Make menu for creating projects
            // TODO - Make item for opening projects
            // TODO - Make item for saving projects
            //project.addSeparator();
            JMenuItem openFolder = project.add("Open Folder");
            folderView = new JCheckBoxMenuItem("View Folder");
            folderView.addItemListener(new FListeners.FolderViewListener());
            openFolder.addActionListener(new FListeners.OpenFolderListener(folderView));
            project.add(folderView);

            return project;
        }

        private JMenu setupEditMenu() {
            JMenu edit = new JMenu("Edit");

            JMenuItem copy = edit.add("Copy");
            copy.addActionListener(new FListeners.CopyListener());
            JMenuItem paste = edit.add("Paste");
            paste.addActionListener(new FListeners.PasteListener());

            return edit;
        }
    }




    public static class FTabPane extends JTabbedPane
    {
        public ArrayList<File> files;

        public FTabPane() {
            files = new ArrayList<>();
        }

        public void addTTab() {
            FTextArea textArea = new FTextArea();
            FScrollPane scrollPane = new FScrollPane(textArea, true);

            addTab("Untitled", scrollPane);
            files.add(null);
            setSelectedComponent(scrollPane);
        }

        public void addTTab(File file) {
            FTextArea textArea = new FTextArea();
            FScrollPane scrollPane = new FScrollPane(textArea, true);

            addTab(file.getName(), scrollPane);
            files.add(file);
            setSelectedComponent(scrollPane);
        }

        public void closeTTab() {
            files.remove(getSelectedIndex());
            removeTabAt(getSelectedIndex());

            if (getTabCount() <= 0) {
                Editor.inst.CloseEditor();
            }
        }

        public void refileTTab(File location) {
            int selectedIndex = getSelectedIndex();
            files.remove(selectedIndex);
            files.add(selectedIndex, location);

            if (files.indexOf(location) == selectedIndex) {
                System.out.println("Refile of TTab " + selectedIndex + " was successful.");
            }
            else {
                System.out.println("Refile of TTab " + selectedIndex + " was unsuccessful.");
            }

            setTitleAt(selectedIndex, location.getName());
        }

        public void closeTTab(int index) {
            files.remove(index);
            removeTabAt(index);

            if (getTabCount() <= 0) {
                Editor.inst.CloseEditor();
            }
        }

        public boolean checkHasFile() {
            if (files.get(getSelectedIndex()) != null)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        public File getCurrentFile() {
            return files.get(getSelectedIndex());
        }

        public ArrayList<File> getAllFiles() {
            return files;
        }
    }



    public static class FTree extends JTree {

        public FTree() {
            addTreeSelectionListener(new treeSelectionListener());
            addTreeExpansionListener(new treeExpansionListener());
        }

        class treeSelectionListener implements TreeSelectionListener {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                FMutableTreeNode selectedNode = (FMutableTreeNode) e.getPath().getLastPathComponent();
                File selectedFile = selectedNode.file;

                if (selectedFile instanceof File && selectedFile.isFile()) {

                    // Open Selected File
                    if (selectedFile != null) {
                        if (!Editor.inst.tabPane.checkHasFile()) {
                            // Refile Current Tab
                            Editor.inst.tabPane.refileTTab(selectedFile);
                            Editor.inst.fileManager.openFile(selectedFile);
                        } else {
                            // Make Tab For File
                            Editor.inst.tabPane.addTTab(selectedFile);
                            Editor.inst.fileManager.openFile(selectedFile);
                        }
                    }

                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                } else {
                    System.out.println("Selected node's user object is not a File.");
                }
            }
        }

        // Resize and Repaint the editor when folder opened or closed
        class treeExpansionListener implements TreeExpansionListener {
            @Override
            public void treeExpanded(TreeExpansionEvent event) {
                // Refactor and Repaint Editor
                Editor.inst.revalidate();
                Editor.inst.repaint();
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
                // Refactor and Repaint Editor
                Editor.inst.revalidate();
                Editor.inst.repaint();
            }
        }
    }



    public static class FTreeCellRenderer extends DefaultTreeCellRenderer {
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus)
        {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

            // Check if the user object is a File instance
            if (value instanceof File) {
                File file = (File) value; // Safe cast after checking the type
                String fileName = file.getName();

                // Find the file extension
                int lastDotIndex = fileName.lastIndexOf('.');
                String fileExtension = (lastDotIndex != -1) ? fileName.substring(lastDotIndex + 1) : "";

                // Append the file extension to the displayed text
                setText(fileName + " (" + fileExtension + ")");
            } else {
                // Handle non-File objects (optional: display a different text)
                setText(value.toString());
            }

            return this;
        }
    }



    public static class FScrollPane extends JScrollPane {
        public FScrollPane(Component component, boolean useHorizontal) {
            super(component);
            setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            if (useHorizontal)
            {
                setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            } else {
                setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            }
        }

        public FTextArea getFTextArea() {
            FTextArea textArea = (FTextArea) super.getViewport().getView();
            return textArea;
        }
    }



    public static class FMutableTreeNode extends DefaultMutableTreeNode {

        public File file;

        public FMutableTreeNode(File file) {
            super(file.getName());
            this.file = file;
        }
    }



    public static class FTextArea extends JTextArea
    {
        public FTextArea() {
            super();

            // Configure the Text Area:
            setTabSize(2); // default = 8
        }
    }


    public static class FFileChooser extends JFileChooser {

    }
}