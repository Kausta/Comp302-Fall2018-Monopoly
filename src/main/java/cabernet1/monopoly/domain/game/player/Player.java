/*
 * TODO: write which functions are called from the current device, and which are called from network (hence, if there is need to distrubute an action
 *       through devices or it's already distributed
 */
package cabernet1.monopoly.domain.game.player;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.NetworkController;
import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.command.*;
import cabernet1.monopoly.utils.RepresentationInvariant;

/**
 * This class represents the normal (human) player in the monopoly game
 *
 * @overview Player represents the normal player in the monopoly game
 */
public class Player extends IPlayer implements RepresentationInvariant {
    private static final long serialVersionUID = -3520681199167071025L;

    /**
     * Class Constructor
     *
     * @param ID           the ID of the player, unique for each player
     * @param name         the name of the player
     * @param money        the initial money
     * @param defaultOrder the initial order of playing, [1,N.Players]
     * @param currentTile  the initial tile that stands on
     * @effects register the player's information
     */
    public Player(int ID, String name, int money, int defaultOrder, Tile currentTile) {
        super(ID, name, money, defaultOrder, currentTile);
    }


    /**
     * Gets this player into Jail
     *
     * @modifies this.inJail, this.curTile
     * @effects move the player into jail tile, and change its status to be in jail
     * @see Player#handleDoubleMove()
     */
    @Override
    protected void goJail() {
        NetworkController nc = Network.getInstance().getNetworkController();
        Board board = Board.getInstance();
        String message = getName() + " has got into jail";

        nc.sendCommand(new AnnounceMessageCommand(message));
        nc.sendCommand(new ChangeJailStatusCommand(this.getID(), true));

        Tile jailTile = board.getJailTile();
        nc.sendCommand(new MovePlayerCommand(this.getID(), jailTile.getID(), true));
        // TODO ask about using card to get out of jail

    }

    @Override
    public boolean repOK() {
        return super.repOK();
    }

    @Override
    public String toString() {
        return "Player{" + super.toString() + "}";
    }

    protected void handleTriplesMove() {
        Game.getInstance().getGameController().chooseTile(this);
    }
}
