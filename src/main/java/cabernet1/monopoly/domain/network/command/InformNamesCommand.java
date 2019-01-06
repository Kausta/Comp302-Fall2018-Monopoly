package cabernet1.monopoly.domain.network.command;

import cabernet1.monopoly.domain.InitializationController;
import cabernet1.monopoly.domain.network.initial.InitialPlayerInfo;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

import java.util.List;

public class InformNamesCommand extends ICommand {
    private final static Logger logger = LoggerFactory.getInstance().getLogger(InformNamesCommand.class);
    private static final long serialVersionUID = 9197415461653787882L;

    private final String clientIdentifier;
    private final List<InitialPlayerInfo> playerNames;

    public InformNamesCommand(String clientIdentifier, List<InitialPlayerInfo> playerNames) {
        this.clientIdentifier = clientIdentifier;
        this.playerNames = playerNames;
    }

    public String getClientIdentifier() {
        return clientIdentifier;
    }

    public List<InitialPlayerInfo> getPlayerNames() {
        return playerNames;
    }

    @Override
    public void execute() {
        playerNames.forEach(name -> logger.i("Player " + name + " joined from " + clientIdentifier));
        InitializationController.getInstance().initializePlayerNamesFor(clientIdentifier, playerNames);
    }
}
