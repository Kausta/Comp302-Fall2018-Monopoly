package cabernet1.monopoly.domain.game.bot;

import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.player.IPlayer;

public class BotPlayer extends IPlayer {
    private static final long serialVersionUID = 8098497332098671670L;
    private final IStrategy botStrategy;

    public BotPlayer(String origin, int ID, String name, int money, int defaultOrder, Tile currentTile, IStrategy botStrategy) {
        super(origin, ID, name, money, defaultOrder, currentTile);
        this.botStrategy = botStrategy;
    }



    public IStrategy getBotStrategy() {
        return botStrategy;
    }



    @Override
    protected void handleMrMonopolyMove() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void handleBusMove() {
        // TODO Auto-generated method stub

    }

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

    @Override
    public void handleBuyProperty() {
        System.out.println("strategy called");
        botStrategy.handleBuyProperty(this);
    }

    @Override
    public void handleUpgradeProperty() {
        botStrategy.handleUpgradeProperty(this);
    }


}
