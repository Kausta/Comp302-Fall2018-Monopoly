package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.network.command.ICommand;

public class IncNumConsDoubleRollsCommand extends ICommand {
    private IPlayer player;

    public IncNumConsDoubleRollsCommand(IPlayer player) {
        this.player = player;
    }

    public IPlayer getPlayer() {
        return player;
    }

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        game.increaseNumberOfConsecutiveDoubleRolls(player);
    }

}
