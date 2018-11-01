package cabernet1.monopoly.domain.bot;

import cabernet1.monopoly.domain.player.IPlayer;

public class BotPlayer extends IPlayer {
    private IStrategy botStrategy;

    public BotPlayer(IStrategy botStrategy) {
        this.botStrategy = botStrategy;
    }

    @Override
    public void startTurn() {
        botStrategy.execute();
    }
}
