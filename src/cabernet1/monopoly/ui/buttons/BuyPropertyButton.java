package cabernet1.monopoly.ui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyPropertyButton extends BaseButton {

    private static volatile BuyPropertyButton _instance = null;

    private BuyPropertyButton() {
        setText("Buy Property");
        initialize();
    }

    public static synchronized BuyPropertyButton getInstance() {
        if (_instance == null) {
            _instance = new BuyPropertyButton();
        }
        return _instance;
    }

    private void initialize() {
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Add a rollDice function to GameController
                controller.buyProperty();
            }
        });
    }

    public void onValueChanged(String value) {
        // Change visibility
    }
}
