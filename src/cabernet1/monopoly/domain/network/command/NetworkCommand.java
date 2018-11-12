package cabernet1.monopoly.domain.network.command;

/**
 * Represents a command being sent in the network
 */
public class NetworkCommand implements ICommand {
    private ICommand innerCommand;

    public NetworkCommand(ICommand innerCommand) {
        this.innerCommand = innerCommand;
    }

    @Override
    public void execute() {
        if(innerCommand != null) {
            innerCommand.execute();
        }
    }

    public ICommand getInnerCommand() {
        return innerCommand;
    }
}
