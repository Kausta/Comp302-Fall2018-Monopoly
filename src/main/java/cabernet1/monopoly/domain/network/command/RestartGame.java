package cabernet1.monopoly.domain.network.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.ui.ContainerView;
import cabernet1.monopoly.ui.GameView;

import java.util.List;
import java.util.Set;

public class RestartGame extends ICommand {
    private static final long serialVersionUID = -398091769800515809L;

    private final Set<String> connectedClients;

    public RestartGame(Set<String> connectedClients) {
        this.connectedClients = connectedClients;
    }

    @Override
    public void execute() {
        List<IPlayer> players = Game.getInstance().getPlayers();
        if(players != null) {
            players.forEach(player -> {
                if(!connectedClients.contains(player.getOrigin())) {
                    player.setOrigin("Server");
                }
            });
        }
        ContainerView.getInstance().setCurrentView(GameView.getInstance());
    }
}
