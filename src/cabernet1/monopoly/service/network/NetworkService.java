package cabernet1.monopoly.service.network;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class NetworkService {
    private static volatile NetworkService _instance = null;
    private MockSocket socket;

    private NetworkService() {
        this.socket = new MockSocket();
    }

    public static synchronized NetworkService getInstance() {
        if(_instance == null) {
            _instance = new NetworkService();
        }
        return _instance;
    }

    /**
     * Returns facade for either server or client class (or mock socket until implemented)
     */
    public INetworkFacade getAdapterForNetwork() {
        return socket;
    }
}
