package cabernet1.monopoly.domain.network.command;

import cabernet1.monopoly.domain.Network;

public class ClientNameCommand extends ICommand {
    private static final long serialVersionUID = -7826280135657506815L;

    private final String clientName;
    private final String clientIdentifier;

    public ClientNameCommand(String clientName, String clientIdentifier) {
        this.clientName = clientName;
        this.clientIdentifier = clientIdentifier;
    }

    @Override
    public void execute() {
        Network.getInstance().addClientIdentifier(clientName, clientIdentifier);
    }
}
