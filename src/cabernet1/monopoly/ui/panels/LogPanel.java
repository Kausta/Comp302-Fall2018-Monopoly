package cabernet1.monopoly.ui.panels;

import java.awt.*;

public class LogPanel extends BasePanel {

    // Height and width coefficient constants
    private final double HEIGHT_COEFFICIENT = 1.6;
    private final double WIDTH_COEFFICIENT = 7.5;

    private static volatile LogPanel _instance = null;

    public LogPanel () {
        setBackground(Color.BLUE);

        // Adjusting the size of panel with the coefficients
        adjustSize(HEIGHT_COEFFICIENT, WIDTH_COEFFICIENT);
    }

    public static synchronized LogPanel getInstance() {
        if (_instance == null) {
            _instance = new LogPanel();
        }
        return _instance;
    }
}
