package cabernet1.monopoly.domain.game.board.tile.property;

import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.player.Player;

public abstract class Property extends Tile {
	private int price;
    private Player owner;
    public Property(String name, int price){
        super(name);
        this.price = price;
        this.owner=null;
    }
    private void ownBy(Player player) {
    	this.owner=player;
    }
    private Player getOwner(Player player) {
    	return owner;
    }
    public int getPrice() {
    	return price;
    }
}
