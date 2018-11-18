package cabernet1.monopoly.domain.game.board.tile.actiontile;

import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.player.Player;

public class Go extends ActionTile{
    public Go(){
        super("Go", TileType.Go);
    }

    @Override
    public void landingAction(Player player) {
        passingAction(player);
    }

    @Override
    public void passingAction(Player player) {
        player.gainMoney(200);
    }
}