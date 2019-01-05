package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.network.command.ICommand;

public class ChangeJailStatusCommand extends ICommand {
    private static final long serialVersionUID = -4319110759036699314L;
    private final boolean inJail;
    private final int playerId;

    public ChangeJailStatusCommand(int playerId, boolean inJail) {
        this.playerId = playerId;
        this.inJail = inJail;
    }

    public int getPlayerId() {
        return playerId;
    }

    public boolean getJailStatus() {
        return inJail;
    }

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        game.changeJailStatus(playerId, inJail);

    }
}
