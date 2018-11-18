package cabernet1.monopoly.ui.buttons;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;
import cabernet1.monopoly.utils.Observer;

import javax.swing.*;

public abstract class BaseButton extends JButton implements Observer<String> {
    public Logger logger = LoggerFactory.getInstance().getLogger(getClass());
    public GameController controller = Game.getInstance().getGameController();

    public BaseButton() {

    }

    public abstract void onValueChanged(String value);
}
