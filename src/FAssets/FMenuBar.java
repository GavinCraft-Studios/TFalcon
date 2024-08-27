package FAssets;

import javax.swing.*;

public class FMenuBar extends JMenuBar
{
    public FMenuBar()
    {
        JMenu file = new JMenu("File");
        JMenuItem newFile = file.add("New");
        JMenuItem open = file.add("Open");
        file.addSeparator();
        JMenuItem save = file.add("Save");
        JMenuItem saveAs = file.add("Save As");
        JCheckBoxMenuItem autoSave = new JCheckBoxMenuItem("Auto Save");
        file.add(autoSave);

        add(file);
    }
}
