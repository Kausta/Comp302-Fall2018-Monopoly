/*
 * TODO: add sellBuilding function
 * TODO: add mortgageProperty function
 */

package cabernet1.monopoly.domain.game.player;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.NetworkController;
import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.actiontile.ChanceTile;
import cabernet1.monopoly.domain.game.board.tile.actiontile.CommunityChestTile;
import cabernet1.monopoly.domain.game.board.tile.actiontile.Jail;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.property.Property;
import cabernet1.monopoly.domain.game.command.*;
import cabernet1.monopoly.domain.game.die.cup.JailDiceCup;
import cabernet1.monopoly.domain.game.die.cup.NormalDiceCup;
import cabernet1.monopoly.domain.game.die.enumerators.JailDiceCupStatus;
import cabernet1.monopoly.domain.game.die.enumerators.NormalDiceCupStatus;
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

    /**
     * simulates moving this player when playing a normal move
     *
     * @requires cupStatus of NormalDiceCup is NORMAL_MOVE
     * @modifies this.curTile, this.ownedProperty,this.money
     * @effects move the player to a newTile based on the sum of faceValues
     * on the dice, and handle the events of standing on the dest tile
     * (like pay rent, buying tile, upgrading building,draw card)
     * @see PlayerMovementStatus
     */
    protected void handleNormalMove() {
        logger.d("Handle Normal move accessed");
        Board board = Board.getInstance();
        NormalDiceCup cup = NormalDiceCup.getInstance();
        Tile newTile = board.getNextTile(curTile, direction, cup.getFacesValue(), cup.isEven());
        if (newTile==null){
            logger.d("ERROR ERROR ERROR: null tile accessed ");
        }
        NetworkController nc = Network.getInstance().getNetworkController();

        String previousTile = curTile.getName();
        String message = getName() + " has moved from " + previousTile + "  to " + newTile.getName();
        logger.d("Message in normal move "+message);
        nc.sendCommand(new AnnounceMessageCommand(message));
        nc.sendCommand(new MovePlayerCommand(this.getID(), newTile.getID(), cup.isEven()));
        logger.d("Handle Normal move finished");
    }

    /**
     * simulate moving this player when playing a Mr.Monopoly move
     *
     * @requires cupStatus of NormalDiceCup is MRMONOPOLY_MOVE
     * player already played the normal move part of the Mr.Monopoly move
     * @modifies this.inJail, this.curTile, this.ownedProperty,this.money
     * @effects move this player to the next unowned property if available,
     * if not, try to move to the next rent-able property
     * in either case: handle the events of standing on the dest tile
     * (like pay rent, buying tile, upgrading building,draw card)
     * if neither happened, stay in your place and do nothing
     * @see PlayerMovementStatus
     */
    protected void handleMrMonopolyMove() {
        Board board = Board.getInstance();
        NormalDiceCup cup = NormalDiceCup.getInstance();
        NetworkController nc = Network.getInstance().getNetworkController();

        Tile nextTile = board.nextUnownedProperty(curTile, direction, cup.isEven());
        String message;
        if (nextTile == null) {
            nextTile = board.nextRentableProperty(curTile, this, direction, cup.isEven());
            if (nextTile == null) {
                message = "No rentable Properties has been found on your way, hence you will stay in your place";
            } else {
                message = "You will move to the next rentable Property, namely " + nextTile.getName();
            }
        } else {
            message = "You will move to the next un owned Property, namely " + nextTile.getName();
        }
        nc.sendCommand(new AnnounceMessageCommand(message));

        if (nextTile != null) {
            nc.sendCommand(new MovePlayerCommand(this.getID(), nextTile.getID(), cup.isEven()));
            handleTile(nextTile, board);
        }
    }

    /**
     * simulate moving this player when playing a bus move
     * (speed die has bus icon shown on it)
     *
     * @requires cupStatus of NormalDiceCup is BUS_MOVE
     * player already played the normal move part of the bus move
     * @modifies this.inJail, this.curTile, this.ownedProperty,this.money
     * @effects move this player to the nearest community chest or chance tile
     * (which ever nearest), if available
     * if the nearest is a community chest tile: draw a card from the community chest cards, and act accordingly
     * if the nearest is a chance tile: draw a card from the chance cards, and act accordingly
     * if neither is available, stay in your place and do nothing
     * @see PlayerMovementStatus
     */
    protected void handleBusMove() {
        NetworkController nc = Network.getInstance().getNetworkController();
        Board board = Board.getInstance();
        NormalDiceCup cup = NormalDiceCup.getInstance();
        Tile nextTile = board.nextUnownedProperty(curTile, direction, cup.isEven());
        String message;
        if (nextTile == null) {
            message = "No community chest or chance card on your way, hence you will stay in your place";
            nc.sendCommand(new AnnounceMessageCommand(message));
        } else {
            if (nextTile.getTileType() == TileType.CommunityChest)
                message = "You will move to the next community chest tile";
            else
                message = "You will move to the next Chance tile";
            nc.sendCommand(new AnnounceMessageCommand(message));
            nc.sendCommand(new MovePlayerCommand(this.getID(), nextTile.getID(), cup.isEven()));
            handleTile(nextTile, board);
        }
    }

    /**
     * simulate moving this player when playing a DOUBLE move
     * (the two normal dice has the same number shown on their top faces)
     *
     * @requires cupStatus of NormalDiceCup is DOUBLE_MOVE
     * @modifies this.inJail, this.curTile, this.ownedProperty,this.money
     * @effects check if the player has played three Double moves in a row,
     * if so, move this player to jail, otherwise increase the
     * number of consecutive double moves, and play a normal move
     * @see PlayerMovementStatus
     */
    protected void handleDoubleMove() {
        NetworkController nc = Network.getInstance().getNetworkController();
        nc.sendCommand(new IncNumConsDoubleRollsCommand(this.getID()));
        ++numberOfConsecutiveDoublesRolls;
        /*if (numberOfConsecutiveDoublesRolls == 3) {
            goJail();
            return;
        }*/
        nc.sendCommand(new ChangeMovementStatusCommand(this.getID(), PlayerMovementStatus.DOUBLE_MOVE));
        handleNormalMove();
    }

    /**
     * handles the turn of this player, by rolling a dice and move accordingly
     *
     * @modifies this.numberOfConsecutiveDoublesRolls, this.PlayerMovementStatus,
     * this.inJail, this.curTile, this.ownedProperty,this.money
     * @effects simulate playing the turn for this player, by rolling the
     * cup, and moving based on its results, player might get into jail,
     * if he/she plays Double for the third time in row
     */
    public void playTurn() {
        logger.i("Turn of " + name);
        NormalDiceCup cup = NormalDiceCup.getInstance();
        NormalDiceCupStatus rollStatus = cup.rollCup();
        NetworkController nc = Network.getInstance().getNetworkController();
        GameController controller = Game.getInstance().getGameController();
        controller.showDiceValue();
        handleNormalMove();
        /*switch (rollStatus) {
            case NORMAL_MOVE:
                handleNormalMove();
                break;
            case DOUBLE_MOVE:
                incrementSteps();
                handleDoubleMove();
                break;
            case TRIPLE_MOVE:
                handleTriplesMove();
                break;
            case MR_MONOPOLY_MOVE:
                nc.sendCommand(new ChangeMovementStatusCommand(this.getID(), PlayerMovementStatus.MRMONOPOLY_MOVE));
                handleNormalMove();
                break;
            case BUS_MOVE:
                nc.sendCommand(new ChangeMovementStatusCommand(this.getID(), PlayerMovementStatus.BUS_MOVE));
                handleNormalMove();
                break;
        }*/
        if (getNumSteps() == 0) {
            controller.disableRollDice();
            controller.enableEndTurn();
        }

    }
    protected abstract void handleTriplesMove();
    // TODO: need to check building buying availability hear can check easier with owned properties
    public void playJailturn() {
        JailDiceCup cup = JailDiceCup.getInstance();
        JailDiceCupStatus rollStatus = cup.rollCup();
        NetworkController nc = Network.getInstance().getNetworkController();

        String message;
        switch (rollStatus) {
            case DOUBLES:
                message = getName() + " has got out of jail!";
                nc.sendCommand(new AnnounceMessageCommand(message));
                // TODO: handleDoubleMove for JailCup: handleJailDoubleMove();
                break;
            case NOT_DOUBLES:
                message = getName() + " has not rolled Doubles. Hence, he/she will stay in jail for this turn";
                nc.sendCommand(new AnnounceMessageCommand(message));
                break;
        }

    }

    /**
     * transport the player immediately to a given tile
     * that is, player doesn't pass by any other tile on the way
     *
     * @param newTile the new tile to move this player into
     * @requires newTile should belong to Board
     * @modifies this.inJail, this.curTile, this.ownedProperty,this.money
     * @effects moves the player immediately to newTile with passing through any
     * intermediary tiles, and handle the actions on landing on this tile
     * (like pay rent, buying tile, upgrading building,draw card)
     */
    public void jumpToTile(Tile newTile) {
        NetworkController nc = Network.getInstance().getNetworkController();
        String previousTile = curTile.getName();
        //TODO implement proper direction finder or special jump player function
        nc.sendCommand(new MovePlayerCommand(this.getID(), newTile.getID(), true));
        String message = getName() + " has transposed immediately from " + previousTile + " to " + curTile.getName();
        nc.sendCommand(new AnnounceMessageCommand(message));
        handleTile(newTile, Board.getInstance());
    }


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

    public void handleTile(Tile destTile, Board board) {
        logger.d("Accessed handle Tile");
        String message = "";
        NetworkController nc = Network.getInstance().getNetworkController();
        GameController controller = Game.getInstance().getGameController();
        if (destTile instanceof Property) {
            handleProperty((Property) destTile);
        } else if (destTile instanceof Jail) {
            message = getName() + " has got into jail!";
            nc.sendCommand(new ChangeJailStatusCommand(getID(), true));
        } else if (destTile instanceof ChanceTile) {
            ((ChanceTile) destTile).landingAction(this);
        } else if (destTile instanceof CommunityChestTile) {
            ((CommunityChestTile) destTile).landingAction(this);
        } else {// other types, do nothing for now
            message = getName() + " has nothing to do now,will take a rest";
        }
        if (this instanceof  Player) {
            controller.enableEndTurn();
        }else{
            controller.endTurn();
        }
        /*if (getMovementStatus() == PlayerMovementStatus.NORMAL_MOVE) {
            controller.enableSpecialAction();
        } else {
            controller.enableEndTurn();
        }*/

        if (!message.equals(""))
            nc.sendCommand(new AnnounceMessageCommand(message));
        logger.d("Finished  handle Tile");
    }

    public void handleProperty(Property property) {
        logger.d(("handle property accessed"));
        GameController controller = Game.getInstance().getGameController();
        NetworkController nc = Network.getInstance().getNetworkController();

        if (property.getOwner() == null && property.getPrice() < getMoney()) {
            handleBuyProperty();

        } else if (property.getOwner().equals(this)) {
            if (property instanceof GroupColoredProperty) {
                GroupColoredProperty gcp = (GroupColoredProperty) property;
                if (gcp.getUpgradeAmount() <= getMoney() && controller.canBeUpgraded(((GroupColoredProperty) property).getColorGroup(), ((GroupColoredProperty) property)))
                    handleUpgradeProperty();
            }
        } else {
            int rent = property.getRent();
            nc.sendCommand(new PayRentCommand(getID(), rent));
            nc.sendCommand(new GainMoneyCommand(property.getOwner().getID(), rent));
            String message = getName() + " has paid a rent to " + property.getOwner().getName();
            nc.sendCommand(new AnnounceMessageCommand(message));
        }
        logger.d(("handle property finished"));
    }
    public abstract void handleBuyProperty();
    public abstract void handleUpgradeProperty();
}
