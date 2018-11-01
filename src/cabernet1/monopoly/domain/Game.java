package cabernet1.monopoly.domain;

import cabernet1.monopoly.domain.player.PlayerFactory;
import cabernet1.monopoly.domain.bot.BotStrategyFactory;

public class Game {
    private static volatile Game _instance = null;
    private GameController controller;
    private PlayerFactory playerFactory;
    private BotStrategyFactory botStrategyFactory;

    private Game() {

    }

    public static synchronized Game getInstance() {
        if (_instance == null) {
            _instance = new Game();
        }
        return _instance;
    }

    public synchronized GameController getGameController() {
        if(controller == null) {
            PlayerFactory playerFactory = getPlayerFactory();
            controller = new GameController(playerFactory);
        }
        return controller;
    }

    public synchronized PlayerFactory getPlayerFactory() {
        if (playerFactory == null) {
            BotStrategyFactory factory = getBotStrategyFactory();
            playerFactory = new PlayerFactory(factory);
        }
        return playerFactory;
    }

    public synchronized BotStrategyFactory getBotStrategyFactory() {
        if (botStrategyFactory == null) {
            botStrategyFactory = new BotStrategyFactory();
        }
        return botStrategyFactory;
    }
}
