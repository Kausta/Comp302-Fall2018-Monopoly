package cabernet1.monopoly.domain.game.board.tile.property;

import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;

public class UtilityProperty extends Property{

    public UtilityProperty(String name, int price){
        super(name,TileType.UtilityProperty, price);
    }
}
