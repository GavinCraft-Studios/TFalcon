import FAssets.FFileChooser;
import FAssets.FMutableTreeNode;
import FAssets.FScrollPane;
import FAssets.FTextArea;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.*;

public class FileManager
{
    public FFileChooser fileChooser;

    public File selectedFolder;
    public File lastSelectedDirectory;

    public FileManager()
    {
        fileChooser = new FFileChooser();
    }

    public File chooseFile()
    {
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        setFileChooserDirectory();

        int result = fileChooser.showOpenDialog(null);

        if (result == FFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();

            lastSelectedDirectory = selectedFile;
            return selectedFile;
        }
        else
        {
            return null;
        }
    }

    public File chooseFolder()
    {
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        setFileChooserDirectory();

        int result = fileChooser.showOpenDialog(null);

        if (result == FFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();

            lastSelectedDirectory = selectedFile;
            selectedFolder = selectedFile;
            return selectedFile;
        }
        else
        {
            return null;
        }
    }

    public void openFile(File file)
    {
        FScrollPane scrollPane = (FScrollPane) Editor.tabPane.getSelectedComponent();
        FTextArea textArea = scrollPane.getFTextArea();

        // Open File Contents
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                textArea.append(line + "\n");
            }
        } catch (IOException IOexception) {
            JOptionPane.showMessageDialog(null, "There was an error while saving the file." + "/n" + IOexception);
            IOexception.printStackTrace();
        }
    }

    public boolean saveFile(File location)
    {
        // Return true if save is completed

        JTextArea textArea = (JTextArea) Editor.instance.tabPane.getSelectedComponent();
        String text = textArea.getText();
        try {
            // Create a FileWriter to write to the file
            FileWriter writer = new FileWriter(location);
            // Create a BufferedWriter to write to the file more efficiently
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            // Write the text to the file
            bufferedWriter.write(text);

            // Close the writer and buffered writer
            bufferedWriter.close();
            writer.close();

            System.out.println("Text saved successfully to " + location.getAbsolutePath());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving text to file.");
            return false;
        }
    }

    public void populateDirectoryTree(File directory)
    {
        FMutableTreeNode rootNode = new FMutableTreeNode(directory);
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
        Editor.fileTree.setModel(treeModel);

        // Recursively add subdirectories
        addSubdirectories(rootNode, directory);
    }

    private void addSubdirectories(FMutableTreeNode node, File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    FMutableTreeNode childNode = new FMutableTreeNode(file);

                    node.add(childNode);
                    addSubdirectories(childNode, file);
                } else {
                    // If the file is not a directory, add it as a leaf node
                    FMutableTreeNode fileNode = new FMutableTreeNode(file);
                    node.add(fileNode);
                }
            }
        }
    }

    public void setFileChooserDirectory()
    {
        if (selectedFolder != null) {
            fileChooser.setCurrentDirectory(selectedFolder);
        } else if (lastSelectedDirectory != null) {
            fileChooser.setCurrentDirectory(lastSelectedDirectory);
        }
    }
}
