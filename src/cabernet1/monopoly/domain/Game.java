package cabernet1.monopoly.domain;

import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.Pool;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.game.player.Player;
import cabernet1.monopoly.domain.game.player.PlayerFactory;
import cabernet1.monopoly.domain.game.player.InitialPlayerData;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class Game {
	private static volatile Game _instance = null;
	private Logger logger = LoggerFactory.getInstance().getLogger(getClass());
	private GameController controller;
	private List<InitialPlayerData> initialPlayerData;
	private List<IPlayer> player;

	private Game() {
		// Manually initialize the board here
		Board board = Board.getInstance();
		board.initialize();
	}

	public static synchronized Game getInstance() {
		if (_instance == null) {
			_instance = new Game();
		}
		return _instance;
	}

	public void initialize(List<InitialPlayerData> initialPlayerData) {
		logger.i("Registering players to the game");
		this.initialPlayerData = initialPlayerData;
		this.player = initialPlayerData.stream()
				.map(playerData -> {
					logger.i("Registered " + playerData.getName());
					return PlayerFactory.getInstance().createFromInitialData(playerData);
				})
				.collect(Collectors.toList());
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
