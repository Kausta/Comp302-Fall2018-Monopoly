package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.network.command.ICommand;

public class IncNumConsDoubleRollsCommand extends ICommand {
    private static final long serialVersionUID = 5144914910873534063L;
    private int playerId;

    public IncNumConsDoubleRollsCommand(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        game.increaseNumberOfConsecutiveDoubleRolls(playerId);
    }

}
