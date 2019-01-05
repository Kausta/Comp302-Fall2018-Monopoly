package cabernet1.monopoly.domain.game.board.tile.tunnels;

import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;

public class TransitStation extends Tile {
    private static final long serialVersionUID = 4647788523820186213L;
    private TransitStation otherEnd;

    public TransitStation(int x, int y) {
        super("Transit", TileType.TransitStation, x, y);
    }

    public Tile passThroughTunnel(boolean direction, boolean takeRailRoads) {
        if (takeRailRoads)
            return otherEnd;
        return getNextTile(direction);
    }

    public void setOtherEnd(TransitStation otherEnd) {
        this.otherEnd = otherEnd;
    }
}
