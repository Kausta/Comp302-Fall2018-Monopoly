package cabernet1.monopoly.domain;

import cabernet1.monopoly.domain.network.INetworkAdapter;
import cabernet1.monopoly.domain.network.MockSocket;

public class Network {
    private static volatile Network _instance = null;
    private MockSocket socket;

    private Network() {
        this.socket = new MockSocket();
    }

    public static synchronized Network getInstance() {
        if(_instance == null) {
            _instance = new Network();
        }
        return _instance;
    }

    /**
     * Returns adapter for either server or client class (or mock socket until implemented)
     */
    public INetworkAdapter getNetworkAdapter() {
        return socket;
    }
}
