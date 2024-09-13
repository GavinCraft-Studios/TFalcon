import FAssets.FScrollPane;
import FAssets.FTextArea;

import javax.swing.*;
import java.awt.event.WindowEvent;
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
        FTextArea textArea = new FTextArea();
        FScrollPane scrollPane = new FScrollPane(textArea, true);

        addTab("Untitled", scrollPane);
        files.add(null);
        setSelectedComponent(scrollPane);
    }

    public void addTTab(File file)
    {
        FTextArea textArea = new FTextArea();
        FScrollPane scrollPane = new FScrollPane(textArea, true);

        addTab(file.getName(), scrollPane);
        files.add(file);
        setSelectedComponent(scrollPane);
    }

    public void closeTTab() {
        files.remove(getSelectedIndex());
        removeTabAt(getSelectedIndex());

        if (getTabCount() <= 0) {
            Editor.instance.CloseEditor();
        }
    }

    public void refileTTab(File location) {
        int selectedIndex = getSelectedIndex();
        files.remove(selectedIndex);
        files.add(selectedIndex, location);

        if (files.indexOf(location) == selectedIndex) {
            System.out.println("Refile of TTab " + selectedIndex + " was successful.");
        }
        else {
            System.out.println("Refile of TTab " + selectedIndex + " was unsuccessful.");
        }

        setTitleAt(selectedIndex, location.getName());
    }

    public void closeTTab(int index)
    {
        files.remove(index);
        removeTabAt(index);

        if (getTabCount() <= 0) {
            Editor.instance.CloseEditor();
        }
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

    public File getCurrentFile()
    {
        return files.get(getSelectedIndex());
    }
}
