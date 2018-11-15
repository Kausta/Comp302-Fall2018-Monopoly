package cabernet1.monopoly.ui.panels;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class ActionPanel extends BasePanel {

    private static volatile ActionPanel _instance = null;
    // Height and width coefficient constants
    private final double HEIGHT_COEFFICIENT = 1.45;
    private final double WIDTH_COEFFICIENT = 7.5;

    private ActionPanel() {
        setBackground(new Color(237, 240, 244));
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        // Adjusting the size of panel with the coefficients
        adjustSize(HEIGHT_COEFFICIENT, WIDTH_COEFFICIENT);

        // Initialize the panel
        initialize();
    }

    public static synchronized ActionPanel getInstance() {
        if (_instance == null) {
            _instance = new ActionPanel();
        }
        return _instance;
    }

    private void initialize() {
        JButton rollDice = new JButton("Roll Dice!");
        add(rollDice);
    }
}
