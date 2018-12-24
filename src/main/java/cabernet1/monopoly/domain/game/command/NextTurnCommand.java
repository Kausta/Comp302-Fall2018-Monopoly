package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.network.command.ICommand;

public class NextTurnCommand extends ICommand {
    private static final long serialVersionUID = 8459648363241579865L;

    /**
     * Execute the commands functionality, which will be done via calling the required methods in the game controller
     */
    @Override
    public void execute() {
        Game.getInstance().getGameController().nextTurn();
    }
}
