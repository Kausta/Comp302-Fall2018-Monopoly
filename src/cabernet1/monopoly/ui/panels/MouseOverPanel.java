package cabernet1.monopoly.ui.panels;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class MouseOverPanel extends BasePanel {

    private static volatile MouseOverPanel _instance = null;
    // Height and width coefficient constants
    private final double HEIGHT_COEFFICIENT = 2;
    private final double WIDTH_COEFFICIENT = 7.5;

    private MouseOverPanel() {
        setBackground(new Color(237, 240, 244));
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

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
