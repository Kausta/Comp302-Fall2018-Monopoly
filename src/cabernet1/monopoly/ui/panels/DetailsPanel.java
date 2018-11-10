package cabernet1.monopoly.ui.panels;

import java.awt.*;

public class DetailsPanel extends BasePanel {

    // Height and width coefficient constants
    private final double HEIGHT_COEFFICIENT = 3;
    private final double WIDTH_COEFFICIENT = 7.5;

    private static volatile DetailsPanel _instance = null;

    public DetailsPanel () {
        setBackground(Color.GREEN);

        // Adjusting the size of panel with the coefficients
        adjustSize(HEIGHT_COEFFICIENT, WIDTH_COEFFICIENT);
    }

    public static synchronized DetailsPanel getInstance() {
        if (_instance == null) {
            _instance = new DetailsPanel();
        }
        return _instance;
    }
}
