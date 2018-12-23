package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.network.command.ICommand;

public class MovePlayerCommand extends ICommand {
    private int playerId;
    private int newTileId;
    private boolean takeRailRoads;

    public MovePlayerCommand(int playerId, int newTileId, boolean takeRailRoads) {
        this.playerId = playerId;
        this.newTileId = newTileId;
        this.takeRailRoads=takeRailRoads;
        System.out.println("Move playerId command is initialized");
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
        game.movePlayer(playerId, newTileId,takeRailRoads);
        game.playerListObservable.setValue(game.playerList());
    }

}
