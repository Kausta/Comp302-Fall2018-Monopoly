package cabernet1.monopoly.domain.bot;

import cabernet1.monopoly.domain.board.Board;
import cabernet1.monopoly.domain.board.tile.Tile;
import cabernet1.monopoly.domain.die.IDie;
import cabernet1.monopoly.domain.player.IPlayer;

public class BotPlayer extends IPlayer {
    private IStrategy botStrategy;

    public BotPlayer(String name, int money, int defaultOrder,Tile currentTile, IStrategy botStrategy) {
        super(name, money, defaultOrder,currentTile);
        this.botStrategy = botStrategy;
    }

    @Override
    public void playTurn() {
        botStrategy.execute();
    }

    public IStrategy getBotStrategy() {
        return botStrategy;
    }

    public void setBotStrategy(IStrategy botStrategy) {
        this.botStrategy = botStrategy;
    }

	@Override
	public void playTurn(IDie[] dice, Board board) {
		// to be implemented later
	}

	@Override
	protected Tile handleNormalMove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Tile handleMrMonopolyMove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Tile handleBusIconMove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Tile handleTriplesMove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Tile handleDoubleMove() {
		// TODO Auto-generated method stub
		return null;
	}
}
