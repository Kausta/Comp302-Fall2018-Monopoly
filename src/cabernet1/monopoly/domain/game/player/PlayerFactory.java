package cabernet1.monopoly.domain.game.player;

import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.bot.BotPlayer;
import cabernet1.monopoly.domain.game.bot.BotStrategyFactory;
import cabernet1.monopoly.domain.game.bot.IStrategy;

public class PlayerFactory {
    private static volatile PlayerFactory _instance = null;
    // used to determine the current ID of the player
    private int numberOfInstances = 0;

    private PlayerFactory() {
    }

    public static synchronized PlayerFactory getInstance() {
        if (_instance == null) {
            _instance = new PlayerFactory();
        }
        return _instance;
    }

    public IPlayer createNormalPlayer(String name, int money, Tile startingTile) {
        ++numberOfInstances;
        return new Player(numberOfInstances, name, money, numberOfInstances, startingTile);
    }

    public IPlayer createBotPlayer(String name, int money, Tile startingTile) {
        ++numberOfInstances;
        IStrategy strategy = BotStrategyFactory.getInstance().createDefaultStrategy();
        return new BotPlayer(numberOfInstances, name, money, numberOfInstances, startingTile, strategy);
    }
}
