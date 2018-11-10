package cabernet1.monopoly.ui.panels;

import java.awt.*;

public class MouseOverPanel extends BasePanel {

    // Height and width coefficient constants
    private final double HEIGHT_COEFFICIENT = 2;
    private final double WIDTH_COEFFICIENT = 7.5;

    private static volatile MouseOverPanel _instance = null;

    public MouseOverPanel () {
        setBackground(Color.MAGENTA);

        // Adjusting the size of panel with the coefficients
        adjustSize(HEIGHT_COEFFICIENT, WIDTH_COEFFICIENT);
    }

    public static synchronized MouseOverPanel getInstance() {
        if (_instance == null) {
            _instance = new MouseOverPanel();
        }
        return _instance;
    }
}
