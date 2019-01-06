package cabernet1.monopoly.ui.players;

import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.ui.panels.BasePanel;
import cabernet1.monopoly.utils.Observer;
import cabernet1.monopoly.utils.animation.Animator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayersPanel extends BasePanel implements Observer<GameController.MovePlayerObservableInfo> {
    private static final double HEIGHT_COEFFICIENT = 8.25;
    private static final double WIDTH_COEFFICIENT = 8.25;
    private static volatile PlayersPanel _instance = null;
    private ArrayList<Player> players;

    private PlayersPanel() {
        setLayout(null);
        setBackground(new Color(212, 216, 221));
        //setBackground(new Color(212, 216, 0));
        // Adjusting the size of panel with the coefficients
        adjustSize(HEIGHT_COEFFICIENT, WIDTH_COEFFICIENT);

        initialize();
        setOpaque(false);
        //drawBoard("D:/gitLab/302_2018_project_cabarnet1/build/resources/main/player/player_1.png");
    }

    public static synchronized PlayersPanel getInstance() {
        if (_instance == null) {
            _instance = new PlayersPanel();
        }
        return _instance;
    }

    private void initialize() {
        Animator animator = new Animator();
        controller.movePlayerObservable.addObserver(this);
        logger.d("After adding observer");
        List<IPlayer> domainPlayerList = controller.playerList();
        int size = domainPlayerList.size();
        players = new ArrayList<>();
        for (IPlayer iPlayer : domainPlayerList) {
            Player player = new Player(iPlayer,animator);
            players.add(player);
            player.setBounds(0, 0, this.getWidth(), this.getHeight());
            this.add(player);
        }

        animator.setVisible(true);
    }

    private Player getPlayer(IPlayer domainPlayer) {
        for (Player player : players) {
            if (player.player.equals(domainPlayer))
                return player;
        }
        assert (false);
        return null;
    }


    @Override
    public void onValueChanged(GameController.MovePlayerObservableInfo value) {
        Player player = getPlayer(controller.getCurrentPlayer());
        if (player != null) {
            player.updatePath(value.tile, value.takeRailRoads);
        }
    }
}
