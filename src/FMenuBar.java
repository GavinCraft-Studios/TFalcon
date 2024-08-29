import FAssets.FTabPane;
import FAssets.FTextArea;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FMenuBar extends JMenuBar
{
    public FTabPane tabPane;

    public FMenuBar(FTabPane tabPane)
    {
        this.tabPane = tabPane;

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
        JCheckBoxMenuItem autoSave = new JCheckBoxMenuItem("Auto Save");
        file.add(autoSave);
        file.addSeparator();
        JMenuItem closeFile = file.add("Close");
        closeFile.addActionListener(new CloseFileListener());
        add(file);
    }

    class NewFileListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            tabPane.addTTab();
        }
    }

    class OpenFileListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            File selectedFile = FileManager.instance.chooseFile();

            if (selectedFile != null)
            {
                // Make Tab For File
                tabPane.addTTab(selectedFile);
                FileManager.instance.openFile(selectedFile);
            }
        }
    }

    class SaveFileListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (tabPane.checkHasFile() == true)
            {
                FileManager.instance.saveFile(tabPane.getCurrentFile());
            }
            else
            {
                File location = FileManager.instance.chooseFile();
                FileManager.instance.saveFile(location);
            }
        }
    }

    class SaveAsFileListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            File location = FileManager.instance.chooseFile();
            FileManager.instance.saveFile(location);
            tabPane.addTTab(location);
            FileManager.instance.openFile(location);
        }
    }

    class CloseFileListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            tabPane.closeTTab();
        }
    }
}
