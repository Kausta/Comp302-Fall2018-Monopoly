package cabernet1.monopoly.domain;

import cabernet1.monopoly.domain.network.*;

import java.io.IOException;

public class Network {
    private static volatile Network _instance = null;

    private NetworkController networkController;
    private BaseSocket socket;
    private INetworkAdapter adapter;
    private String identifier;
    private boolean serverMode;

    private Network() {
    }

    public static synchronized Network getInstance() {
        if (_instance == null) {
            _instance = new Network();
        }
        return _instance;
    }

    public void initializeServer(int port) throws IOException {
        this.serverMode = true;
        this.identifier = "Server";
        ServerSocket socket = new ServerSocket(port);
        this.socket = socket;
        this.socket.connect();
        this.adapter = new ServerSocketAdapter(socket);
        this.networkController = new NetworkController(getNetworkAdapter());
    }

    public void initializeClient(String ip, int port) throws IOException {
        this.serverMode = false;
        ClientSocket socket = new ClientSocket(ip, port);
        this.socket = socket;
        this.socket.connect();
        this.adapter = new ClientSocketAdapter(socket);
        this.networkController = new NetworkController(getNetworkAdapter());
    }

    /**
     * Returns adapter for either server or client class (or mock socket until implemented)
     */
    public INetworkAdapter getNetworkAdapter() {
        return adapter;
    }

    public NetworkController getNetworkController() {
        return networkController;
    }

    public boolean isServerMode() {
        return serverMode;
    }

    public void setServerMode(boolean serverMode) {
        this.serverMode = serverMode;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
