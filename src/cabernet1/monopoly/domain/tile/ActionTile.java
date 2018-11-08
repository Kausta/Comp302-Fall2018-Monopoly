package cabernet1.monopoly.domain.tile;

import cabernet1.monopoly.domain.tile.enumerators.TileType;

public class ActionTile extends Tile{
    public TileType tileType;

    public ActionTile(String name, TileType tileType){
        super(name);
        this.tileType = tileType;
    }
}