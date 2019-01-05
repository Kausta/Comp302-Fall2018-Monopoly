package cabernet1.monopoly.domain.game.board.tile.property;

import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;
import cabernet1.monopoly.domain.game.player.Player;
import cabernet1.monopoly.utils.RepresentationInvariant;

public abstract class Property extends Tile implements RepresentationInvariant {
    private static final long serialVersionUID = -8930335527754939743L;
    private final int price;
    private Player owner;

    public Property(String name, TileType tileType, int price, int x, int y, Track track) {
        super(name, tileType, x, y, track);
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

    @Override
    public boolean repOK() {
        if (owner == null) {
            return price > 0;
        }
        return price > 0 && owner.repOK();
    }

    @Override
    public String toString() {
        return "Property{ " +
                "price: " + price +
                ", owner: " + owner +
                ", " + super.toString() + " }";
    }
}
