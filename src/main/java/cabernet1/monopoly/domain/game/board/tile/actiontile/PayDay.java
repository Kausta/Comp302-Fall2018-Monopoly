package cabernet1.monopoly.domain.game.board.tile.actiontile;

import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.player.IPlayer;

public class PayDay extends ActionTile {

    public PayDay(int x, int y) {
        super("PayDay", TileType.PayDay, x,y);
    }

    @Override
    public void landingAction(IPlayer player) {
        passingAction(player);
    }

    @Override
    public void passingAction(IPlayer player) {
        /*
        if rolled odd collect 300
        if even collect 400
        */
    }

}
