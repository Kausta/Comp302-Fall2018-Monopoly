package cabernet1.monopoly.domain.game.board.tile.actiontile;

import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.player.Player;

public class PayDay extends ActionTile {

    public PayDay() {
        super("PayDay", TileType.PayDay);
    }

    @Override
    public void landingAction(Player player) {
        passingAction(player);
    }

    @Override
    public void passingAction(Player player) {
        /*
        if rolled odd collect 300
        if even collect 400
        */
    }

}
