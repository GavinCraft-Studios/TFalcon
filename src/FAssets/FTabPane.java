package FAssets;

import javax.swing.*;

public class FTabPane extends JTabbedPane
{
    public void addTTab()
    {
        addTab("Untitled", new FTextArea());
    }

    public void closeTTab()
    {
        removeTabAt(getSelectedIndex());
    }
}
