package cabernet1.monopoly.domain.network.command.commands;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.network.command.ICommand;

public class IncNumConsDoubleRollsCommand extends ICommand {
    private IPlayer player;

    public IPlayer getPlayer() {
        return player;
    }

    public IncNumConsDoubleRollsCommand(IPlayer player) {
        this.player = player;
    }

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        game.increaseNumberOfConsecutiveDoubleRolls(player);


    }

}
