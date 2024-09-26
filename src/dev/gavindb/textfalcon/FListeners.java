package dev.gavindb.textfalcon;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;

public class FListeners {
    // All Listeners are stored here for FAssets like the FMenuBar

    // FMenuBar (File)
    static class NewFileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Editor.inst.tabPane.addTTab();
        }
    }

    static class OpenFileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            File selectedFile = Editor.inst.fileManager.chooseFile();

            if (selectedFile != null)
            {
                if (!Editor.inst.tabPane.checkHasFile())
                {
                    // Refile Current Tab
                    Editor.inst.tabPane.refileTTab(selectedFile);
                    Editor.inst.fileManager.openFile(selectedFile);
                }
                else
                {
                    // Make Tab For File
                    Editor.inst.tabPane.addTTab(selectedFile);
                    Editor.inst.fileManager.openFile(selectedFile);
                }
            }
        }
    }

    static class SaveFileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Editor.inst.tabPane.checkHasFile() == true)
            {
                Editor.inst.fileManager.saveFile(Editor.inst.tabPane.getCurrentFile());
            }
            else
            {
                File location = Editor.inst.fileManager.chooseFile();
                Editor.inst.fileManager.saveFile(location);
                Editor.inst.tabPane.refileTTab(location);
            }
        }
    }

    static class SaveAsFileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            File location = Editor.inst.fileManager.chooseFile();

            if (Editor.inst.tabPane.checkHasFile())
            {
                Editor.inst.fileManager.saveFile(location);
                Editor.inst.tabPane.addTTab(location);
                Editor.inst.fileManager.openFile(location);
            }
            else
            {
                Editor.inst.fileManager.saveFile(location);
                Editor.inst.tabPane.refileTTab(location);
                Editor.inst.fileManager.openFile(location);
            }
        }
    }

    static class SaveAllFilesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<File> files = Editor.inst.tabPane.getAllFiles();
            for (File file : files)
            {
                int index = files.indexOf(file);
                Editor.inst.fileManager.saveFile(file, index);
            }
        }
    }

    static class OpenFolderListener implements ActionListener {
        private JCheckBoxMenuItem folderView;
        public OpenFolderListener(JCheckBoxMenuItem folderView) {
            this.folderView = folderView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            File selectedFolder = Editor.inst.fileManager.chooseFolder();
            Editor.inst.fileManager.populateDirectoryTree(selectedFolder);
            folderView.setState(true);
            Editor.inst.revalidate();
            Editor.inst.repaint();
        }
    }

    static class FolderViewListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Editor.inst.fileTreePane.setVisible(true);
                Editor.inst.revalidate();
                Editor.inst.repaint();
            }
            else {
                Editor.inst.fileTreePane.setVisible(false);
                Editor.inst.revalidate();
                Editor.inst.repaint();
            }
        }
    }

    static class CloseFileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Editor.inst.tabPane.closeTTab();
        }
    }

    // FMenuBar (Edit)
    static class CopyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            FAssets.FScrollPane scrollPane = (FAssets.FScrollPane) Editor.inst.tabPane.getSelectedComponent();
            FAssets.FTextArea textArea = scrollPane.getFTextArea();
            String selectedText = textArea.getSelectedText();

            if (selectedText != null) {
                StringSelection stringSelection = new StringSelection(selectedText);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            }
        }
    }

    static class PasteListener implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            FAssets.FScrollPane scrollPane = (FAssets.FScrollPane) Editor.inst.tabPane.getSelectedComponent();
            FAssets.FTextArea textArea = scrollPane.getFTextArea();

            try {
                // Get the clipboard contents
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable transferable = clipboard.getContents(null);

                if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor))
                {
                    // Get the string data from the clipboard
                    String pastedText = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                    // Insert the pasted text into the JTextArea at the current cursor position
                    textArea.insert(pastedText, textArea.getCaretPosition());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
