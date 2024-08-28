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
        JMenuItem save = file.add("Save");
        JMenuItem saveAs = file.add("Save As");
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
                FTextArea textArea = (FTextArea) tabPane.getSelectedComponent();

                // Open File Contents
                try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        textArea.append(line + "\n");
                    }
                } catch (IOException IOexception) {
                    IOexception.printStackTrace();
                }
            }
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
