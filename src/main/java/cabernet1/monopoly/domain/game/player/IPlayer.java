/*
 * TODO: add payRent function
 * TODO: add playTurn function
 * TODO: add buyProperty function
 * TODO: add sellBuilding function
 * TODO: add buyBuilding function
 * TODO: add mortgageProperty function
 */

package cabernet1.monopoly.domain.game.player;

import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.property.Property;
import cabernet1.monopoly.domain.game.player.enumerators.PlayerMovementStatus;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the player in monopoly, which can be either normal
 * player or bot
 */
public abstract class IPlayer {
    //OVERVIEW: IPLayer is the abstract player of the monopoly game
    // a player can be either normal player, or a bot player
    private static final Logger logger = LoggerFactory.getInstance().getLogger(IPlayer.class);
    protected Tile curTile;
    protected int numberOfConsecutiveDoublesRolls;
    protected PlayerMovementStatus movementStatus;
    protected int ID;
    protected int direction;
    List<Property> ownedProperty;
    private String name;
    private int money;
    private boolean isActive;
    private int playerOrder;
    private boolean inJail;
    private int numberOfSteps = 1;

    /**
     * Class Constructor
     *
     * @param ID the ID of the player, unique for each player
     * @param name the name of the player
     * @param money the initial money
     * @param defaultOrder the initial order of playing, [1,N.Players]
     * @param currentTile the initial tile that stands on
     */
    public IPlayer(int ID, String name, int money, int defaultOrder, Tile currentTile) {
        //REQUIRES: ID to be unique for each player
        //MODIFIES: this.ID,this.name,this.money,this.isActive,this.playerOrder,
        //  this.curTile,this.numberOfConsecutiveDoublesRolls,this.inJail,
        //  this.ownedProperty,this.direction
        //EFFECTS: register the information of this player
        this.ID = ID;
        this.name = name;
        this.money = money;
        this.isActive = true;
        this.playerOrder = defaultOrder;
        this.curTile = currentTile;
        this.numberOfConsecutiveDoublesRolls = 0;
        this.inJail = false;
        this.ownedProperty = new ArrayList<>();
        this.direction = 1;
    }

    /**
     * Gets the movement status of this player, like normalMove,DoubleMove..
     *
     * @return the movement status of the current player
     */
    public PlayerMovementStatus getMovementStatus() {
        //EFFECTS: the movement status of the current player
        return movementStatus;
    }

    /**
     * Change the movement status of this player
     * used when the player plays a dice
     * @param newStatus the new movement status of this player
     */
    public void setMovementStatus(PlayerMovementStatus newStatus) {
        //MODIFIES: this.movementStatus
        //EFFECTS: change the current movement status of this player
        // to a new one
        this.movementStatus = newStatus;
    }

    /**
     * Increases the times the player has rolled Doubles
     * used to detect if player will go into jail due to three Double moves
     */
    public void increaseNumberOfConsecutiveDoublesRolls() {
        //MODIFIES: this.numberOfConsecutiveDoublesRolls
        //EFFECTS: track the number of doubles rolled by increasing its counter
        ++numberOfConsecutiveDoublesRolls;
    }

    /**
     * Checks if this player own a property
     * @param property the property to be checked
     * @return true if the player own the given property, false otherwise
     */
    public boolean isOwningProperty(Property property) {
        //EFFECTS: if property is owned by this player, return true,
        // otherwise return false

        for (Property prop : ownedProperty) {
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

    public Tile getCurrentTile() {
        return curTile;
    }

    public void setCurrentTile(Tile tile) {
        this.curTile = tile;
    }

    public int getID() {
        return ID;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    protected abstract void handleNormalMove();

    protected abstract void handleMrMonopolyMove();

    protected abstract void handleBusMove();

    protected abstract void handleTriplesMove();

    protected abstract void handleDoubleMove();

    public abstract void playJailturn();

    public abstract void jumpToTile(Tile newTile);


    /**
     * Checks if the given string holds an integer
     *    that is, can be parsed as integer
     * @param value the string variable to be checked
     * @return true if the string can be parsed as integer,
     *      false otherwise
     */
    protected boolean isInteger(String value) {
        //EFFECTS: returns true if value can be parsed to integer, and false otherwise
        try {
            Integer.valueOf(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void playTurn() {
        logger.i("Turn of " + name);
    }

    /**
     * Change the jail status of this player
     * (got into jail/got out of jail)
     * @param inJail the new status of this player, whether to become inJail(true)
     *               or get out of jail (false)
     */
    public void changeJailStatus(boolean inJail) {
        //MODIFIES: this.inJail,this.numberOfConsecutiveDoublesRolls
        //EFFECTS: change the current jail status of the player(becomes in jail,
        //         got out of jail, and reset the numberOfConsecutiveDoublesRolls
        //         if he/she gets in jail
        this.inJail = inJail;
        if (inJail)
            numberOfConsecutiveDoublesRolls = 0;
    }

    protected abstract void goJail();

    public void payRent(int amountOfMoney) {
        if (money >= amountOfMoney) {
            money -= amountOfMoney;
        } else {

            // TODO: handle selling houses and mortgage property
        }
    }

    /**
     * Increase this player money by the amount given
     * @param amountOfMoney the amount of money to add to this player money
     */
    public void gainMoney(int amountOfMoney) {
        //REQUIRES: amountOfMoney>=0
        //MODIFIES: this.money
        //EFFECTS: increase this player money by amountOfMoney
        money += amountOfMoney;
    }
    /**
     * Decrease this player money by the amount given
     * @param amountOfMoney the amount of money to subtract from this player money
     */
    public void loseMoney(int amountOfMoney) {
        //REQUIRES: amountOfMoney>=0, money>=amountOfMoney
        //MODIFIES: this.money
        //EFFECTS: decrease this player money by amountOfMoney
        money -= amountOfMoney;
    }

    /**
     * Checks if this player in jail
     *
     * @return true if this player is in jail, false otherwise
     */
    public boolean isInJail() {
        //EFFECTS: returns true if this player in jail, and false otherwise
        return inJail;
    }

    public int getNumSteps() {
        return this.numberOfSteps;
    }

    public void incrementSteps() {
        numberOfSteps++;
    }

    public void resetSteps() {
        numberOfSteps = 1;
    }
}
