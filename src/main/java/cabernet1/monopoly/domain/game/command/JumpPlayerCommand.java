package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;

public class JumpPlayerCommand extends MovePlayerCommand {

    private static final long serialVersionUID = 2009558307791493529L;

    public JumpPlayerCommand(int playerId, int newTileId) {
        super(playerId, newTileId, false);
    }

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        game.jumpPlayer(playerId, newTileId);
        game.playerListObservable.setValue(game.playerList());
    }
}
