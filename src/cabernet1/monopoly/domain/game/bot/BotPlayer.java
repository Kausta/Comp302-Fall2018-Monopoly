package cabernet1.monopoly.domain.game.bot;

import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.die.util.JailDiceCup;
import cabernet1.monopoly.domain.game.die.util.NormalDiceCup;
import cabernet1.monopoly.domain.game.player.IPlayer;

public class BotPlayer extends IPlayer {
    private IStrategy botStrategy;

    public BotPlayer(int ID, String name, int money, int defaultOrder, Tile currentTile, IStrategy botStrategy) {
        super(ID, name, money, defaultOrder, currentTile);
        this.botStrategy = botStrategy;
    }

    @Override
    public void playTurn() {

    }

    public IStrategy getBotStrategy() {
        return botStrategy;
    }

    public void setBotStrategy(IStrategy botStrategy) {
        this.botStrategy = botStrategy;
    }

    @Override
    public void playTurn(NormalDiceCup dice, Board board) {
        botStrategy.execute();
        //TODO implement bot-playTurn
    }


    @Override
    protected void goJail(Board board) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playJailturn(JailDiceCup cup, Board board) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void handleNormalMove(NormalDiceCup cup, Board board) {
        // TODO Auto-generated method stub
    }

    @Override
    protected void handleMrMonopolyMove(NormalDiceCup cup, Board board) {
        // TODO Auto-generated method stub
    }

    @Override
    protected void handleBusMove(NormalDiceCup cup, Board board) {
        // TODO Auto-generated method stub
    }

    @Override
    protected void handleTriplesMove(NormalDiceCup cup, Board board) {
        // TODO Auto-generated method stub
    }

    @Override
    protected void handleDoubleMove(NormalDiceCup cup, Board board) {
        // TODO Auto-generated method stub
    }

    @Override
    public void jumpToTile(Tile newTile) {
        // TODO Auto-generated method stub

    }
}
