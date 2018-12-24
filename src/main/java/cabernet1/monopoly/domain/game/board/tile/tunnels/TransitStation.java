package cabernet1.monopoly.domain.game.board.tile.tunnels;

import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.die.util.DiceCup;

public class TransitStation extends Tile {
    TransitStation otherEnd;
    public TransitStation(int x, int y) {
        super("Transit", TileType.TransitStation, x, y);
    }

    public Tile passThroughTunnel(int direction, boolean takeRailRoads){
     if (takeRailRoads)
         return otherEnd;
     return getNextTile(direction);
    }
    public void setOtherEnd(TransitStation otherEnd){
        this.otherEnd=otherEnd;
    }
}
