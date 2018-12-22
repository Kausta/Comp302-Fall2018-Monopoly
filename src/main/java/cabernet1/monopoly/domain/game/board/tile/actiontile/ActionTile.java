package cabernet1.monopoly.domain.game.board.tile.actiontile;

import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.player.IPlayer;

public abstract class ActionTile extends Tile {

    private static final long serialVersionUID = 3426134948066241573L;

    public ActionTile(String name, TileType tileType) {
        super(name, tileType);
    }

    public abstract void landingAction(IPlayer player);

    public abstract void passingAction(IPlayer player);
}
