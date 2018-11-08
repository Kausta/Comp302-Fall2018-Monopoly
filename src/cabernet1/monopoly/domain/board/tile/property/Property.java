package cabernet1.monopoly.domain.board.tile.property;

import cabernet1.monopoly.domain.board.tile.Tile;

public abstract class Property extends Tile {
    public int price;

    public Property(String name, int price){
        super(name);
        this.price = price;
    }
}