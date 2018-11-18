package cabernet1.monopoly.domain.game.board.tile.actiontile;

import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.player.IPlayer;

public class Jail extends ActionTile {

    public Jail(){
        super("Jail", TileType.Jail);
    }

    @Override
    public void landingAction(IPlayer player) {
        // TODO: implement
    }

    @Override
    public void passingAction(IPlayer player) {

    }

}