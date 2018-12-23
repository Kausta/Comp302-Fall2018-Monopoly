package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.game.player.enumerators.PlayerMovementStatus;
import cabernet1.monopoly.domain.network.command.ICommand;

public class ChangeMovementStatusCommand extends ICommand {

    private PlayerMovementStatus movementStatus;
    private int playerId;

    public ChangeMovementStatusCommand(int playerId, PlayerMovementStatus status) {
        this.playerId = playerId;
        this.movementStatus = status;
    }

    public int getPlayerId() {
        return playerId;
    }

    public PlayerMovementStatus getmovementStatus() {
        return movementStatus;
    }

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        game.changeMovementStatus(playerId, movementStatus);
    }
}
