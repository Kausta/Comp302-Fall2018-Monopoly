/*
 * TODO: add endTurn function that calls the endTurn in the game
 */
package cabernet1.monopoly.domain;

import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.die.util.DiceCup;
import cabernet1.monopoly.domain.game.die.util.NormalDiceCup;
import cabernet1.monopoly.domain.game.player.Player;
import cabernet1.monopoly.domain.game.player.enumerators.PlayerMovementStatus;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

public class GameController {
	private Logger logger = LoggerFactory.getInstance().getLogger(getClass());

	public GameController() {
		logger.i("Created Game Controller");

	}
	public Player getCurrentPlayer() {
		return Game.getInstance().getCurrentPlayer();
	}
	public void announceMessage(String message) {
		// TODO implement the announceMessage method
		// call the announceMessage function in the UI using observer

	}

	public void chooseTile(Player player) {
		// TODO implement the chooseTile method
		// call the chooseTile method in the UI using observer
	}

	public void showDiceValue() {
		// TODO implement the showDiceValue method
		// call the showDiceValue method in the UI using observer
	}
	public void movePlayer(Player player, Tile newTile) {
		// TODO implement movePlayer method
		// call the movePlayer method in ui using observer
	}
	public void jumpToTile(Player player, Tile newTile) {
		player.jumpToTile(newTile);
	}

	public void changeCurrentTile(Player player, Tile newTile) {
		player.changeCurrentTile(newTile);
	}

	public void changeJailStatus(Player player, boolean inJail) {
		player.changeJailStatus(inJail);
	}

	public void changeMovementStatus(Player player, PlayerMovementStatus status) {
		player.setMovementStatus(status);
	}

	public void increaseNumberOfConsecutiveDoubleRolls(Player player) {
		player.increaseNumberOfConsecutiveDoublesRolls();
	}
	public void playTurn() {
		getCurrentPlayer().playTurn();
	}
	public void endTurn() {
		Game.getInstance().endTurn();
	}
	
	
}
