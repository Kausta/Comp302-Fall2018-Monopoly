package cabernet1.monopoly.domain.player;

import cabernet1.monopoly.domain.bot.BotPlayer;
import cabernet1.monopoly.domain.bot.BotStrategyFactory;
import cabernet1.monopoly.domain.bot.IStrategy;

public class PlayerFactory {
    private static volatile PlayerFactory _instance = null;

    private PlayerFactory() {}

    public static synchronized PlayerFactory getInstance() {
        if(_instance == null) {
            _instance = new PlayerFactory();
        }
        return _instance;
    }

    // used to determine the current ID of the player
    private int numberOfInstances = 0;

    public IPlayer createNormalPlayer(String name, int money) {
        ++numberOfInstances;
        return new Player(name, money, numberOfInstances);
    }

    public IPlayer createBotPlayer(String name, int money) {
        ++numberOfInstances;
        IStrategy strategy = BotStrategyFactory.getInstance().createDefaultStrategy();
        return new BotPlayer(name, money, numberOfInstances, strategy);
    }
}
