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
import cabernet1.monopoly.ui.panels.*;
import cabernet1.monopoly.ui.scrollpanes.LogScrollPane;
import cabernet1.monopoly.ui.tabbedpanes.DetailsTabbedPane;
import cabernet1.monopoly.utils.ResourceManager;

import javax.swing.*;
import java.awt.*;

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
        // We have one big panel which covers the whole frame.
        this.root = new JPanel();

        root.setBackground(new Color(212, 216, 221));

        // Getting the path of the board image
        String boardImage = ResourceManager.getInstance().getResourcePath("board_small.png").getPath();

        // Board panel covers the left side of the frame.
        BoardPanel bP = BoardPanel.getInstance(boardImage);

        // Right panel covers the right side of the frame.
        // In addition, it contains some other panels
        RightPanel rP = RightPanel.getInstance();
        rP.add(MouseOverPanel.getInstance(), BorderLayout.NORTH);
        rP.add(DetailsTabbedPane.getInstance(), BorderLayout.NORTH);
        rP.add(LogScrollPane.getInstance(), BorderLayout.NORTH);
        rP.add(ActionPanel.getInstance(), BorderLayout.NORTH);

        // Adding BoardPanel and RightPanel to our one big panel -which acts as frame in our case-.
        this.root.add(bP, BorderLayout.WEST);
        this.root.add(rP, BorderLayout.EAST);

        logger.d("Loading board from " + boardImage);
    }

    @Override
    public JPanel getRoot() {
        return this.root;
    }
}
