package cabernet1.monopoly.domain.network.command;

import cabernet1.monopoly.domain.InitializationController;
import cabernet1.monopoly.domain.Network;

import java.util.List;

public class SetPlayersCommand extends ICommand {
    private static final long serialVersionUID = 2652773731900771898L;
    private final String clientIdentifier;
    private final List<String> playerNames;

    public SetPlayersCommand(String clientIdentifier, List<String> playerNames) {
        this.clientIdentifier = clientIdentifier;
        this.playerNames = playerNames;
    }

    public String getClientIdentifier() {
        return clientIdentifier;
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }

    @Override
    public void execute() {
        boolean isServer = Network.getInstance().isServerMode();
        if (!isServer) {
            return;
        }
        InitializationController.getInstance().initializePlayerNamesFor(clientIdentifier, playerNames);
    }
}
