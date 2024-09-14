import FAssets.FMutableTreeNode;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

public class FTree extends JTree {

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
                    if (!Editor.tabPane.checkHasFile()) {
                        // Refile Current Tab
                        Editor.tabPane.refileTTab(selectedFile);
                        Editor.fileManager.openFile(selectedFile);
                    } else {
                        // Make Tab For File
                        Editor.tabPane.addTTab(selectedFile);
                        Editor.fileManager.openFile(selectedFile);
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
            Editor.instance.revalidate();
            Editor.instance.repaint();
        }

        @Override
        public void treeCollapsed(TreeExpansionEvent event) {
            // Refactor and Repaint Editor
            Editor.instance.revalidate();
            Editor.instance.repaint();
        }
    }
}
