package cabernet1.monopoly;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.InitializationController;
import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;
import cabernet1.monopoly.ui.ContainerView;
import cabernet1.monopoly.ui.GameView;
import cabernet1.monopoly.ui.InitializationView;
import cabernet1.monopoly.utils.ViewUtils;

import javax.swing.*;
import java.io.IOException;

public class Application implements Runnable {
    private static volatile Application _instance = null;
    private final Logger logger = LoggerFactory.getInstance().getLogger(getClass());

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
        InitializationView initializationView = InitializationView.getInstance();
        initializationView.initialize(InitializationController.getInstance());

        ContainerView containerView = ContainerView.getInstance();
        containerView.setCurrentView(initializationView);
        ViewUtils.createWindowFromView(containerView);
    }

    public boolean startClient(String clientName, String ip, int port) {
        try {
            Network.getInstance().initializeClient(clientName, ip, port);
            return true;
        } catch (IOException e) {
            logger.e("Cannot start program");
            return false;
        }
    }

    public boolean startServer(int port) {
        try {
            Network.getInstance().initializeServer(port);
            return true;
        } catch (IOException e) {
            logger.e("Cannot start program");
            return false;
        }
    }

    public void startGame() {
        logger.i("Starting the actual game");
        Game game = Game.getInstance();
        game.startGame();
        GameController controller = game.getGameController();
        GameView view = GameView.getInstance();
        logger.i("Initializing game view");
        view.initialize(controller);
        logger.i("Navigating to game view");
        ContainerView.getInstance().setCurrentView(view);
        game.configureTurn();
    }
}

