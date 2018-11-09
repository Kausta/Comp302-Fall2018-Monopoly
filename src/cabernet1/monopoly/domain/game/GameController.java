package cabernet1.monopoly.domain.game;

import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

public class GameController {
    private Logger logger = LoggerFactory.getInstance().getLogger(getClass());

    public GameController() {
        logger.i("Created Game Controller");

    }
}
