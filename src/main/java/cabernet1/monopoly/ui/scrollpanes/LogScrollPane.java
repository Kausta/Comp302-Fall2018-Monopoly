package cabernet1.monopoly.ui.scrollpanes;

import cabernet1.monopoly.utils.Observer;

import javax.swing.*;
import java.awt.*;

public class LogScrollPane extends BaseScrollPane implements Observer<String> {

    // Height and width coefficient constants
    private static final double HEIGHT_COEFFICIENT = 1.6;
    private static final double WIDTH_COEFFICIENT = 7.5;
    private static volatile LogScrollPane _instance = null;
    private final JTextArea display = new JTextArea();

    private LogScrollPane() {
        initialize();
    }

    public static synchronized LogScrollPane getInstance() {
        if (_instance == null) {
            _instance = new LogScrollPane();
        }
        return _instance;
    }

    public void initialize() {
        // Starting observe the announcement field on GameController
        controller.announcement.addObserver(this);

        // Adjusting the size of panel with the coefficients
        adjustSize(HEIGHT_COEFFICIENT, WIDTH_COEFFICIENT);

        // Adding a vertical scroll bar to pane
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // Creating a text area and add it into scroll pane
        display.setEditable(false); // text area must be non-editable
        display.setBackground(new Color(237, 240, 244));
        setViewportView(display);
    }

    public void announceMessage(String message) {
        display.append(" " + message + "\n");
    }

    @Override
    public void onValueChanged(String value) {
        announceMessage(value);
    }
}
