package cabernet1.monopoly.ui.panels;

import java.awt.*;

public class RightPanel extends BasePanel {

    // Height and width coefficient constants
    private static final double HEIGHT_COEFFICIENT = 8.25;
    private static final double WIDTH_COEFFICIENT = 7.5;
    private static volatile RightPanel _instance = null;

    private RightPanel() {
        setBackground(new Color(212, 216, 221));

        // Adjusting the size of panel with the coefficients
        adjustSize(HEIGHT_COEFFICIENT, WIDTH_COEFFICIENT);
    }

    public static synchronized RightPanel getInstance() {
        if (_instance == null) {
            _instance = new RightPanel();
        }
        return _instance;
    }
}
