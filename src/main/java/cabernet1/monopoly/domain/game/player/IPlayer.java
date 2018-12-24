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
import cabernet1.monopoly.utils.RepresentationInvariant;

import java.io.Serializable;
import java.util.HashSet;

/**
 * This class represents the player in monopoly, which can be either normal
 * player or bot
 */
public abstract class IPlayer implements RepresentationInvariant, Serializable {
    //OVERVIEW: IPLayer is the abstract player of the monopoly game
    // a player can be either normal player, or a bot player
    private static final Logger logger = LoggerFactory.getInstance().getLogger(IPlayer.class);
    private static final long serialVersionUID = 7190182886453386040L;
    protected final int ID;
    final boolean direction;
    final HashSet<Property> ownedProperty;
    private final String name;
    private final boolean isActive;
    private final int playerOrder;
    protected Tile curTile;
    protected int numberOfConsecutiveDoublesRolls;
    protected PlayerMovementStatus movementStatus;
    private int money;
    private boolean inJail;
    private int numberOfSteps = 1;

    /**
     * Class Constructor
     *
     * @param ID           the ID of the player, unique for each player
     * @param name         the name of the player
     * @param money        the initial money
     * @param defaultOrder the initial order of playing, [1,N.Players]
     * @param currentTile  the initial tile that stands on
     * @requires ID to be unique for each player
     * @modifies this.ID, this.name, this.money, this.isActive, this.playerOrder,
     * this.curTile,this.numberOfConsecutiveDoublesRolls,this.inJail,
     * this.ownedProperty,this.direction
     * @effects register the information of this player
     */
    public IPlayer(int ID, String name, int money, int defaultOrder, Tile currentTile) {
        this.ID = ID;
        this.name = name;
        this.money = money;
        this.isActive = true;
        this.playerOrder = defaultOrder;
        this.curTile = currentTile;
        this.numberOfConsecutiveDoublesRolls = 0;
        this.inJail = false;
        this.ownedProperty = new HashSet<>();
        this.direction = true;
        this.movementStatus = PlayerMovementStatus.NORMAL_MOVE;
    }

    @Override
    public String toString() {
        return "IPlayer{" +
                "ID: " + ID +
                ", name: " + name +
                ", money: " + money +
                ", order: " + playerOrder +
                ", Active: " + isActive +
                ", inJail: " + inJail +
                ", N.Owned Properties" + ownedProperty.size() +
                "}";
    }

    /**
     * Gets the movement status of this player, like normalMove,DoubleMove..
     *
     * @return the movement status of the current player
     * @effects the movement status of the current player
     */
    public PlayerMovementStatus getMovementStatus() {
        return movementStatus;
    }

    /**
     * Change the movement status of this player
     * used when the player plays a dice
     *
     * @param newStatus the new movement status of this player
     * @modifies this.movementStatus
     * @effects change the current movement status of this player to a new one
     */
    public void setMovementStatus(PlayerMovementStatus newStatus) {
        this.movementStatus = newStatus;
    }

    public boolean getDirection() {
        return direction;
    }

    /**
     * Increases the times the player has rolled Doubles
     * used to detect if player will go into jail due to three Double moves
     *
     * @modifies this.numberOfConsecutiveDoublesRolls
     * @effects track the number of doubles rolled by increasing its counter
     */
    public void increaseNumberOfConsecutiveDoublesRolls() {
        ++numberOfConsecutiveDoublesRolls;
    }

    /**
     * gets the number of consecutive double rolls this player has player until now
     *
     * @return numberOfConsecutiveDoublesRolls
     * @effects gets the number of consecutive double rolls this player has player until now
     */
    public int getNumberOfConsecutiveDoublesRolls() {
        return numberOfConsecutiveDoublesRolls;
    }

    /**
     * Checks if this player own a property
     *
     * @param property the property to be checked
     * @return true if the player own the given property, false otherwise
     * @effects if property is owned by this player, return true
     * otherwise return false
     */
    public boolean isOwningProperty(Property property) {
        return ownedProperty.contains(property);
    }

    /**
     * Own the property by the player
     *
     * @param property the property to be added
     * @effects Add a property to the list properties owned by this player
     */
    public void ownProperty(Property property) {
        ownedProperty.add(property);
    }

