package cabernet1.monopoly.domain.game.board.tile;

import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

public class FreeTile extends Tile {
    private static final long serialVersionUID = -6435864561214763690L;

    public FreeTile(String name, int x, int y) {
        // TODO: add track as variable
        super(name, TileType.FreeParking, x, y, Track.Center);
    }
}
