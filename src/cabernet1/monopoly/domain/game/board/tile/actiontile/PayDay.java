package cabernet1.monopoly.domain.game.board.tile.actiontile;

import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;

public class PayDay extends ActionTile{

    public PayDay(){
        super("PayDay", TileType.PayDay);
    }

    @Override
    public void landingAction() {
        passingAction();
    }

    @Override
    public void passingAction() {
        /*
        if rolled odd collect 300
        if even collect 400
        */
    }

}