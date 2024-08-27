package FAssets;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FMenuBar extends JMenuBar
{
    public FTabPane tabPane;

    public FMenuBar(FTabPane tabPane)
    {
        this.tabPane = tabPane;

        JMenu file = new JMenu("File");

        JMenuItem newFile = file.add("New");
        newFile.addActionListener(new NewFileListener());
        JMenuItem open = file.add("Open");
        file.addSeparator();
        JMenuItem save = file.add("Save");
        JMenuItem saveAs = file.add("Save As");
        JCheckBoxMenuItem autoSave = new JCheckBoxMenuItem("Auto Save");
        file.add(autoSave);

        add(file);
    }

    class NewFileListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            tabPane.addTTab();
        }
    }
}
