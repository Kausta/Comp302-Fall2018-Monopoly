package cabernet1.monopoly.domain;

import cabernet1.monopoly.domain.network.INetworkAdapter;
import cabernet1.monopoly.domain.network.command.ICommand;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

public class NetworkController {
    private Logger logger = LoggerFactory.getInstance().getLogger(getClass());

    private INetworkAdapter adapter;

    public NetworkController(INetworkAdapter adapter) {
        logger.i("Created Network Controller");
        this.adapter = adapter;
    }

    /**
     * Send command first sends the command throughout the network, then also executes it in this local instance
     * @param command
     */
    public void sendCommand(ICommand command) {
        adapter.sendCommand(command);
        command.execute();
    }
}
