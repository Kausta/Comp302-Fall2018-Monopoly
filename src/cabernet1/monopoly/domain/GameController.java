/*
 * TODO: add endTurn function that calls the endTurn in the game
 */
package cabernet1.monopoly.domain;

import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;
import cabernet1.monopoly.utils.Observable;

public class GameController {
    private Logger logger = LoggerFactory.getInstance().getLogger(getClass());

    // To add announcements to UI
    public Observable<String> announcement = new Observable<>();

    public GameController() {
        logger.i("Created Game Controller");

    }

}
