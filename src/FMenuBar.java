import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FMenuBar extends JMenuBar
{
    public FMenuBar()
    {
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
        //JCheckBoxMenuItem autoSave = new JCheckBoxMenuItem("Auto Save"); TODO <-------------
        //file.add(autoSave);
        file.addSeparator();
        JMenuItem closeFile = file.add("Close");
        closeFile.addActionListener(new CloseFileListener());
        add(file);
    }

    class NewFileListener implements ActionListener
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
                // Make Tab For File
                Editor.tabPane.addTTab(selectedFile);
                Editor.fileManager.openFile(selectedFile);
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
            Editor.fileManager.saveFile(location);
            Editor.tabPane.addTTab(location);
            Editor.fileManager.openFile(location);
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
