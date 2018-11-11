package cabernet1.monopoly.domain.game.player;

import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.die.enumerators.JailDiceCupStatus;
import cabernet1.monopoly.domain.game.die.enumerators.NormalDiceCupStatus;
import cabernet1.monopoly.domain.game.die.util.JailDiceCup;
import cabernet1.monopoly.domain.game.die.util.NormalDiceCup;

public class Player extends IPlayer {
    public Player(String name, int money, int defaultOrder, Tile currentTile) {
        super(name, money, defaultOrder, currentTile);
    }

    @Override
    public void playTurn(NormalDiceCup cup, Board board) {
        NormalDiceCupStatus rollStatus = cup.rollCup();
        //TODO announceMessage of the dice result to UI
        //TODO changeDiceFaces(die1: DieFaces,die2: DieFaces,die3: DieFaces)
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
                handleMrMonopolyMove(cup, board);
                break;
            case BUS_MOVE:
                handleBusMove(cup, board);
                break;
        }

    }

    @Override
    public void playJailturn(JailDiceCup cup, Board board) {
        JailDiceCupStatus rollStatus = cup.rollCup();
        switch (rollStatus) {
            case doubles:
                //TODO announce to UI that user goes out of Jail
                //TODO: handleDoubleMove for JailCup
                //handleDoubleMove(cup,board);
                break;
            case notDoubles:
                //TODO announce to UI that user still in jail
                break;
        }

    }

    @Override
    protected Tile handleNormalMove(NormalDiceCup cup, Board board) {
        Tile newTile = board.getNextTile(curTile, cup.getFacesValue());
        //TODO call the ICommand for changeCurrentTile(newTile)
        //TODO announceMessage of transportation
        //call interface.movePlayer
        return null;
    }

    @Override
    protected Tile handleMrMonopolyMove(NormalDiceCup cup, Board board) {
        // TODO implement handleMrMonopolyMove function
        return null;
    }

    @Override
    protected Tile handleBusMove(NormalDiceCup cup, Board board) {
        // TODO implement handleBusIconMove function
        return null;
    }

    @Override
    protected Tile handleTriplesMove(NormalDiceCup cup, Board board) {
        // TODO implement handleTriplesMove function
        // TODO call the interface.chooseTile observer
        return null;
    }


    @Override
    protected Tile handleDoubleMove(NormalDiceCup cup, Board board) {
        ++numberOfConsecutiveDoublesRolls;
        if (numberOfConsecutiveDoublesRolls == 3) {
            goJail(board);
            return board.getJailTile();
        }
        setLastMoveDouble(true);
        return handleNormalMove(cup, board);
    }

    @Override
    protected void goJail(Board board) {
        getInJail();
        numberOfConsecutiveDoublesRolls = 0;
        //TODO order the interface to moveTile
        //TODO ask about using card to get out of jail
        changeCurrentTile(board.getJailTile());
    }


    @Override
    public void jumpToTile(Tile newTile) {
        //changeCurrentTile(newTile);
        //TODO call the ICommand for changeCurrentTile(newTile)
        //TODO announceMessage of transportation

    }


}
