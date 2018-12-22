package cabernet1.monopoly.domain.game.bot;

import cabernet1.monopoly.lib.persistence.Saveable;

import java.io.Serializable;

@Saveable
public class BotStrategyFactory implements Serializable {
    private static final long serialVersionUID = -5855733850792751579L;
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
