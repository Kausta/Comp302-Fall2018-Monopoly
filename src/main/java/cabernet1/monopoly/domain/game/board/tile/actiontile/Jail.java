package cabernet1.monopoly.domain.game.board.tile.actiontile;

import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.player.IPlayer;

public class Jail extends ActionTile {

    public Jail(int x, int y) {
        super("Jail", TileType.Jail, x,y);
    }

    @Override
    public void landingAction(IPlayer player) {
        // TODO: implement
    }

    @Override
    public void passingAction(IPlayer player) {

    }

}
