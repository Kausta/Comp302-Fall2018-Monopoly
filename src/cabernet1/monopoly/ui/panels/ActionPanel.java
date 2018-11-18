package cabernet1.monopoly.ui.panels;

import cabernet1.monopoly.ui.buttons.BuyPropertyButton;
import cabernet1.monopoly.ui.buttons.DieImage;
import cabernet1.monopoly.ui.buttons.RollDiceButton;

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
        add(RollDiceButton.getInstance(), BorderLayout.CENTER);
        add(BuyPropertyButton.getInstance(), BorderLayout.CENTER);

        DieImage die1 = new DieImage();
        die1.startObserving(controller.die1Observeable);
        add(die1);

        DieImage die2 = new DieImage();
        die2.startObserving(controller.die2Observeable);
        add(die2);
    }
}
