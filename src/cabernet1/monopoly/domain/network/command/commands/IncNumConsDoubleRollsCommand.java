package cabernet1.monopoly.domain.network.command.commands;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.player.Player;
import cabernet1.monopoly.domain.network.command.ICommand;

public class IncNumConsDoubleRollsCommand extends ICommand {
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public IncNumConsDoubleRollsCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        game.increaseNumberOfConsecutiveDoubleRolls(player);


    }

}
