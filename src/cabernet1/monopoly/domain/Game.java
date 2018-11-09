package cabernet1.monopoly.domain;

import java.util.ArrayList;
import java.util.List;

import cabernet1.monopoly.domain.board.Board;
import cabernet1.monopoly.domain.die.util.NormalDiceCup;
import cabernet1.monopoly.domain.die.util.RollThreeDiceCup;
import cabernet1.monopoly.domain.player.Player;

public class Game {
    private static volatile Game _instance = null;
    private GameController controller;
    
    private Board gameBoard;
    private List<Player> player;
    private NormalDiceCup normalCup;
    private RollThreeDiceCup rollThreeCup;
    
    private Game() {
    	initialization();
    }
    private void initialization() {
    	gameBoard=new Board();
    	player=new ArrayList<>();
    	normalCup=new NormalDiceCup();
    	rollThreeCup=new RollThreeDiceCup();
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
    	//TODO: implement endTurn function
    }
}
