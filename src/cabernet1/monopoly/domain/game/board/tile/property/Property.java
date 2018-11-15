package cabernet1.monopoly.domain.game.board.tile.property;

import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;

public abstract class Property extends Tile {
	private int price;

    public Property(String name,TileType tileType, int price){
        super(name,tileType);
        this.price = price;
    }
    public int getPrice() {
    	return price;
    }
}
