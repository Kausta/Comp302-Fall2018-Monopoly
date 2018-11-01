package cabernet1.monopoly.domain.player;

import cabernet1.monopoly.domain.bot.BotPlayer;
import cabernet1.monopoly.domain.bot.BotStrategyFactory;
import cabernet1.monopoly.domain.bot.IStrategy;

public class PlayerFactory {
    private BotStrategyFactory botStrategyFactory;

    public PlayerFactory(BotStrategyFactory botStrategyFactory) {
        this.botStrategyFactory = botStrategyFactory;
    }

    public IPlayer createNormalPlayer() {
        return new Player();
    }

    public IPlayer createBotPlayer() {
        IStrategy strategy = botStrategyFactory.createDefaultStrategy();
        return new BotPlayer(strategy);
    }
}
