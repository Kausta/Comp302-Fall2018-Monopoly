/*
 * TODO: add endTurn function that calls the endTurn in the game
 */
package cabernet1.monopoly.domain;

import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.Pool;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.property.Property;
import cabernet1.monopoly.domain.game.die.RegularDie;
import cabernet1.monopoly.domain.game.die.SpeedDie;
import cabernet1.monopoly.domain.game.die.util.NormalDiceCup;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.game.player.Player;
import cabernet1.monopoly.domain.game.player.enumerators.PlayerMovementStatus;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;
import cabernet1.monopoly.utils.Observable;

import java.util.List;

public class GameController {
	private Logger logger = LoggerFactory.getInstance().getLogger(getClass());
	// To add announcements to UI
    public Observable<String> announcement = new Observable<>();

    public Observable<Integer> die1Observeable = new Observable<>();
    public Observable<Integer> die2Observeable = new Observable<>();
    public Observable<Integer> speedDieObserveable = new Observable<>();

    public Observable<Tile> movePlayerObserveable = new Observable<>();

    public Observable<Boolean> upgradeButton = new Observable<>();
	public Observable<Boolean> buyButton = new Observable<>();
	public Observable<Boolean> specialButton = new Observable<>();
	public Observable<Boolean> endButton = new Observable<>();
	public Observable<Boolean> rollButton = new Observable<>();

	public Observable<Player> playerObserver = new Observable<>();
	public Observable<List<IPlayer>> playerListObservable = new Observable<>();


	RegularDie die1 = NormalDiceCup.getInstance().die1;
    RegularDie die2 = NormalDiceCup.getInstance().die2;
    SpeedDie die3 = NormalDiceCup.getInstance().die3;


	public GameController() {
		logger.i("Created Game Controller");
	}

	public Player getCurrentPlayer() {
		return Game.getInstance().getCurrentPlayer();
	}

	public void announceMessage(String message) {
		announcement.setValue(message);
	}

	public void rollDice(){
		Player currentPlayer = getCurrentPlayer();
		if (currentPlayer.isInJail()) {
			currentPlayer.playJailturn();
		} else {
			currentPlayer.playTurn();
		}
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
		}
		playerListObservable.setValue(playerList());
		// showDiceValue();
	}

	public void chooseTile(Player player) {
		// TODO implement the chooseTile method
		// call the chooseTile method in the UI using observer
	}

	public void showDiceValue() {
		die1Observeable.setValue(die1.getDiceValue().getValue());
		die2Observeable.setValue(die2.getDiceValue().getValue());
		speedDieObserveable.setValue(die3.speedDieValue());
	}

	public void movePlayer(IPlayer player, Tile newTile) {
		movePlayerObserveable.setValue(newTile);
		player.setCurrentTile(newTile);
	}

	public void jumpToTile(Player player, Tile newTile) {
		player.jumpToTile(newTile);
	}

	public void changeCurrentTile(Player player, Tile newTile) {
		player.changeCurrentTile(newTile);
	}

	public void changeJailStatus(IPlayer player, boolean inJail) {
		player.changeJailStatus(inJail);
	}

	public void changeMovementStatus(IPlayer player, PlayerMovementStatus status) {
		player.setMovementStatus(status);
	}

	public void increaseNumberOfConsecutiveDoubleRolls(IPlayer player) {
		player.increaseNumberOfConsecutiveDoublesRolls();
	}
	public void playTurn() {
		getCurrentPlayer().playTurn();
	}

	public void playerPayRent(IPlayer player, int rentAmount) {
		player.payRent(rentAmount);
	}
	public void playerGainMoney(IPlayer player, int amount) {
		player.gainMoney(amount);
	}
	public void endTurn() {
		Game.getInstance().endTurn();
	}
	public void enableUpgradeBuilding() {
		upgradeButton.setValue(true);
	}

	public void UpgradeBuilding() {
		Board.getInstance().upgradeBuilding(getCurrentPlayer(),(GroupColoredProperty) getCurrentPlayer().getCurrentTile());
	}
	public void buyProperty() {
		Board.getInstance().buyProperty(getCurrentPlayer(),(Property) getCurrentPlayer().getCurrentTile());
		playerListObservable.setValue(playerList());
	}
	// All the enableX methods below are set to update the observer with "true" assuming the specified buttons'

	//initial states are disabled.
	public void enableBuyProperty() {
		buyButton.setValue(true);
	}
	public void enableSpecialAction() {
		specialButton.setValue(true);
		
	}
	public void enableEndTurn() {
		endButton.setValue(true);
		
	}

	public void enableRollDice(){
		rollButton.setValue(true);
	}

	public void disableUpgradeBuilding(){
		rollButton.setValue(false);
	}

	public void disableBuyProperty(){
		buyButton.setValue(false);
	}

	public void disableSpecialAction(){
		specialButton.setValue(false);
	}

	public void disableEndTurn(){
		endButton.setValue(false);
	}

	public void disableRollDice(){
		rollButton.setValue(false);
	}

	public void playerInfo(Player player){
		playerObserver.setValue(player);
	}

	public List<IPlayer> playerList(){
		return Game.getInstance().getPlayers();
	}
	
	public void increasePool(int amount) {
		Board.getInstance().getPoolTile().addMoney(amount);
		
	}

	public void completeUpgradeBuilding(GroupColoredProperty property) {
		property.upgrade();
		
	}
}
