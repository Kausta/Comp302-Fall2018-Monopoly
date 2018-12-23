package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.network.command.ICommand;

public class GainMoneyCommand extends ICommand {
    private int amount;
    private int playerId;

    public GainMoneyCommand(int playerId, int amount) {
        this.playerId = playerId;
        this.amount = amount;
    }

    public int getPlayerId() {
        return playerId;
    }

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        game.playerGainMoney(playerId, amount);

    }
}
