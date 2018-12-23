package cabernet1.monopoly.domain.game.board.tile.actiontile;

import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.player.IPlayer;

public abstract class ActionTile extends Tile {

    public ActionTile(String name, TileType tileType, int x, int y) {
        super(name, tileType, x ,y );
    }

    public abstract void landingAction(IPlayer player);

    public abstract void passingAction(IPlayer player);
}
