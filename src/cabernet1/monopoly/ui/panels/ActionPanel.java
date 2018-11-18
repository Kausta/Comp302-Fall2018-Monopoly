package cabernet1.monopoly.ui.panels;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.ui.buttons.BuyPropertyButton;
import cabernet1.monopoly.ui.buttons.RollDiceButton;
import cabernet1.monopoly.ui.scrollpanes.LogScrollPane;

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
        add(RollDiceButton.getInstance());
        add(BuyPropertyButton.getInstance());
        JTextField dieLabel1 = new JTextField();
        dieLabel1.setText("5");
        dieLabel1.setFont(getFont().deriveFont(20f));
        dieLabel1.setBackground(new Color(25, 25, 25));
        dieLabel1.setForeground(new Color(237, 237, 237));
        dieLabel1.setEditable(false);
        add(dieLabel1);
    }
}
