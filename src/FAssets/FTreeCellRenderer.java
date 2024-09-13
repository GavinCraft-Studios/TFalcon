package FAssets;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.io.File;

public class FTreeCellRenderer extends DefaultTreeCellRenderer {
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
