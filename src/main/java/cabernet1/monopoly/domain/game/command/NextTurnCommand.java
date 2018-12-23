package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.network.command.ICommand;

public class NextTurnCommand extends ICommand {
    /**
     * Execute the commands functionality, which will be done via calling the required methods in the game controller
     */
    @Override
    public void execute() {
        Game.getInstance().getGameController().nextTurn();
    }
}
