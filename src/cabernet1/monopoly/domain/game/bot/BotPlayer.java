package cabernet1.monopoly.domain.game.bot;

import cabernet1.monopoly.domain.game.board.tile.Tile;
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

    @Override
    protected void handleNormalMove() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void handleMrMonopolyMove() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void handleBusMove() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void handleTriplesMove() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void handleDoubleMove() {
        // TODO Auto-generated method stub

    }

    @Override
    public void playJailturn() {
        // TODO Auto-generated method stub

    }

    @Override
    public void jumpToTile(Tile newTile) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void goJail() {
        // TODO Auto-generated method stub

    }


}
