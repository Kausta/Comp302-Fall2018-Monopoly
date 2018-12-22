package cabernet1.monopoly.domain.game.bot;

import cabernet1.monopoly.lib.persistence.Saveable;

@Saveable
public class BotStrategyFactory {
    private static volatile BotStrategyFactory _instance = null;

    private BotStrategyFactory() {
    }

    public static synchronized BotStrategyFactory getInstance() {
        if (_instance == null) {
            _instance = new BotStrategyFactory();
        }
        return _instance;
    }

    public IStrategy createDefaultStrategy() {
        return new DoNothingStrategy();
    }
}
