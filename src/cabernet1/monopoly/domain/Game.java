package cabernet1.monopoly.domain;

public class Game {
    private static volatile Game _instance = null;
    private GameController controller;

    private Game() {

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
}
