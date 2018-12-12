package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.network.command.ICommand;

public class GainMoneyCommand extends ICommand {
    private int amount;
    private IPlayer player;

    public GainMoneyCommand(IPlayer player, int amount) {
        this.player = player;
        this.amount = amount;
    }

    public IPlayer getPlayer() {
        return player;
    }

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        game.playerGainMoney(player, amount);

    }
}
