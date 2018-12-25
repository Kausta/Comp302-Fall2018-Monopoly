/*
 * TODO: add endTurn function that calls the endTurn in the game
 */
package cabernet1.monopoly.domain;

import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.property.Property;
import cabernet1.monopoly.domain.game.die.RegularDie;
import cabernet1.monopoly.domain.game.die.SpeedDie;
import cabernet1.monopoly.domain.game.die.cup.NormalDiceCup;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.game.player.Player;
import cabernet1.monopoly.domain.game.player.enumerators.PlayerMovementStatus;
import cabernet1.monopoly.domain.network.command.PauseCommand;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;
import cabernet1.monopoly.utils.Observable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameController implements Serializable {
    private static final long serialVersionUID = 8999488415439250201L;
    // To add announcements to UI
    public final Observable<String> announcement = new Observable<>();
    public final Observable<Integer> die1Observable = new Observable<>();
    public final Observable<Integer> die2Observable = new Observable<>();
    public final Observable<Integer> speedDieObservable = new Observable<>();
    public final Observable<MovePlayerObservableInfo> movePlayerObservable = new Observable<>();
    public final Observable<Boolean> upgradeButton = new Observable<>();
    public final Observable<Boolean> buyButton = new Observable<>();
    public final Observable<Boolean> specialButton = new Observable<>();
    public final Observable<Boolean> endButton = new Observable<>();
    public final Observable<Boolean> rollButton = new Observable<>();
    public final Observable<Boolean> resumeButton = new Observable<>();
    public final Observable<Boolean> pauseButton = new Observable<>();
    public final Observable<Player> playerObserver = new Observable<>();
    public final Observable<ArrayList<IPlayer>> playerListObservable = new Observable<>();
    private final RegularDie die1 = NormalDiceCup.getInstance().die1;
    private final RegularDie die2 = NormalDiceCup.getInstance().die2;
    private final SpeedDie die3 = NormalDiceCup.getInstance().die3;
    private final Logger logger = LoggerFactory.getInstance().getLogger(getClass());

    public GameController() {
        logger.i("Created Game Controller");
    }

    public Player getCurrentPlayer() {
        return Game.getInstance().getCurrentPlayer();
    }

    public void announceMessage(String message) {
        announcement.setValue(message);
    }

    public void rollDice() {
        Player currentPlayer = getCurrentPlayer();
        if (currentPlayer.isInJail()) {
            currentPlayer.playJailturn();
        } else {
            currentPlayer.playTurn();
        }
        // showDiceValue();
    }

    private IPlayer getPlayer(int ID) {
        List<IPlayer> players = playerList();
        for (IPlayer player : players) {
            if (player.getID() == ID) {
                return player;
            }
        }
        throw new RuntimeException("Player with given ID not found!");
    }

    private Tile getTile(int ID) {
        return Board.getInstance().getTileById(ID);
    }

    public void chooseTile(Player player) {
        // TODO implement the chooseTile method
        // call the chooseTile method in the UI using observer
    }

    public void showDiceValue() {
        die1Observable.setValue(die1.getDiceValue().getValue());
        die2Observable.setValue(die2.getDiceValue().getValue());
        speedDieObservable.setValue(die3.speedDieValue());
    }

    public void movePlayer(int playerId, int newTileId, boolean takeRailRoads) {
        movePlayerObservable.setValue(new MovePlayerObservableInfo(getTile(newTileId), takeRailRoads));//make the command send the dice cup instead and calculate on all devices
        // make arrays of movePlayerObservable each
        getPlayer(playerId).setCurrentTile(getTile(newTileId));
    }

    public void jumpToTile(Player player, Tile newTile) {
        player.jumpToTile(newTile);
    }

    public void changeCurrentTile(Player player, Tile newTile) {
        player.setCurrentTile(newTile);
    }

    public void changeJailStatus(int playerId, boolean inJail) {
        getPlayer(playerId).changeJailStatus(inJail);
    }

    public void changeMovementStatus(int playerId, PlayerMovementStatus status) {
        getPlayer(playerId).setMovementStatus(status);
    }

    public void increaseNumberOfConsecutiveDoubleRolls(int playerId) {
        getPlayer(playerId).increaseNumberOfConsecutiveDoublesRolls();
    }

    public void playTurn() {
        getCurrentPlayer().playTurn();
    }

    public void playerPayRent(int playerId, int rentAmount) {
        getPlayer(playerId).payRent(rentAmount);
    }

    public void playerGainMoney(int playerId, int amount) {
        getPlayer(playerId).gainMoney(amount);
    }

    public void endTurn() {
        Game.getInstance().endTurn();
    }

    public void nextTurn() {
        Game.getInstance().nextTurn();
    }

    public void enableUpgradeBuilding() {
        upgradeButton.setValue(true);
    }

    public void upgradeBuilding() {
        Board.getInstance().upgradeBuilding(getCurrentPlayer(), (GroupColoredProperty) getCurrentPlayer().getCurrentTile());
    }

    public void buyProperty() {
        Board.getInstance().buyProperty(getCurrentPlayer(), (Property) getCurrentPlayer().getCurrentTile());
        playerListObservable.setValue(playerList());
    }

    //initial states are disabled.
    public void enableBuyProperty() {
        buyButton.setValue(true);
    }
    // All the enableX methods below are set to update the observer with "true" assuming the specified buttons'

    public void enableSpecialAction() {
        specialButton.setValue(true);

    }

    public void enableEndTurn() {
        endButton.setValue(true);

    }

    public void enableRollDice() {
        rollButton.setValue(true);
    }

    public void disableUpgradeBuilding() {
        rollButton.setValue(false);
    }

    public void disableBuyProperty() {
        buyButton.setValue(false);
    }

    public void disableSpecialAction() {
        specialButton.setValue(false);
    }

    public void disableEndTurn() {
        endButton.setValue(false);
    }

    public void disableRollDice() {
        rollButton.setValue(false);
    }

    public void playerInfo(Player player) {
        playerObserver.setValue(player);
    }

    public ArrayList<IPlayer> playerList() {
        return Game.getInstance().getPlayers();
    }

    public void increasePool(int amount) {
        Board.getInstance().getPool().addMoney(amount);
    }

    public void completeUpgradeBuilding(int propertyId) {
        ((GroupColoredProperty) getTile(propertyId)).upgrade();
    }

    public static class MovePlayerObservableInfo implements Serializable {
        private static final long serialVersionUID = 1495053868551960438L;
        public Tile tile;
        public boolean takeRailRoads;

        private MovePlayerObservableInfo(Tile tile, boolean takeRailRoads) {
            this.tile = tile;
            this.takeRailRoads = takeRailRoads;
        }
    }

    /**
     * Pauses the game via PauseCommand.
     * Restricts all user interactions on UI.
     */
    public void pauseGame() {
        Network.getInstance().getNetworkController().sendCommand(new PauseCommand());
    }
}
