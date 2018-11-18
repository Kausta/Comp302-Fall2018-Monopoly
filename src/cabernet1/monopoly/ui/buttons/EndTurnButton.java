package cabernet1.monopoly.ui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndTurnButton extends BaseButton {

    private static volatile EndTurnButton _instance = null;

    private EndTurnButton() {
        setText("End Turn");
        initialize();
    }

    public static synchronized EndTurnButton getInstance() {
        if (_instance == null) {
            _instance = new EndTurnButton();
        }
        return _instance;
    }

    private void initialize() {
        controller.endButton.addObserver(this);

        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Add a rollDice function to GameController
                controller.endTurn();
            }
        });
    }

    public void onValueChanged(Boolean value) {
        // Change visibility
        if(value) {
            setVisible(true);
        }
        else {
            setVisible(false);
        }
    }
}
