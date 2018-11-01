package cabernet1.monopoly.domain;

import cabernet1.monopoly.domain.player.Player;
import cabernet1.monopoly.domain.player.PlayerFactory;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

public class GameController {
    private Logger logger = LoggerFactory.getInstance().getLogger(getClass());
    private PlayerFactory playerFactory;

    public GameController(PlayerFactory playerFactory) {
        logger.i("Created Game Controller");
        this.playerFactory = playerFactory;
    }
}
