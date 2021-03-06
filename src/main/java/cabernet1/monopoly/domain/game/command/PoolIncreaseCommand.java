package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.network.command.ICommand;

public class PoolIncreaseCommand extends ICommand {
    private static final long serialVersionUID = -19580553237329520L;
    final int amount;

    public PoolIncreaseCommand(int amount) {
        this.amount = amount;
    }

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        game.increasePool(amount);


    }

}
