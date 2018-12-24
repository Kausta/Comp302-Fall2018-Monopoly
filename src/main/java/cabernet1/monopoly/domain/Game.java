package cabernet1.monopoly.domain;

import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.command.AnnounceMessageCommand;
import cabernet1.monopoly.domain.game.command.NextTurnCommand;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.game.player.InitialPlayerData;
import cabernet1.monopoly.domain.game.player.Player;
import cabernet1.monopoly.domain.game.player.PlayerFactory;
import cabernet1.monopoly.lib.persistence.Saveable;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Saveable
public class Game implements Serializable {
    private static final long serialVersionUID = -3452240765331746220L;
    private static volatile Game _instance = null;
    private final Logger logger = LoggerFactory.getInstance().getLogger(getClass());
    private GameController controller;
    private ArrayList<IPlayer> player;
    private int playerPointer = 0;

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
        this.player = initialPlayerData.stream().map(playerData -> {
            logger.i("Registered " + playerData.getName());
            return PlayerFactory.getInstance().createFromInitialData(playerData);
        }).collect(toCollection(ArrayList::new));
    }

    public synchronized GameController getGameController() {
        if (controller == null) {
            controller = new GameController();
        }
        return controller;
    }

    public void endTurn() {
        String message = getCurrentPlayer().getName() + " turn has ended";
        NetworkController nc = Network.getInstance().getNetworkController();
        nc.sendCommand(new AnnounceMessageCommand(message));
        nc.sendCommand(new NextTurnCommand());
    }

    public void nextTurn() {
        playerPointer = (playerPointer + 1) % player.size();
        configureTurn();
    }

    public void configureTurn() {
        controller.playerInfo(getCurrentPlayer());
    }

    public Player getCurrentPlayer() {
        return (Player) player.get(playerPointer);
    }

    public ArrayList<IPlayer> getPlayers() {
        return this.player;
    }

}
