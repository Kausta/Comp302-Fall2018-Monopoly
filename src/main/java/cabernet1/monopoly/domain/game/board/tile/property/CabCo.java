package cabernet1.monopoly.domain.game.board.tile.property;

import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;

public class CabCo extends UtilityProperty {

    private static final long serialVersionUID = 2354470330891490238L;

    public CabCo() {
        super("Cab Co", 300, 0, 0);
    }

    @Override
    public int getRent() {
        return 0;
    }
}
