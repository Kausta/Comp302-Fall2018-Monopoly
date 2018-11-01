package cabernet1.monopoly.domain.bot;

import cabernet1.monopoly.domain.player.IPlayer;

public class BotPlayer extends IPlayer {
    private IStrategy botStrategy;

    public BotPlayer(String name,int money,int defaultOrder,IStrategy botStrategy) {
       super(name,money,defaultOrder);
       this.botStrategy = botStrategy;
    }

    @Override
    public void playTurn() {
        botStrategy.execute();
    }
}
