package cabernet1.monopoly.domain.player;

import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

public abstract class IPlayer {
    private static final Logger logger = LoggerFactory.getInstance().getLogger(IPlayer.class);
    private String name;
    private int money;
    private boolean isActive;
    private int playerOrder;

    public IPlayer(String name, int money, int defaultOrder) {
        this.name = name;
        this.money = money;
        this.isActive = true;
        this.playerOrder = defaultOrder;
    }

    public void playTurn() {
        logger.i("Turn of " + name);
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getPlayerOrder() {
        return playerOrder;
    }
}
