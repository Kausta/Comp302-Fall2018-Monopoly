package cabernet1.monopoly.ui.scrollpanes;

import cabernet1.monopoly.utils.Observable;
import cabernet1.monopoly.utils.Observer;

import javax.swing.*;
import java.awt.*;

public class LogScrollPane extends BaseScrollPane implements Observer<String> {

    private static volatile LogScrollPane _instance = null;
    // Height and width coefficient constants
    private final double HEIGHT_COEFFICIENT = 1.6;
    private final double WIDTH_COEFFICIENT = 7.5;

    private JTextArea display = new JTextArea();

    private LogScrollPane() {
        // Adjusting the size of panel with the coefficients
        adjustSize(HEIGHT_COEFFICIENT, WIDTH_COEFFICIENT);

        // Adding a vertical scroll bar to pane
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // Creating a text area and add it into scroll pane
        display.setEditable(false); // text area must be non-editable
        display.setBackground(new Color(237, 240, 244));
        setViewportView(display);
    }

    public static synchronized LogScrollPane getInstance() {
        if (_instance == null) {
            _instance = new LogScrollPane();
        }
        return _instance;
    }

    public void initialize(Observable<String> announcement) {
        announcement.addObserver(this);
    }

    public void announceMessage(String message) {
        display.append(" " + message + "\n");
    }

    @Override
    public void onValueChanged(String value) {
        announceMessage(value);
        logger.d("Value is changed with observers");
    }
}
