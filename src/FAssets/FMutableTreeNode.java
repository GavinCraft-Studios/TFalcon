package FAssets;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

public class FMutableTreeNode extends DefaultMutableTreeNode {

    public File file;

    public FMutableTreeNode(File file)
    {
        super(file.getName());
        this.file = file;
    }
}
