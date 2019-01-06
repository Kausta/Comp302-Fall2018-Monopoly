/*
 * TODO add moveToTile function
 * 		this function move the current player to the selected tile
 * TODO add chooseTile function
 * 		this function order the user to choose a tile to go to(for TRIPLES move)
 */
package cabernet1.monopoly.ui;

import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;
import cabernet1.monopoly.ui.panels.*;
import cabernet1.monopoly.ui.players.PlayersPanel;
import cabernet1.monopoly.ui.scrollpanes.LogScrollPane;
import cabernet1.monopoly.ui.tabbedpanes.DetailsTabbedPane;
import cabernet1.monopoly.utils.ResourceManager;

import javax.swing.*;
import java.awt.*;

public class GameView extends BaseView {
    private static volatile GameView _instance = null;
    private final Logger logger = LoggerFactory.getInstance().getLogger(getClass());
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
        JPanel insidePanel = bP.insidePanel;
        PlayersPanel pP = PlayersPanel.getInstance();

        pP.setBounds(0, 0, insidePanel.getWidth(), insidePanel.getHeight());
        insidePanel.add(pP, 0);

        // Right panel covers the right side of the frame.
        // In addition, it contains some other panels
        RightPanel rP = RightPanel.getInstance();
        rP.add(DicePanel.getInstance(),BorderLayout.NORTH);
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

    public void showPlayerInfo(IPlayer player) {
        //TODO showPlayerInfo method
        // this method should represent the starting turn
        // all info in the ui should be updated to be for that player
        // all the buttons except rollDice button should be disabled
    }

    public void rollDice() {
        //TODO: call this function when the rollDice button is clicked
        controller.rollDice();
    }

    public void chooseTile(IPlayer player) {
        //TODO chooseTile method
        // command the player (currently playing) to choose a tile
        // call controller.jumpToTile(player,newSelectedTile)
    }


    public void endTurn() {
        controller.endTurn();
    }

    public void enableUpgradeBuildingButton() {
        //TODO implement enableUpgradeBuildingButtons method
        // enable the "Upgrade Building button" (create one)

    }

    public void upgradeBuilding() {
        //TODO implement upgradeBuilding method
        // this method should be called when the upgrade button is pressed
        // call controller.upgradeBuilding
    }

    public void enableBuyPropertyButton() {
        //TODO implement enableBuyPropertyButtons method
        // enable the "Buy Property button" (create one)

    }

    public void buyBuilding() {
        //TODO implement buyBuilding method
        // this method should be called when the buy button is pressed
        // call controller.buyBuilding
    }

    public void enableSpecialActionButton() {
        // TODO implement enableSpecialActionButton button
        // enable that button
    }

    public void enableEndTurnButton() {
        // TODO implement enableEndTurnButton button
        // enable that button
    }
}
