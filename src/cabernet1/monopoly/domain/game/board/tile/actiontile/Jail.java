package cabernet1.monopoly.domain.game.board.tile.actiontile;

import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.player.Player;

public class Jail extends ActionTile {

    public Jail(){
        super("Jail", TileType.Jail);
    }

    @Override
    public void landingAction(Player player) {
        // TODO: implement
    }

    @Override
    public void passingAction(Player player) {

    }

}