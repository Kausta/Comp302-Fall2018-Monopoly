package cabernet1.monopoly.domain.game.board.tile;

import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;

public abstract class Tile{
    private String name;
    private TileType tileType;
    public Tile(String name, TileType tileType){
        this.name = name;
        this.tileType=tileType;
    }

	public String getName() {
		return name;
	}
	public TileType getTileType() {
    	return tileType;
    }
}
