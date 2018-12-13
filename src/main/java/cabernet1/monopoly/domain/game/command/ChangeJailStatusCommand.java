package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.network.command.ICommand;

public class ChangeJailStatusCommand extends ICommand {
    private boolean inJail;
    private IPlayer player;

    public ChangeJailStatusCommand(IPlayer player, boolean inJail) {
        this.player = player;
        this.inJail = inJail;
    }

    public IPlayer getPlayer() {
        return player;
    }

    public boolean getJailStatus() {
        return inJail;
    }

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        game.changeJailStatus(player, inJail);

    }
}
