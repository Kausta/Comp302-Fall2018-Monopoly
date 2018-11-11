package cabernet1.monopoly.ui.panels;

import java.awt.*;

public class ActionPanel extends BasePanel {

    // Height and width coefficient constants
    private final double HEIGHT_COEFFICIENT = 1.5;
    private final double WIDTH_COEFFICIENT = 7.5;

    private static volatile ActionPanel _instance = null;

    private ActionPanel () {
        setBackground(Color.CYAN);

        // Adjusting the size of panel with the coefficients
        adjustSize(HEIGHT_COEFFICIENT, WIDTH_COEFFICIENT);
    }

    public static synchronized ActionPanel getInstance() {
        if (_instance == null) {
            _instance = new ActionPanel();
        }
        return _instance;
    }
}
