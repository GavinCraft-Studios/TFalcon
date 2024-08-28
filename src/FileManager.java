import FAssets.FFileChooser;

import java.io.File;

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
}
