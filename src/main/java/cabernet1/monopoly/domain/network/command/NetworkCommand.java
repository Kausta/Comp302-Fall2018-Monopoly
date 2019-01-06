package cabernet1.monopoly.domain.network.command;

import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

/**
 * Represents a command being sent in the network
 */
public class NetworkCommand extends ICommand {
    private static final long serialVersionUID = -5425404217326223597L;
    private final Logger logger = LoggerFactory.getInstance().getLogger(NetworkCommand.class);
    private final ICommand innerCommand;

    public NetworkCommand(ICommand innerCommand) {
        this.innerCommand = innerCommand;
    }

    @Override
    public void execute() {
        if (innerCommand != null) {
            try {
                innerCommand.execute();
            } catch (Exception ex) {
                logger.e("Error occurred while executing command: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public ICommand getInnerCommand() {
        return innerCommand;
    }
}
