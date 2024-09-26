package dev.gavindb.textfalcon;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Editor extends JFrame
{
    public static Editor inst; // instance

    public FAssets.FTabPane tabPane;
    public FileManager fileManager;
    public FAssets.FTree fileTree;
    public FAssets.FScrollPane fileTreePane;
    public FAssets.FMenuBar fmenuBar;

    public static void main(String[] args) {
        inst = new Editor();
    }

    public Editor() {
        fileManager = new FileManager();

        // Application Setup
        setTitle("Text Falcon");
        URL iconURL = getClass().getResource("TFalcon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());

        Toolkit toolkit = getToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setBounds(screenSize.width/4,screenSize.height/4,  // Position
                screenSize.width/2,screenSize.height/2);  // Size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout & Container
        BorderLayout layout = new BorderLayout(); // North, East, West, South, and Center Grid
        Container container = getContentPane();
        container.setLayout(layout);

        // Construction - Tab Pane
        tabPane = new FAssets.FTabPane();
        tabPane.addTTab();
        container.add(tabPane, BorderLayout.CENTER);

        // Construction - File Tree
        fileTree = new FAssets.FTree();
        fileTree.setCellRenderer(new FAssets.FTreeCellRenderer());
        fileTreePane = new FAssets.FScrollPane(fileTree, false);
        fileTreePane.setVisible(false);
        container.add(fileTreePane, BorderLayout.WEST);

        // Construction - Menu Bar
        fmenuBar = new FAssets.FMenuBar();
        setJMenuBar(fmenuBar);

        // Make the Window Visible
        setVisible(true);
    }

    public void CloseEditor() {
        Editor.inst.setVisible(false);
        Editor.inst.dispose();
    }
}
