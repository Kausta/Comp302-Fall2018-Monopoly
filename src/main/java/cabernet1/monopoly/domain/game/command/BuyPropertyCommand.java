package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.network.command.ICommand;

public class BuyPropertyCommand extends ICommand {

    private static final long serialVersionUID = -3834713065869223713L;

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        game.activateBuyProperty();

    }
}
