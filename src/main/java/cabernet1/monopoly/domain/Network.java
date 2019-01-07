package cabernet1.monopoly.domain;

import cabernet1.monopoly.domain.network.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Network {
    private static volatile Network _instance = null;

    private String clientName;
    private Map<String, String> allClientNames = new HashMap<>();
    private Map<String, String> clientsForRecovery = new HashMap<>();
    private NetworkController networkController;
    private BaseSocket socket;
    private INetworkAdapter adapter;
    private String identifier;
    private boolean serverMode;
    private int serverPort;

    private Network() {
        allClientNames.put("Server", "Server");
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
        this.clientName = "Server";
        // In the case of Server, reset
        allClientNames = new HashMap<>();
        allClientNames.put("Server", "Server");
        clientsForRecovery = new HashMap<>();
        ServerSocket socket = new ServerSocket(port);
        this.socket = socket;
        this.socket.connect();
        this.adapter = new ServerSocketAdapter(socket);
        this.networkController = new NetworkController(getNetworkAdapter());
    }

    public void initializeClient(String clientName, String ip, int port) throws IOException {
        this.serverMode = false;
        this.clientName = clientName;
        ClientSocket socket = new ClientSocket(ip, port);
        this.socket = socket;
        this.socket.connect();
        this.adapter = new ClientSocketAdapter(socket);
        this.networkController = new NetworkController(getNetworkAdapter());
        this.serverPort = port;
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

    public Map<String, String> getAllClientNames() {
        return this.allClientNames;
    }

    public void addClientIdentifier(String clientName, String clientIdentifier) {
        this.allClientNames.put(clientName, clientIdentifier);
        clientsForRecovery.put(clientIdentifier, clientName);
    }

    public String getClientName() {
        return clientName;
    }

    public void removeClient(String identifier) {
        String name = this.clientsForRecovery.remove(identifier);
        this.allClientNames.remove(name);
    }

    public String nextClientCandidateForServer() {
        Optional<String> nextClient = this.clientsForRecovery.keySet().stream()
                .min(String::compareTo);
        return nextClient.orElse(null);
    }

    public Set<String> getAllClientIdentifiers() {
        return clientsForRecovery.keySet();
    }

    public Map<String, String> getAllClientIdentifiersByNames() {
        return clientsForRecovery;
    }

    public void setAllClientNames(Map<String, String> allClientNames) {
        this.allClientNames = allClientNames;
    }

    public void setClientsForRecovery(Map<String, String> clientsForRecovery) {
        this.clientsForRecovery = clientsForRecovery;
    }

    public int getServerPort() {
        return serverPort;
    }
}
