package cabernet1.monopoly.domain.game.bot;

import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.die.util.NormalDiceCup;
import cabernet1.monopoly.domain.game.player.IPlayer;

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
	public void playTurn(NormalDiceCup dice, Board board) {
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
	protected Tile handleBusMove() {
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
