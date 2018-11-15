/*
 * TODO: write which functions are called from the current device, and which are called from network (hence, if there is need to distrubute an action
 *       through devices or it's already distributed
 */
package cabernet1.monopoly.domain.game.player;

import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.die.enumerators.JailDiceCupStatus;
import cabernet1.monopoly.domain.game.die.enumerators.NormalDiceCupStatus;
import cabernet1.monopoly.domain.game.die.util.JailDiceCup;
import cabernet1.monopoly.domain.game.die.util.NormalDiceCup;

public class Player extends IPlayer {
    public Player(int ID, String name, int money, int defaultOrder, Tile currentTile) {
        super(ID, name, money, defaultOrder, currentTile);
    }

    @Override
    public void playTurn(NormalDiceCup cup, Board board) {
        NormalDiceCupStatus rollStatus = cup.rollCup();
        // TODO announceMessage of the dice result to UI
        // TODO changeDiceFaces(die1: DieFaces,die2: DieFaces,die3: DieFaces)
        switch (rollStatus) {
            case NORMAL_MOVE:
                handleNormalMove(cup, board);
                break;
            case DOUBLE_MOVE:
                handleDoubleMove(cup, board);
                break;
            case TRIPLE_MOVE:
                handleTriplesMove(cup, board);
                break;
            case MR_MONOPOLY_MOVE:
                //TODO:network.changeMovementStatus(this,PlayerMovementStatus.MRMONOPOLY_MOVE);
                handleNormalMove(cup, board);
                break;
            case BUS_MOVE:
                //TODO:network.changeMovementStatus(this,PlayerMovementStatus.BUS_MOVE);
                handleNormalMove(cup, board);
                break;
        }

    }

    @Override
    public void playJailturn(JailDiceCup cup, Board board) {
        JailDiceCupStatus rollStatus = cup.rollCup();
        String message;
        switch (rollStatus) {
            case DOUBLES:
                message = getName() + " has got out of jail!";
                // TODO network.announceMessage(message)
                // TODO: handleDoubleMove for JailCup: handleDoubleMove(cup,board);
                break;
            case NOT_DOUBLES:
                message = getName() + " has not rolled Doubles. Hence, he/she will stay in jail for this turn";
                // TODO network.announceMessage(message)
                break;
        }

    }

    @Override
    protected void handleNormalMove(NormalDiceCup cup, Board board) {
        Tile newTile = board.getNextTile(curTile, cup.getFacesValue());
        String previousTile = curTile.getName();
        // TODO network.changeCurrentTile(this,newTile)
        String message;
        if (newTile.equals(board.getJailTile())) {
            message = getName() + " will land on jail, hence, he/she will go to jail";
            goJail(board);
        } else {
            message = getName() + " has moved from " + previousTile + "  to " + curTile.getName();
            // TODO network.announceMessage(message)
        }


        // TODO network.movePlayer(this,curTile);


    }

    @Override
    protected void handleMrMonopolyMove(NormalDiceCup cup, Board board) {
        Tile nextTile = board.nextUnownedProperty(curTile, directionClockwise, cup.getFacesValue());
        String message;
        if (nextTile == null) {
            nextTile = board.nextRentableProperty(curTile, directionClockwise, cup.getFacesValue());
            if (nextTile == null) {
                message = "No rentable Properties has been found on your way, hence you will stay in your place";
            } else {
                message = "You will move to the next rentable Property, namely " + nextTile.getName();
            }
        } else {
            message = "You will move to the next un owned Property, namely " + nextTile.getName();
        }
        // TODO: UI.announceMessage(message)
        if (nextTile != null) {
            // TODO:network.changeCurrentTile(this,newTile)
            // TODO: network.movePlayer(this,newTile)
        }
    }

    @Override
    protected void handleBusMove(NormalDiceCup cup, Board board) {

        // TODO implement handleBusIconMove function
        Tile nextTile = board.nextUnownedProperty(curTile, directionClockwise, cup.getFacesValue());
        String message;
        if (nextTile == null) {
            message = "No community chest or chance card on your way, hence you will stay in your place";
            // TODO: UI.announceMessage(message)
        } else {
            if (nextTile.getTileType() == TileType.CommunityChest)
                message = "You will move to the next community chest tile";
            else
                message = "You will move to the next Chance tile";
            // TODO: UI.announceMessage(message)
            // TODO:network.changeCurrentTile(this,newTile)
            // TODO: network.movePlayer(this,newTile)
        }
    }

    @Override
    protected void handleTriplesMove(NormalDiceCup cup, Board board) {
        // TODO UI.chooseTile()

    }

    @Override
    protected void handleDoubleMove(NormalDiceCup cup, Board board) {
        //TODO: network.increaseNumberOfConsecutiveDoublesRolls()
        ++numberOfConsecutiveDoublesRolls;
        if (numberOfConsecutiveDoublesRolls == 3) {
            goJail(board);
            return;
        }
        //TODO:network.changeMovementStatus(this,PlayerMovementStatus.DOUBLE_MOVE);
        handleNormalMove(cup, board);
    }

    @Override
    protected void goJail(Board board) {
        String message = getName() + " has got into jail";

        // TODO: netowrk.announceMessage(message)
        Tile jailTile = board.getJailTile();
        // TODO network.movePlayer(this,jailTile);
        // TODO ask about using card to get out of jail
        // TODO: changeCurrentTile(jailTile);
        // TODO: network.changeJailStatus(this,true);

    }

    @Override
    public void jumpToTile(Tile newTile) {
        String previousTile = curTile.getName();
        // TODO network.changeCurrentTile(newTile)
        String message = getName() + " has transposed immediately from " + previousTile + " to " + curTile.getName();
        // TODO network.announceMessage(message)

    }

}
