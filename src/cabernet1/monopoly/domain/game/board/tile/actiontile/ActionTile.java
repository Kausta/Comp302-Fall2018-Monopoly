package cabernet1.monopoly.domain.game.board.tile.actiontile;

import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;

public abstract class ActionTile extends Tile{
    private TileType tileType;

    public ActionTile(String name, TileType tileType){
        super(name);
        this.tileType = tileType;
    }

    /**
     * @return the type of the tile
     */
    public TileType getTileType() {
    	return tileType;
    }

    public abstract void landingAction();

    public abstract void passingAction();
}
