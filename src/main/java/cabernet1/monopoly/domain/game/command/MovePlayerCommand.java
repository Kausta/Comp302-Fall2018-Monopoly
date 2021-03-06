package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.network.command.ICommand;

public class MovePlayerCommand extends ICommand {
    private static final long serialVersionUID = 7516943243199598634L;
    protected final int playerId;
    protected final int newTileId;
    protected final boolean takeRailRoads;

    public MovePlayerCommand(int playerId, int newTileId, boolean takeRailRoads) {
        this.playerId = playerId;
        this.newTileId = newTileId;
        this.takeRailRoads = takeRailRoads;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getNewTileId() {
        return newTileId;
    }

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        game.movePlayer(playerId, newTileId, takeRailRoads);

    }

}
