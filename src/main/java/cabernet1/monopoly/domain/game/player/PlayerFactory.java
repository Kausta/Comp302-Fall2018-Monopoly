package cabernet1.monopoly.domain.game.player;

import cabernet1.monopoly.domain.game.Constants;
import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.bot.BotPlayer;
import cabernet1.monopoly.domain.game.bot.BotStrategyFactory;
import cabernet1.monopoly.domain.game.bot.IStrategy;
import cabernet1.monopoly.lib.persistence.Saveable;

import java.io.Serializable;

@Saveable
public class PlayerFactory implements Serializable {
    private static final long serialVersionUID = -6221995932713839216L;
    private static volatile PlayerFactory _instance = null;

    private int numberOfInstances = 0;

    private PlayerFactory() {
    }

    public static synchronized PlayerFactory getInstance() {
        if (_instance == null) {
            _instance = new PlayerFactory();
        }
        return _instance;
    }

    public IPlayer createFromInitialData(InitialPlayerData playerData) {
        if (playerData.isBotPlayer() || true) {
            return this.createBotPlayer(playerData.getId(), playerData.getName(), Constants.INITIAL_MONEY, Board.getInstance().getInitialTile());
        } else {
            return this.createNormalPlayer(playerData.getId(), playerData.getName(), Constants.INITIAL_MONEY, Board.getInstance().getInitialTile());
        }
    }

    public IPlayer createNormalPlayer(int id, String name, int money, Tile startingTile) {
        ++numberOfInstances;
        return new Player(id, name, money, id, startingTile);
    }

    public IPlayer createBotPlayer(int id, String name, int money, Tile startingTile) {
        ++numberOfInstances;
        IStrategy strategy = BotStrategyFactory.getInstance().createDefaultStrategy();
        return new BotPlayer(id, name, money, id, startingTile, strategy);
    }
}
