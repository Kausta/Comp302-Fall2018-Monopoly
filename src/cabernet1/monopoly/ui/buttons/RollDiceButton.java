package cabernet1.monopoly.ui.buttons;

import cabernet1.monopoly.utils.Observable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RollDiceButton extends BaseButton {

    private static volatile RollDiceButton _instance = null;

    private RollDiceButton() {
        setText("Roll Dice");
        initialize();
    }

    public static synchronized RollDiceButton getInstance() {
        if (_instance == null) {
            _instance = new RollDiceButton();
        }
        return _instance;
    }

    private void initialize() {
        controller.rollButton.addObserver(this);

        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Add a rollDice function to GameController
                controller.rollDice();
                logger.d("Roll Dice button is clicked");
            }
        });
    }

    @Override
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
