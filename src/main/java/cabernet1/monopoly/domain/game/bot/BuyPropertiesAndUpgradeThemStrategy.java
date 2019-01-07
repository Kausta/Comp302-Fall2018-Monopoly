package cabernet1.monopoly.domain.game.bot;

import cabernet1.monopoly.domain.Game;

public class BuyPropertiesAndUpgradeThemStrategy extends IStrategy {
    private static final long serialVersionUID = 2399626675453959221L;

    @Override
    public void handleBuyProperty(BotPlayer player) {
        System.out.println("strategy accessed");
        Game game=Game.getInstance();
        game.getGameController().buyProperty();

    }

    @Override
    public void handleUpgradeProperty(BotPlayer player) {
        Game game=Game.getInstance();
    }
}
