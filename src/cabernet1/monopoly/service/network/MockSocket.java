package cabernet1.monopoly.service.network;

import cabernet1.monopoly.service.ICommand;
import cabernet1.monopoly.utils.Observer;

public class MockSocket extends BaseSocket implements INetworkFacade {
    @Override
    public void sendObject(ICommand command) {
        // Do nothing
    }

    @Override
    public void registerReceiver(Observer<ICommand> commandReceiver) {
        // Do nothing
    }
}
