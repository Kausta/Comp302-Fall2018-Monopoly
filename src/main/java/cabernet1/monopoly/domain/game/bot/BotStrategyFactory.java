package cabernet1.monopoly.domain.game.bot;

import cabernet1.monopoly.lib.persistence.Saveable;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

import java.io.Serializable;

@Saveable
public class BotStrategyFactory implements Serializable {
    private static final long serialVersionUID = -5855733850792751579L;
    private static volatile BotStrategyFactory _instance = null;
    private final Logger logger= LoggerFactory.getInstance().getLogger(getClass());
    private BotStrategyFactory() {
    }

    public static synchronized BotStrategyFactory getInstance() {
        if (_instance == null) {
            _instance = new BotStrategyFactory();
        }
        return _instance;
    }

    public IStrategy createDefaultStrategy() {
        return new BuyPropertiesAndUpgradeThemStrategy();
    }
    public IStrategy createStrategy(String strat){
        if (strat.equals("easy")){
            return new DoNothingStrategy();
        }
        if (strat.equals("normal")){
            return new BuyPropertiesOnlyStrategy();
        }
        if (strat.equals("hard")){
            return new BuyPropertiesAndUpgradeThemStrategy();
        }
        logger.d("Error, no suitable bot strategy found");
        return new BuyPropertiesAndUpgradeThemStrategy();
    }
}
