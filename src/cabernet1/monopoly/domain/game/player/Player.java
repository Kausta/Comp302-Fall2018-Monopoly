package cabernet1.monopoly.domain.game.player;

import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.die.enumerators.NormalDiceCupStatus;
import cabernet1.monopoly.domain.game.die.util.NormalDiceCup;

public class Player extends IPlayer {
	public Player(String name, int money, int defaultOrder, Tile currentTile) {
		super(name, money, defaultOrder, currentTile);
	}

	@Override
	public void playTurn(NormalDiceCup cup, Board board) {
		NormalDiceCupStatus rollStatus = cup.rollCup();
		switch (rollStatus) {
		case normalMove:
			handleNormalMove();
			break;
		case DoubleMove:
			handleDoubleMove();
			break;
		case TripleMove:
			handleTriplesMove();
			break;
		case MrMonopolyMove:
			handleMrMonopolyMove();
			break;
		case BusMove:
			handleBusMove();
			break;
		}

	}

	@Override
	protected Tile handleNormalMove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Tile handleMrMonopolyMove() {
		// TODO implement handleMrMonopolyMove function
		return null;
	}

	@Override
	protected Tile handleBusMove() {
		// TODO implement handleBusIconMove function
		return null;
	}

	@Override
	protected Tile handleTriplesMove() {
		// TODO implement handleTriplesMove function
		return null;
	}

	@Override
	protected Tile handleDoubleMove() {
		// TODO handleDoubleMove function
		++numberOfConsecutiveDoublesRolls;
		if (numberOfConsecutiveDoublesRolls==3) {
			numberOfConsecutiveDoublesRolls=0;
		}
		return null;
	}
}
