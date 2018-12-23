package cabernet1.monopoly.domain.game.board.tile.property;

import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.player.Player;

public abstract class Property extends Tile {
    private int price;
    private Player owner;

    public Property(String name, TileType tileType, int price, int x, int y) {
        super(name, tileType, x , y);
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
     * gets the owner
     *
     * @return the owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * sets the owner of the tile
     *
     * @param player theh player who is buying the tile
     */
    public void setOwner(Player player) {
        owner = player;
    }

    public abstract int getRent();
}
