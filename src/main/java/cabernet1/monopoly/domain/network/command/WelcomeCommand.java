package cabernet1.monopoly.domain.network.command;

import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.NetworkController;

public class WelcomeCommand extends ICommand {
    private static final long serialVersionUID = 2309790474718010323L;
    private final String clientIdentifier;

    public WelcomeCommand(String clientIdentifier) {
        this.clientIdentifier = clientIdentifier;
    }

    public String getClientIdentifier() {
        return clientIdentifier;
    }

    @Override
    public void execute() {
        NetworkController controller = Network.getInstance().getNetworkController();
        ConnectedCommand command = new ConnectedCommand(clientIdentifier);
        controller.sendCommand(command);

        Network.getInstance().setIdentifier(clientIdentifier);
    }
}
