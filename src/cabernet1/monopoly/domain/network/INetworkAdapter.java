package cabernet1.monopoly.domain.network;

import cabernet1.monopoly.domain.network.command.ICommand;
import cabernet1.monopoly.domain.network.command.NetworkCommand;
import cabernet1.monopoly.utils.Observer;

public interface INetworkAdapter {
    /**
     * Sends a command to the network socket
     * @param command
     */
    void sendCommand(ICommand command);

    /**
     * Called when a packet is received from network
     * @param observer
     */
    void onReceiveCommand(Observer<NetworkCommand> observer);
}
