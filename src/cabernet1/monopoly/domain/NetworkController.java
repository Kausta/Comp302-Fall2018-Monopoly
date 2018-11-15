package cabernet1.monopoly.domain;

import cabernet1.monopoly.domain.network.INetworkAdapter;
import cabernet1.monopoly.domain.network.command.ICommand;
import cabernet1.monopoly.domain.network.command.NetworkCommand;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;
import cabernet1.monopoly.utils.Observer;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Controller for handling sending and receiving commands
 */
public class NetworkController implements Observer<NetworkCommand> {
    private Logger logger = LoggerFactory.getInstance().getLogger(getClass());
    /**
     * Thread pool for executing network related commands in another thread
     */
    private ScheduledThreadPoolExecutor threadPool = new ScheduledThreadPoolExecutor(1);
    private INetworkAdapter adapter;

    public NetworkController(INetworkAdapter adapter) {
        logger.i("Created Network Controller");
        this.adapter = adapter;
        this.adapter.onReceiveCommand(this);
    }

    /**
     * Send command first sends the command throughout the network, it doesn't execute it locally because server will resend it
     * So, the command will be executed when the server accepts and resends it
     *
     * @param command
     */
    public void sendCommand(ICommand command) {
        // Don't send network commands to the network again, they already come from the network
        if (!(command instanceof NetworkCommand)) {
            NetworkCommand commandToSend = new NetworkCommand(command);
            scheduleNetworkCommand(commandToSend);
        }
    }

    /**
     * Executes the command received from the network
     *
     * @param command
     */
    @Override
    public void onValueChanged(NetworkCommand command) {
        // Execute the received command
        command.execute();
    }

    private void scheduleNetworkCommand(NetworkCommand command) {
        threadPool.execute(() -> adapter.sendCommand(command));
    }
}
