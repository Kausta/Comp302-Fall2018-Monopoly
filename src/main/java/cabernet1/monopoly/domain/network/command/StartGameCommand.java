package cabernet1.monopoly.domain.network.command;

import cabernet1.monopoly.Application;
import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.game.player.InitialPlayerData;

import java.util.List;
import java.util.Map;

public class StartGameCommand extends ICommand {
    private static final long serialVersionUID = -4525676647561793235L;
    private final List<InitialPlayerData> players;
    private final Map<String, String> clientNames;
    private final Map<String, String> clientIdentifiers;

    public StartGameCommand(List<InitialPlayerData> players, Map<String, String> clientNames, Map<String, String> clientIdentifiers) {
        this.players = players;
        this.clientNames = clientNames;
        this.clientIdentifiers = clientIdentifiers;
    }

    @Override
    public void execute() {
        Network.getInstance().setAllClientNames(clientNames);
        Network.getInstance().setClientsForRecovery(clientIdentifiers);
        Game.getInstance().initialize(players);
        Application.getInstance().startGame();
    }
}
