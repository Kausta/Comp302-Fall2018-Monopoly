package cabernet1.monopoly.domain.game.board.tile.property;

import cabernet1.monopoly.domain.game.board.tile.Tile;

public abstract class Property extends Tile {
	private int price;

    public Property(String name, int price){
        super(name);
        this.price = price;
    }
    public int getPrice() {
    	return price;
    }
}
