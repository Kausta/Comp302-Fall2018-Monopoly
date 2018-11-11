/*
 * TODO add moveToTile function 
 * 		this function move the current player to the selected tile
 * TODO add chooseTile function
 * 		this function order the user to choose a tile to go to(for TRIPLES move)
 */
package cabernet1.monopoly.ui;

import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;
import cabernet1.monopoly.utils.ResourceManager;

import javax.swing.*;
import java.net.URL;

public class GameView extends BaseView {
    private static volatile GameView _instance = null;
    private Logger logger = LoggerFactory.getInstance().getLogger(getClass());
    private GameController controller;
    private JPanel root;

    private GameView() {

    }

    public static synchronized GameView getInstance() {
        if (_instance == null) {
            _instance = new GameView();
        }
        return _instance;
    }

    public void initialize(GameController controller) {
        this.controller = controller;
        this.initializeUI();
        logger.i("Initialized Game UI");
    }

    private void initializeUI() {
        this.root = new JPanel();

        URL boardImage = ResourceManager.getInstance().getResourcePath("board.png");
        logger.d("Loading board from " + boardImage);
    }

    @Override
    public JPanel getRoot() {
        return this.root;
    }
}
