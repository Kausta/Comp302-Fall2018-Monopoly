package cabernet1.monopoly.domain.player;

import cabernet1.monopoly.domain.board.Board;
import cabernet1.monopoly.domain.board.tile.Tile;
import cabernet1.monopoly.domain.die.IDie;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

public abstract class IPlayer {
    private static final Logger logger = LoggerFactory.getInstance().getLogger(IPlayer.class);
    private String name;
    private int money;
    private boolean isActive;
    private int playerOrder;
    private Tile curTile;
    private int numberOfConsecutiveDoublesRolls;
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
    
    /*
     * rolling dice status related methods 
     */
    protected boolean isDoubles(String diceValues[]) {
    	// when determining doubles, only the first two dice are consider
    	return diceValues[0].equals(diceValues[1]);
    }
    protected boolean isTriples(String diceValues[]) {
    	return diceValues[0].equals(diceValues[1]) && diceValues[1].equals(diceValues[2]);
    }
    protected boolean isMrMonopolyMove(String diceValues[]) {
    	return diceValues[2]=="Mr.Monopoly";
    }
    protected boolean isBusMove(String diceValues[]) {
    	return diceValues[2]=="BusIcon";
    }
    protected String determineDiceStatus(String diceValues[]) {
    	if (isTriples(diceValues))
    		return "Triples";
    	if (isDoubles(diceValues))
    		return "Doubles";
    	if (diceValues[2].equals("Mr.Monopoly"))
    		return "Mr.Monopoly";
    	if (diceValues[2].equals("BusIcon"))
    		return "BusIcon";
    	
    	return "non";
    }
    
    
    protected abstract Tile handleNormalMove();
    protected abstract Tile handleMrMonopolyMove();
    protected abstract Tile handleBusIconMove();
    protected abstract Tile handleTriplesMove();
    protected abstract Tile handleDoubleMove();
    
    public abstract void playTurn(IDie[] dice,Board board);
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
