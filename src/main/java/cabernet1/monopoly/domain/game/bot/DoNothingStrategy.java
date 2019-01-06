package cabernet1.monopoly.domain.game.bot;

import cabernet1.monopoly.domain.Game;

public class DoNothingStrategy extends IStrategy {
    private static final long serialVersionUID = -7388946731441428492L;

    @Override
    public void handleBuyProperty(BotPlayer player) {
        //simply end the turn without doing anything
        Game.getInstance().endTurn();
    }
    @Override
    public void handleUpgradeProperty(BotPlayer player){
        //simply end the turn without doing anything
        Game.getInstance().endTurn();
    }
}
