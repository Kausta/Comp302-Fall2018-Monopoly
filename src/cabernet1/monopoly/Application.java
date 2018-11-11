package cabernet1.monopoly;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;
import cabernet1.monopoly.ui.GameView;
import cabernet1.monopoly.utils.ViewUtils;

import javax.swing.*;

public class Application implements Runnable {
    private Logger logger = LoggerFactory.getInstance().getLogger(getClass());

    public Application() {
        logger.i("Created new Monopoly Instance");
    }

    public static void main(String[] args) {
        Application application = new Application();
        SwingUtilities.invokeLater(application);
    }

    @Override
    public void run() {
        logger.i("Running Monopoly");

        Game game = Game.getInstance();
        GameController controller = game.getGameController();
        GameView view = GameView.getInstance();
        view.initialize(controller);
        ViewUtils.createWindowFromView(view);
    }
}
