package cabernet1.monopoly.domain.game.bot;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;

public class BuyPropertiesOnlyStrategy extends IStrategy {
    private static final long serialVersionUID = 1536209175918621910L;

    @Override
    public void handleBuyProperty(BotPlayer player) {
        Game game=Game.getInstance();
        game.getGameController().buyProperty();
        game.endTurn();
    }

    @Override
    public void handleUpgradeProperty(BotPlayer player) {
        Game game=Game.getInstance();
        game.getGameController().upgradeBuilding();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.endTurn();
    }
}
