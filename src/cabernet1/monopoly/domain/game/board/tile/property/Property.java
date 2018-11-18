package cabernet1.monopoly.domain.game.board.tile.property;

import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.player.Player;

public abstract class Property extends Tile {
    private int price;
    private Player owner;

    public Property(String name, TileType tileType, int price) {
        super(name, tileType);
        this.price = price;
        owner = null;
    }

    /**
     * @return price of the property
     */
    public int getPrice() {
        return price;
    }

    /**
     * sets the owner of the tile
     * @param player theh player who is buying the tile
     */
    public void setOwner(Player player){
        owner = player;
    }

    /**
     * gets the owner
     * @return the owner
     */
    public Player getOwner(){
        return owner;
    }

    public abstract int getRent();
}
