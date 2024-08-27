import FAssets.FTextArea;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Editor extends JFrame
{
    public FTextArea textArea;

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
        textArea = new FTextArea();
        container.add(textArea,BorderLayout.CENTER);

        // Make the Window Visible
        setVisible(true);
    }
}
