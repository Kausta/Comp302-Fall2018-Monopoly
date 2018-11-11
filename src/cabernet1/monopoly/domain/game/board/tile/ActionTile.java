package cabernet1.monopoly.domain.game.board.tile;

import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;

public class ActionTile extends Tile{
    public TileType tileType;

    public ActionTile(String name, TileType tileType){
        super(name);
        this.tileType = tileType;
    }
}
