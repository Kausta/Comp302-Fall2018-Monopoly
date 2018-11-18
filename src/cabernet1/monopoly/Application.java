package cabernet1.monopoly;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;
import cabernet1.monopoly.ui.GameView;
import cabernet1.monopoly.utils.ViewUtils;

import javax.swing.*;
import java.io.IOException;

public class Application implements Runnable {
    private static Application _instance = null;
    private Logger logger = LoggerFactory.getInstance().getLogger(getClass());

    private Application() {
        logger.i("Created new Monopoly Instance");
    }

    public static synchronized Application getInstance() {
        if (_instance == null) {
            _instance = new Application();
        }
        return _instance;
    }

    public static void main(String[] args) {
        Application application = Application.getInstance();
        SwingUtilities.invokeLater(application);
    }

    @Override
    public void run() {
        logger.i("Running Monopoly");
        startInitialization();
    }

    public void startInitialization() {

    }

    public void startClient(String ip, int port) {
        try {
            Network.getInstance().initializeClient(ip, port);
        } catch (IOException e) {
            logger.e("Cannot start program");
        }
    }

    public void startServer(int port) {
        try {
            Network.getInstance().initializeServer(port);
        } catch (IOException e) {
            logger.e("Cannot start program");
        }
    }

    public void startGame() {
        Game game = Game.getInstance();
        GameController controller = game.getGameController();
        GameView view = GameView.getInstance();
        view.initialize(controller);
        ViewUtils.createWindowFromView(view);
    }
}

