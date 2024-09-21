import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;

public class FMenuBar extends JMenuBar
{
    private JCheckBoxMenuItem folderView;

    public FMenuBar()
    {
        JMenu file = setupFileMenu();
        add(file);
        // TODO - Create Edit Menu
    }

    /* ----------------------- Menu Creation Methods ----------------------- */
    private JMenu setupFileMenu() {
        JMenu file = new JMenu("File");

        JMenuItem newFile = file.add("New");
        newFile.addActionListener(new NewFileListener());
        JMenuItem openFile = file.add("Open");
        openFile.addActionListener(new OpenFileListener());
        file.addSeparator();
        JMenuItem saveFile = file.add("Save");
        saveFile.addActionListener(new SaveFileListener());
        JMenuItem saveFileAs = file.add("Save As");
        saveFileAs.addActionListener(new SaveAsFileListener());
        JMenuItem saveAllFiles = file.add("Save All");
        saveAllFiles.addActionListener(new SaveAllFilesListener());
        //JCheckBoxMenuItem autoSave = new JCheckBoxMenuItem("Auto Save"); TODO <-------------
        //file.add(autoSave);
        file.addSeparator();
        file.add(setupProjectMenu());
        file.addSeparator();
        JMenuItem closeFile = file.add("Close");
        closeFile.addActionListener(new CloseFileListener());
        return file;
    }

    private JMenu setupProjectMenu() {
        JMenu project = new JMenu("Project");

        // TODO - Make menu for creating projects
        // TODO - Make item for opening projects
        // TODO - Make item for saving projects
        project.addSeparator();
        JMenuItem openFolder = project.add("Open Folder");
        openFolder.addActionListener(new OpenFolderListener());
        folderView = new JCheckBoxMenuItem("View Folder");
        folderView.addItemListener(new FolderViewListener());
        project.add(folderView);
        return project;
    }

    /* --------------------- Action and Item Listeners --------------------- */
    class NewFileListener implements ActionListener // File Menu Listeners
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            Editor.tabPane.addTTab();
        }
    }

    class OpenFileListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            File selectedFile = Editor.fileManager.chooseFile();

            if (selectedFile != null)
            {
                if (!Editor.tabPane.checkHasFile())
                {
                    // Refile Current Tab
                    Editor.tabPane.refileTTab(selectedFile);
                    Editor.fileManager.openFile(selectedFile);
                }
                else
                {
                    // Make Tab For File
                    Editor.tabPane.addTTab(selectedFile);
                    Editor.fileManager.openFile(selectedFile);
                }
            }
        }
    }

    class SaveFileListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Editor.tabPane.checkHasFile() == true)
            {
                Editor.fileManager.saveFile(Editor.tabPane.getCurrentFile());
            }
            else
            {
                File location = Editor.fileManager.chooseFile();
                Editor.fileManager.saveFile(location);
                Editor.tabPane.refileTTab(location);
            }
        }
    }

    class SaveAsFileListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            File location = Editor.fileManager.chooseFile();

            if (Editor.tabPane.checkHasFile())
            {
                Editor.fileManager.saveFile(location);
                Editor.tabPane.addTTab(location);
                Editor.fileManager.openFile(location);
            }
            else
            {
                Editor.fileManager.saveFile(location);
                Editor.tabPane.refileTTab(location);
                Editor.fileManager.openFile(location);
            }
        }
    }

    class SaveAllFilesListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<File> files = Editor.tabPane.getAllFiles();
            for (File file : files)
            {
                // TODO - Save ALl Files
                int index = files.indexOf(file);
                Editor.fileManager.saveFile(file, index);
            }
        }
    }

    class OpenFolderListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            File selectedFolder = Editor.fileManager.chooseFolder();
            Editor.fileManager.populateDirectoryTree(selectedFolder);
            folderView.setState(true);
            Editor.instance.revalidate();
            Editor.instance.repaint();
        }
    }

    class FolderViewListener implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Editor.fileTreePane.setVisible(true);
                Editor.instance.revalidate();
                Editor.instance.repaint();
            }
            else {
                Editor.fileTreePane.setVisible(false);
                Editor.instance.revalidate();
                Editor.instance.repaint();
            }
        }
    }

    class CloseFileListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            Editor.tabPane.closeTTab();
        }
    }
}
