/*
 * TODO: add payRent function
 * TODO: add playTurn function
 * TODO: add buyProperty function
 * TODO: add sellBuilding function
 * TODO: add buyBuilding function
 * TODO: add mortgageProperty function
 */

package cabernet1.monopoly.domain.game.player;

import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.die.util.NormalDiceCup;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

public abstract class IPlayer {
    private static final Logger logger = LoggerFactory.getInstance().getLogger(IPlayer.class);
    private String name;
    private int money;
    private boolean isActive;
    private int playerOrder;
    private Tile curTile;
    protected int numberOfConsecutiveDoublesRolls;
    private boolean inJail;


    public IPlayer(String name, int money, int defaultOrder,Tile currentTile) {
        this.name = name;
        this.money = money;
        this.isActive = true;
        this.playerOrder = defaultOrder;
        this.curTile=currentTile;
        this.numberOfConsecutiveDoublesRolls=0;
        this.inJail=false;
    }
    public void changeCurrentTile(Tile newTile) {
    	this.curTile=newTile;
    }

    public void playTurn() {
        logger.i("Turn of " + name);
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





    protected abstract Tile handleNormalMove();
    protected abstract Tile handleMrMonopolyMove();
    protected abstract Tile handleBusMove();
    protected abstract Tile handleTriplesMove();
    protected abstract Tile handleDoubleMove();

    public abstract void playTurn(NormalDiceCup dice,Board board);
    /*
     * helper method for playTurn
     */
    protected boolean isInteger(String value) {
    	try {
    		Integer.valueOf(value);
    		return true;
    	}catch(NumberFormatException e) {
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
    	inJail=true;
    }

}
