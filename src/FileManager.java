import FAssets.FFileChooser;
import FAssets.FTextArea;

import javax.swing.*;
import java.io.*;

public class FileManager
{
    public static FileManager instance;

    public FFileChooser fileChooser;

    public File chooseFile()
    {
        fileChooser = new FFileChooser();

        int result = fileChooser.showOpenDialog(null);

        if (result == FFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile;
        }
        else
        {
            return null;
        }
    }

    public void openFile(File file)
    {
        FTextArea textArea = (FTextArea) Editor.instance.tabPane.getSelectedComponent();

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
}
