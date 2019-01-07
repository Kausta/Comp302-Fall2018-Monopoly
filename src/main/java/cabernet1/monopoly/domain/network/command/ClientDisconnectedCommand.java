package cabernet1.monopoly.domain.network.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.player.IPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClientDisconnectedCommand extends ICommand {
    private static final long serialVersionUID = 1380460954304578921L;

    private final Set<String> disconnectedIdentifiers;

    public ClientDisconnectedCommand(Set<String> disconnectedIdentifiers) {
        this.disconnectedIdentifiers = disconnectedIdentifiers;
    }

    @Override
    public void execute() {
        Game game = Game.getInstance();
        List<IPlayer> players = game.getPlayers();
        if(players == null) {
            return;
        }
        players.forEach(player -> {
            if(disconnectedIdentifiers.contains(player.getOrigin())) {
                player.setOrigin("Server");
            }
        });
    }

    public Set<String> getDisconnectedIdentifiers() {
        return disconnectedIdentifiers;
    }
}
