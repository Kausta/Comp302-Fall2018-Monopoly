package cabernet1.monopoly.domain.network;

import cabernet1.monopoly.service.ICommand;
import cabernet1.monopoly.utils.Observable;
import cabernet1.monopoly.utils.Observer;

public class MockSocket extends BaseSocket implements INetworkFacade {
    private Observable<ICommand> commandObservable = new Observable<>();

    @Override
    public void sendObject(ICommand command) {
        // Send to socket
    }

    @Override
    public void registerReceiver(Observer<ICommand> commandReceiver) {
        commandObservable.addObserver(commandReceiver);
        // Command observable will get the value when the server sends it
    }
}
