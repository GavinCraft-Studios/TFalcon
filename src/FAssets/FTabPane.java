package FAssets;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class FTabPane extends JTabbedPane
{
    public ArrayList<File> files;

    public FTabPane()
    {
        files = new ArrayList<>();
    }

    public void addTTab()
    {
        addTab("Untitled", new FTextArea());
        files.add(null);
    }

    public void addTTab(File file)
    {
        // TODO - Remove Untitled Tab

        FTextArea textArea = new FTextArea();
        addTab(file.getName(), textArea);
        files.add(file);
        setSelectedComponent(textArea);
    }

    public void closeTTab()
    {
        removeTabAt(getSelectedIndex());
        files.remove(getSelectedIndex());
    }

    public void closeTTab(int index)
    {
        removeTabAt(index);
        files.remove(index);
    }

    public boolean checkHasFile()
    {
        if (files.get(getSelectedIndex()) != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
