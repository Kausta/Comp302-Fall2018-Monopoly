package cabernet1.monopoly.domain;

public class Game {
    private static volatile Game _instance = null;
    private GameController controller;

    private Game() {
        this.controller = new GameController();
    }

    public static synchronized Game getInstance() {
        if (_instance == null) {
            _instance = new Game();
        }
        return _instance;
    }

    public GameController getGameController() {
        return controller;
    }
}
