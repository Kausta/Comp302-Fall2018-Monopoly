/*
 * TODO: add payRent function
 * TODO: add playTurn function
 * TODO: add buyProperty function
 * TODO: add sellBuilding function
 * TODO: add buyBuilding function
 * TODO: add mortgageProperty function
 */

package cabernet1.monopoly.domain.game.player;

import java.util.ArrayList;
import java.util.List;

import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.property.Property;
import cabernet1.monopoly.domain.game.die.util.JailDiceCup;
import cabernet1.monopoly.domain.game.die.util.NormalDiceCup;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

public abstract class IPlayer {
	private static final Logger logger = LoggerFactory.getInstance().getLogger(IPlayer.class);
	private String name;
	private int money;
	private boolean isActive;
	private int playerOrder;
	protected Tile curTile;
	protected int numberOfConsecutiveDoublesRolls;
	private boolean inJail;
	protected boolean lastMoveDouble;
	protected int ID;
	
	List<Property> ownedProperty;
	public boolean isLastMoveDouble() {
		return lastMoveDouble;
	}

	protected void setLastMoveDouble(boolean lastMoveDouble) {
		this.lastMoveDouble = lastMoveDouble;
	}

	public IPlayer(int ID, String name, int money, int defaultOrder, Tile currentTile) {
		this.ID=ID;
		this.name = name;
		this.money = money;
		this.isActive = true;
		this.playerOrder = defaultOrder;
		this.curTile = currentTile;
		this.numberOfConsecutiveDoublesRolls = 0;
		this.inJail = false;
		this.ownedProperty=new ArrayList<>();
	}
	public boolean isOwningProperty(Property property) {
		for (Property prop:ownedProperty) {
			if (prop.equals(property))
				return true;
		}
		return false;
	}
	public void ownProperty(Property property) {
		ownedProperty.add(property);
		
	}
	public void changeCurrentTile(Tile newTile) {
		this.curTile = newTile;
		
	}

	public void playTurn() {
		logger.i("Turn of " + name);
	}
	public int getID() {
		return ID;
	}
	public String getName() {
		return name;
	}

	public int getMoney() {
		return money;
	}

	public boolean isActive() {
		return isActive;
	}

	public int getPlayerOrder() {
		return playerOrder;
	}

	protected abstract Tile handleNormalMove(NormalDiceCup cup,Board board);

	protected abstract Tile handleMrMonopolyMove(NormalDiceCup cup,Board board);

	protected abstract Tile handleBusMove(NormalDiceCup cup,Board board);

	protected abstract Tile handleTriplesMove(NormalDiceCup cup,Board board);

	protected abstract Tile handleDoubleMove(NormalDiceCup cup,Board board);
	
	public abstract void playTurn(NormalDiceCup cup, Board board);
	public abstract void playJailturn(JailDiceCup cup, Board board);
	public abstract void jumpToTile(Tile newTile);
	/*
	 * helper method for playTurn
	 */
	protected boolean isInteger(String value) {
		try {
			Integer.valueOf(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/*
	 * Jail related methods
	 */
	public boolean isInJail() {
		return inJail;
	}

	public void getInJail() {
		inJail = true;
	}

	protected abstract void goJail(Board board);
	public void payRent(int amountOfMoney) {
		if (money>=amountOfMoney) {
			money-=amountOfMoney;
		}else {
			
			//TODO: handle selling houses and morgage property
		}
	}
}
