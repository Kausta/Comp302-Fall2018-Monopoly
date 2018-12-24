/*
 * TODO: write which functions are called from the current device, and which are called from network (hence, if there is need to distrubute an action
 *       through devices or it's already distributed
 */
package cabernet1.monopoly.domain.game.player;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.NetworkController;
import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.command.*;
import cabernet1.monopoly.domain.game.die.cup.JailDiceCup;
import cabernet1.monopoly.domain.game.die.cup.NormalDiceCup;
import cabernet1.monopoly.domain.game.die.enumerators.JailDiceCupStatus;
import cabernet1.monopoly.domain.game.die.enumerators.NormalDiceCupStatus;
import cabernet1.monopoly.domain.game.player.enumerators.PlayerMovementStatus;
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
     * handles the turn of this player, by rolling a dice and move accordingly
     *
     * @modifies this.numberOfConsecutiveDoublesRolls, this.PlayerMovementStatus,
     * this.inJail, this.curTile, this.ownedProperty,this.money
     * @effects simulate playing the turn for this player, by rolling the
     * cup, and moving based on its results, player might get into jail,
     * if he/she plays Double for the third time in row
     */
    @Override
    public void playTurn() {
        NormalDiceCup cup = NormalDiceCup.getInstance();
        NormalDiceCupStatus rollStatus = cup.rollCup();
        NetworkController nc = Network.getInstance().getNetworkController();
        GameController controller = Game.getInstance().getGameController();
        controller.showDiceValue();
        switch (rollStatus) {
            case NORMAL_MOVE:
                handleNormalMove();
                break;
            case DOUBLE_MOVE:
                incrementSteps();
                handleDoubleMove();
                break;
            case TRIPLE_MOVE:
                handleTriplesMove();
                break;
            case MR_MONOPOLY_MOVE:
                nc.sendCommand(new ChangeMovementStatusCommand(this.getID(), PlayerMovementStatus.MRMONOPOLY_MOVE));
                handleNormalMove();
                break;
            case BUS_MOVE:
                nc.sendCommand(new ChangeMovementStatusCommand(this.getID(), PlayerMovementStatus.BUS_MOVE));
                handleNormalMove();
                break;
        }
        if (getNumSteps() == 0) {
            controller.disableRollDice();
            controller.enableEndTurn();
        }

    }

    // TODO: need to check building buying availability hear can check easier with owned properties
    @Override
    public void playJailturn() {
        JailDiceCup cup = JailDiceCup.getInstance();
        JailDiceCupStatus rollStatus = cup.rollCup();
        NetworkController nc = Network.getInstance().getNetworkController();

        String message;
        switch (rollStatus) {
            case DOUBLES:
                message = getName() + " has got out of jail!";
                nc.sendCommand(new AnnounceMessageCommand(message));
                // TODO: handleDoubleMove for JailCup: handleJailDoubleMove();
                break;
            case NOT_DOUBLES:
                message = getName() + " has not rolled Doubles. Hence, he/she will stay in jail for this turn";
                nc.sendCommand(new AnnounceMessageCommand(message));
                break;
        }

    }

    /**
     * simulates moving this player when playing a normal move
     *
     * @requires cupStatus of NormalDiceCup is NORMAL_MOVE
     * @modifies this.curTile, this.ownedProperty,this.money
     * @effects move the player to a newTile based on the sum of faceValues
     * on the dice, and handle the events of standing on the dest tile
     * (like pay rent, buying tile, upgrading building,draw card)
     * @see PlayerMovementStatus
     */
    @Override
    protected void handleNormalMove() {
        Board board = Board.getInstance();
        NormalDiceCup cup = NormalDiceCup.getInstance();
        Tile newTile = board.getNextTile(curTile, direction, cup.getFacesValue(), cup.isEven());
        NetworkController nc = Network.getInstance().getNetworkController();

        String previousTile = curTile.getName();
        String message = getName() + " has moved from " + previousTile + "  to " + newTile.getName();
        nc.sendCommand(new AnnounceMessageCommand(message));
        nc.sendCommand(new MovePlayerCommand(this.getID(), newTile.getID(), cup.isEven()));
        board.handleTile(this, newTile);
    }


    /**
     * simulate moving this player when playing a Mr.Monopoly move
     *
     * @requires cupStatus of NormalDiceCup is MRMONOPOLY_MOVE
     * player already played the normal move part of the Mr.Monopoly move
     * @modifies this.inJail, this.curTile, this.ownedProperty,this.money
     * @effects move this player to the next unowned property if available,
     * if not, try to move to the next rent-able property
     * in either case: handle the events of standing on the dest tile
     * (like pay rent, buying tile, upgrading building,draw card)
     * if neither happened, stay in your place and do nothing
     * @see PlayerMovementStatus
     */
    @Override
    protected void handleMrMonopolyMove() {
        Board board = Board.getInstance();
        NormalDiceCup cup = NormalDiceCup.getInstance();
        NetworkController nc = Network.getInstance().getNetworkController();

        Tile nextTile = board.nextUnownedProperty(curTile, direction, cup.isEven());
        String message;
        if (nextTile == null) {
            nextTile = board.nextRentableProperty(curTile, this, direction, cup.isEven());
            if (nextTile == null) {
                message = "No rentable Properties has been found on your way, hence you will stay in your place";
            } else {
                message = "You will move to the next rentable Property, namely " + nextTile.getName();
            }
        } else {
            message = "You will move to the next un owned Property, namely " + nextTile.getName();
        }
        nc.sendCommand(new AnnounceMessageCommand(message));

        if (nextTile != null) {
            nc.sendCommand(new MovePlayerCommand(this.getID(), nextTile.getID(), cup.isEven()));
            board.handleTile(this, nextTile);
        }
    }

    /**
     * simulate moving this player when playing a bus move
     * (speed die has bus icon shown on it)
     *
     * @requires cupStatus of NormalDiceCup is BUS_MOVE
     * player already played the normal move part of the bus move
     * @modifies this.inJail, this.curTile, this.ownedProperty,this.money
     * @effects move this player to the nearest community chest or chance tile
     * (which ever nearest), if available
     * if the nearest is a community chest tile: draw a card from the community chest cards, and act accordingly
     * if the nearest is a chance tile: draw a card from the chance cards, and act accordingly
     * if neither is available, stay in your place and do nothing
     * @see PlayerMovementStatus
     */
    @Override
    protected void handleBusMove() {
        NetworkController nc = Network.getInstance().getNetworkController();
        Board board = Board.getInstance();
        NormalDiceCup cup = NormalDiceCup.getInstance();
        Tile nextTile = board.nextUnownedProperty(curTile, direction, cup.isEven());
        String message;
        if (nextTile == null) {
            message = "No community chest or chance card on your way, hence you will stay in your place";
            nc.sendCommand(new AnnounceMessageCommand(message));
        } else {
            if (nextTile.getTileType() == TileType.CommunityChest)
                message = "You will move to the next community chest tile";
            else
                message = "You will move to the next Chance tile";
            nc.sendCommand(new AnnounceMessageCommand(message));
            nc.sendCommand(new MovePlayerCommand(this.getID(), nextTile.getID(), cup.isEven()));
            board.handleTile(this, nextTile);
        }
    }

    @Override
    protected void handleTriplesMove() {
        Game.getInstance().getGameController().chooseTile(this);
    }

    /**
     * simulate moving this player when playing a DOUBLE move
     * (the two normal dice has the same number shown on their top faces)
     *
     * @requires cupStatus of NormalDiceCup is DOUBLE_MOVE
     * @modifies this.inJail, this.curTile, this.ownedProperty,this.money
     * @effects check if the player has played three Double moves in a row,
     * if so, move this player to jail, otherwise increase the
     * number of consecutive double moves, and play a normal move
     * @see PlayerMovementStatus
     */
    @Override
    protected void handleDoubleMove() {
        NetworkController nc = Network.getInstance().getNetworkController();
        nc.sendCommand(new IncNumConsDoubleRollsCommand(this.getID()));
        ++numberOfConsecutiveDoublesRolls;
        if (numberOfConsecutiveDoublesRolls == 3) {
            goJail();
            return;
        }
        nc.sendCommand(new ChangeMovementStatusCommand(this.getID(), PlayerMovementStatus.DOUBLE_MOVE));
        handleNormalMove();
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

    /**
     * transport the player immediately to a given tile
     * that is, player doesn't pass by any other tile on the way
     *
     * @param newTile the new tile to move this player into
     * @requires newTile should belong to Board
     * @modifies this.inJail, this.curTile, this.ownedProperty,this.money
     * @effects moves the player immediately to newTile with passing through any
     * intermediary tiles, and handle the actions on landing on this tile
     * (like pay rent, buying tile, upgrading building,draw card)
     */
    @Override
    public void jumpToTile(Tile newTile) {
        NetworkController nc = Network.getInstance().getNetworkController();
        String previousTile = curTile.getName();
        //TODO implement proper direction finder or special jump player function
        nc.sendCommand(new MovePlayerCommand(this.getID(), newTile.getID(), true));
        String message = getName() + " has transposed immediately from " + previousTile + " to " + curTile.getName();
        nc.sendCommand(new AnnounceMessageCommand(message));
        Board.getInstance().handleTile(this, newTile);
    }

    @Override
    public boolean repOK() {
        return super.repOK();
    }

    @Override
    public String toString() {
        return "Player{" + super.toString() + "}";
    }
}
