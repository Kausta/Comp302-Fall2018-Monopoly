package cabernet1.monopoly.domain.network;

import cabernet1.monopoly.domain.network.command.ICommand;
import cabernet1.monopoly.utils.Observable;

public class MockSocket extends BaseSocket implements INetworkAdapter {
    private Observable<ICommand> commandObservable = new Observable<>();

    @Override
    public void sendCommand(ICommand command) {
        // Send to socket
    }
}