    /**
     * gets the current tile that the player is standing on
     *
     * @return the current standing tile of this player
     * @effects gets the current tile that the player is standing on
     */
    public Tile getCurrentTile() {
        return curTile;
    }

    /**
     * Change the current Tile that the player standing on
     *
     * @param newTile the new tile that player stands on
     * @effects change the current tile of this player to a given new tile
     */
    public void setCurrentTile(Tile newTile) {
        this.curTile = newTile;
    }

    /**
     * Gets the ID of this player
     *
     * @return the ID if this player
     * @effects returns the ID of this player
     */
    public int getID() {
        return ID;
    }

    /**
     * Gets the money that this player has
     *
     * @return the money of this player
     * @effects return the money that this player has
     */
    public int getMoney() {
        return money;
    }

    /**
     * Gets the name of this player
     *
     * @return the name of this player
     * @effects return the name of this player
     */
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
     * that is, can be parsed as integer
     *
     * @param value the string variable to be checked
     * @return true if the string can be parsed as integer,
     * false otherwise
     * @effects returns true if value can be parsed to integer, and false otherwise
     */
    protected boolean isInteger(String value) {
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
     *
     * @param inJail the new status of this player, whether to become inJail(true)
     *               or get out of jail (false)
     * @modifies this.inJail, this.numberOfConsecutiveDoublesRolls
     * @effects change the current jail status of the player(becomes in jail,
     * got out of jail, and reset the numberOfConsecutiveDoublesRolls
     * if he/she gets in jail
     */
    public void changeJailStatus(boolean inJail) {
        this.inJail = inJail;
        if (inJail)
            numberOfConsecutiveDoublesRolls = 0;
    }

    protected abstract void goJail();

    /**
     * Pay the rent causing by this player standing on a square owned by another player
     *
     * @param amountOfMoney the amount of money to add to this player money
     * @modifies this.money
     * @effects decrease the money with amount equal to the amount given,
     * and sell buildings and properties if the money is not enough
     */
    public void payRent(int amountOfMoney) {
        if (money >= amountOfMoney) {
            loseMoney(amountOfMoney);
        } else {
            int moneyLeftToPay = amountOfMoney - money;
            // losing all the money
            loseMoney(money);
            // TODO: handle selling houses and mortgage property
        }
    }

    /**
     * Increase this player money by the amount given
     *
     * @param amountOfMoney the amount of money to add to this player money
     * @requires amountOfMoney>=0
     * @modifies this.money
     * @effects increase this player money by amountOfMoney
     */
    public void gainMoney(int amountOfMoney) {
        money += amountOfMoney;
    }

    /**
     * Decrease this player money by the amount given
     *
     * @param amountOfMoney the amount of money to subtract from this player money
     * @requires amountOfMoney>=0, money>=amountOfMoney
     * @modifies this.money
     * @effects decrease this player money by amountOfMoney
     */
    public void loseMoney(int amountOfMoney) {
        money -= amountOfMoney;
    }

    /**
     * Checks if this player in jail
     *
     * @return true if this player is in jail, false otherwise
     * @effects returns true if this player in jail, and false otherwise
     */
    public boolean isInJail() {
        return inJail;
    }

    /**
     * Checks if this player in active (still playing)
     *
     * @return true if this player is active
     * @effects returns true if this player hasn't resigned or got into bankrupt
     */
    public boolean isActive() {
        return isActive;
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

    public boolean repOK() {
        boolean res = curTile != null;
        res &= ID >= 0;
        res &= movementStatus != null;
        res &= name != null && !name.equals("");
        res &= money >= 0;

        res &= ownedProperty != null;
        return res;
    }

    public int xShift() {
        switch (ID) {
            case 0:
            case 1:
            case 2:
                return 0;
            case 3:
            case 4:
            case 5:
                return 1;
            case 6:
            case 7:
            case 8:
            default:
                return -1;
        }
    }

    public int yShift() {
        switch (ID) {
            case 0:
            case 3:
            case 6:
                return 0;
            case 1:
            case 4:
            case 7:
                return 1;
            case 2:
            case 5:
            case 8:
            default:
                return -1;
        }
    }
}
