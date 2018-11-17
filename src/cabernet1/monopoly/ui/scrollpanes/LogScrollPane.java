package cabernet1.monopoly.ui.scrollpanes;

import javax.swing.*;
import java.awt.*;

public class LogScrollPane extends BaseScrollPane {

    private static volatile LogScrollPane _instance = null;
    // Height and width coefficient constants
    private final double HEIGHT_COEFFICIENT = 1.6;
    private final double WIDTH_COEFFICIENT = 7.5;

    private LogScrollPane() {
        // Adjusting the size of panel with the coefficients
        adjustSize(HEIGHT_COEFFICIENT, WIDTH_COEFFICIENT);

        // Initialize the scroll pane
        initialize();
    }

    public static synchronized LogScrollPane getInstance() {
        if (_instance == null) {
            _instance = new LogScrollPane();
        }
        return _instance;
    }

    private void initialize() {
        // Adding a vertical scroll bar to pane
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // Creating a text area and add it into scroll pane
        JTextArea display = new JTextArea();
        display.setEditable(false); // text area must be non-editable
        display.setBackground(new Color(237, 240, 244));
        setViewportView(display);

        // Sample: how to append text to text area
        // display.append("Lorem ipsum dolor sit amet\n");
    }
}
