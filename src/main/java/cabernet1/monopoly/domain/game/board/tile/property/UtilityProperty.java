package cabernet1.monopoly.domain.game.board.tile.property;

import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

public class UtilityProperty extends Property {

    private static final long serialVersionUID = 2354470330891490238L;

    public UtilityProperty(String name, int price, int x, int y, Track track) {
        super(name, TileType.UtilityProperty, price, x, y, track);
    }

    @Override
    public int getRent() {
        return 0;
    }
}
