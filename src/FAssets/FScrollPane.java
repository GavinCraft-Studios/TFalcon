package FAssets;

import javax.swing.*;
import java.awt.*;

public class FScrollPane extends JScrollPane {
    public FScrollPane(Component component, boolean useHorizontal) {
        super(component);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        if (useHorizontal)
        {
            setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        } else {
            setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        }
    }

    public FTextArea getFTextArea()
    {
        FTextArea textArea = (FTextArea) super.getViewport().getView();
        return textArea;
    }
}
