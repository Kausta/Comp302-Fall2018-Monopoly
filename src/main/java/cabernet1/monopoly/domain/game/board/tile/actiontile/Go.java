package cabernet1.monopoly.domain.game.board.tile.actiontile;

import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.player.IPlayer;

public class Go extends ActionTile {
    private static final long serialVersionUID = 966844210964996020L;

    public Go() {
        super("Go", TileType.Go);
    }

    @Override
    public void landingAction(IPlayer player) {
        passingAction(player);
    }

    @Override
    public void passingAction(IPlayer player) {
        player.gainMoney(200);
    }
}
