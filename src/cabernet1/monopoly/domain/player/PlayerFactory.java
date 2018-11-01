package cabernet1.monopoly.domain.player;

import cabernet1.monopoly.domain.bot.BotPlayer;
import cabernet1.monopoly.domain.bot.BotStrategyFactory;
import cabernet1.monopoly.domain.bot.IStrategy;

public class PlayerFactory {
    private BotStrategyFactory botStrategyFactory;
    private int numberOfInstances; // used to determine the current ID of the player
    public PlayerFactory(BotStrategyFactory botStrategyFactory) {
        this.botStrategyFactory = botStrategyFactory;
    }

    public IPlayer createNormalPlayer(String name,int money) {
    	++numberOfInstances;
        return new Player(name,money,numberOfInstances);
    }

    public IPlayer createBotPlayer(String name,int money) {
    	++numberOfInstances;
        IStrategy strategy = botStrategyFactory.createDefaultStrategy();
        return new BotPlayer(name,money,numberOfInstances,strategy);
    }
}
