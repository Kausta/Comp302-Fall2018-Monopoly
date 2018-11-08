package cabernet1.monopoly.domain.player;

import cabernet1.monopoly.domain.board.Board;
import cabernet1.monopoly.domain.board.tile.Tile;
import cabernet1.monopoly.domain.die.IDie;

public class Player extends IPlayer {
    public Player(String name, int money, int defaultOrder,Tile currentTile) {
        super(name, money, defaultOrder,currentTile);
    }
    
	@Override
	public void playTurn(IDie[] dice, Board board) {
		assert(dice.length==3);
		
		String diceValues[]=new String[dice.length];
		for (IDie die:dice) {
			die.rollDice();
			String value=die.getDiceValue();
		}
		String status=determineDiceStatus(diceValues);
		
		
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
