import FAssets.FMenuBar;
import FAssets.FTextArea;
import FListeners.FDocumentListener;

import javax.swing.*;
import java.awt.*;

public class Editor extends JFrame
{
    public FMenuBar fmenuBar;

    public FTextArea textArea;
    public FDocumentListener documentListener;

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
        fmenuBar = new FMenuBar();
        setJMenuBar(fmenuBar);

        textArea = new FTextArea();
        container.add(textArea,BorderLayout.CENTER);

        // Make the Window Visible
        setVisible(true);
    }
}
