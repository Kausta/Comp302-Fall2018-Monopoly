package cabernet1.monopoly.domain.bot;

public class BotStrategyFactory {
    public IStrategy createDefaultStrategy() {
        return new DoNothingStrategy();
    }
}
