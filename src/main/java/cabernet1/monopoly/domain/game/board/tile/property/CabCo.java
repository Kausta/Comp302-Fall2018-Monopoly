package cabernet1.monopoly.domain.game.board.tile.property;

import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

public class CabCo extends UtilityProperty {

    private static final long serialVersionUID = 2354470330891490238L;

    public CabCo(Track track) {
        super("Cab Co", 300, 0, 0, track);
    }

    @Override
    public int getRent() {
        return 0;
    }
}
