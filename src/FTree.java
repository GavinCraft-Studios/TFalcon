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
            // TODO - Load File
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
