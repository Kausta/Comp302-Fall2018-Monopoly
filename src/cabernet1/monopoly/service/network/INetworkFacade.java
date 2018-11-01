package cabernet1.monopoly.service.network;

import cabernet1.monopoly.service.ICommand;
import cabernet1.monopoly.utils.Observer;

public interface INetworkFacade {
    void sendObject(ICommand command);

    void registerReceiver(Observer<ICommand> commandReceiver);
}
