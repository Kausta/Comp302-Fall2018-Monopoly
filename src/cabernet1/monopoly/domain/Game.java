package cabernet1.monopoly.domain;

import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.die.util.NormalDiceCup;
import cabernet1.monopoly.domain.game.die.util.RollThreeDiceCup;
import cabernet1.monopoly.domain.game.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private static volatile Game _instance = null;
	private GameController controller;

	private List<Player> player;

	private Game() {
		initialization();
	}

	private void initialization() {
		player = new ArrayList<>();
		
		// TODO coordinate with Caner to see how to start the game with how many players
		// and which ones on which devices
		// then instantiate them and add them to the player list
		// call configureTurn to start the turn
	}

	public static synchronized Game getInstance() {
		if (_instance == null) {
			_instance = new Game();
		}
		return _instance;
	}

	public synchronized GameController getGameController() {
		if (controller == null) {
			controller = new GameController();
		}
		return controller;
	}

	public void endTurn() {
		// TODO implement the endTurn method
		/*
		 * - checks which player is playing - check if in jail, end the move, and
		 * announce that in ui - get the status of the player - if normal move, just
		 * finish the turn, and announce that in ui - if bus move, call
		 * player.busMove(normalCup,board) - if mr monopoly move, call
		 * player.handleMrMonopolyMove(normalCup,board) - if double move, call
		 * player.playTurn(normalCup,board)
		 */
	}

	public void configureTurn() {
		//TODO implement configureTurn method
		
		// call the showPlayerInfo(player) on the UI using observer

	}

	public void nextTurn() {
		// TODO implement nextTurn method
		// this method should know the next player
		//then it should call configureTurn
	}

	public Player getCurrentPlayer() {
		// TODO implement getCurrentPlayer method
		// you should return the current player
		// build a mechanism that dealing with the current player
		return null;
	}
}
