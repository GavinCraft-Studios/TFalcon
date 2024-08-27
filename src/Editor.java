import FAssets.FMenuBar;
import FAssets.FTabPane;
import FAssets.FTextArea;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Editor extends JFrame
{
    public FMenuBar fmenuBar;

    public FTabPane tabPane;
    public ArrayList<FTextArea> textAreas;

    public Editor()
    {
        // Application Setup
        setTitle("Text Falcon");

        Toolkit toolkit = getToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setBounds(screenSize.width/4,screenSize.height/4,  // Position
                screenSize.width/2,screenSize.height/2);  // Size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout & Container
        BorderLayout layout = new BorderLayout(); // North, East, West, South, and Center Grid
        Container container = getContentPane();
        container.setLayout(layout);

        // Construction
        tabPane = new FTabPane();
        tabPane.addTTab();
        container.add(tabPane, BorderLayout.CENTER);

        fmenuBar = new FMenuBar(tabPane);
        setJMenuBar(fmenuBar);

        // Make the Window Visible
        setVisible(true);
    }
}
