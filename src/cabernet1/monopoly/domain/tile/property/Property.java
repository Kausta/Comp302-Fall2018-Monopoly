package cabernet1.monopoly.domain.tile.property;

import cabernet1.monopoly.domain.tile.Tile;

public abstract class Property extends Tile {
    public int price;

    public Property(String name, int price){
        super(name);
        this.price = price;
    }
}