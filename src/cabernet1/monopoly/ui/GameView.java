package cabernet1.monopoly.ui;

import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

import javax.swing.*;

public class GameView extends BaseView {
    private static volatile GameView _instance = null;
    private Logger logger = LoggerFactory.getInstance().getLogger(getClass());
    private GameController controller;
    private JPanel root;

    private GameView() {
        // I know this is not a right way to use the git, but I'm just testing something. So, please don't kill me Caner :/
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
    }

    @Override
    public JPanel getRoot() {
        return this.root;
    }
}
