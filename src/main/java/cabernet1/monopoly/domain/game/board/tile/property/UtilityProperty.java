package cabernet1.monopoly.domain.game.board.tile.property;

import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;

public class UtilityProperty extends Property {

    public UtilityProperty(String name, int price, int x, int y) {

        super(name, TileType.UtilityProperty, price,x,y);
    }

    @Override
    public int getRent() {
        return 0;
    }
}
