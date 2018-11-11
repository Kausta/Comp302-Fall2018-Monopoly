package cabernet1.monopoly.domain.bot;

import cabernet1.monopoly.domain.board.Board;
import cabernet1.monopoly.domain.board.tile.Tile;
import cabernet1.monopoly.domain.die.IDie;
import cabernet1.monopoly.domain.die.util.JailDiceCup;
import cabernet1.monopoly.domain.die.util.NormalDiceCup;
import cabernet1.monopoly.domain.player.IPlayer;

public class BotPlayer extends IPlayer {
	private IStrategy botStrategy;

	public BotPlayer(String name, int money, int defaultOrder, Tile currentTile, IStrategy botStrategy) {
		super(name, money, defaultOrder, currentTile);
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
	protected Tile handleNormalMove(NormalDiceCup cup, Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Tile handleMrMonopolyMove(NormalDiceCup cup, Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Tile handleBusMove(NormalDiceCup cup, Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Tile handleTriplesMove(NormalDiceCup cup, Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Tile handleDoubleMove(NormalDiceCup cup, Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void jumpToTile(Tile newTile) {
		// TODO Auto-generated method stub
		
	}
}
