package cabernet1.monopoly.domain.network.command;

import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

public class ConnectedCommand extends ICommand {
    private final static Logger logger = LoggerFactory.getInstance().getLogger(ConnectedCommand.class);
    private static final long serialVersionUID = 7299121351564742343L;

    private final String clientIdentifier;

    public ConnectedCommand(String clientIdentifier) {
        this.clientIdentifier = clientIdentifier;
    }

    public String getClientIdentifier() {
        return clientIdentifier;
    }

    @Override
    public void execute() {
        logger.i("Client " + clientIdentifier + " connected");
    }
}
