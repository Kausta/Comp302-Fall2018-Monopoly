package cabernet1.monopoly.domain;

import cabernet1.monopoly.Application;
import cabernet1.monopoly.domain.game.player.InitialPlayerData;
import cabernet1.monopoly.domain.network.command.InformNamesCommand;
import cabernet1.monopoly.domain.network.command.StartGameCommand;
import cabernet1.monopoly.utils.Observable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// import sun.nio.ch.Net;

public class InitializationController {
    private static volatile InitializationController _instance = null;

    private boolean isServer = false;

    private Observable<Boolean> connectionObservable;
    private List<String> playerNames;
    private HashMap<String, List<String>> otherClientsPlayerNames = new HashMap<>();

    private InitializationController() {
        connectionObservable = new Observable<>();
    }

    public static synchronized InitializationController getInstance() {
        if (_instance == null) {
            _instance = new InitializationController();
        }
        return _instance;
    }

    public boolean isServer() {
        return isServer;
    }

    public void setServer(boolean server) {
        isServer = server;
        Network.getInstance().setServerMode(server);
    }

    public void startServer(int port) {
        boolean connected = Application.getInstance().startServer(port);
        connectionObservable.setValue(connected);
    }

    public void startClient(String ip, int port) {
        boolean connected = Application.getInstance().startClient(ip, port);
        connectionObservable.setValue(connected);
    }

    public void initializePlayerNames(List<String> playerNames) {
        this.playerNames = playerNames;

        Network network = Network.getInstance();
        String identifier = network.getIdentifier();
        InformNamesCommand command = new InformNamesCommand(
                identifier,
                playerNames);
        NetworkController networkController = network.getNetworkController();
        networkController.sendCommand(command);
    }

    public void initializePlayerNamesFor(String client, List<String> playerNames) {
        this.otherClientsPlayerNames.put(client, playerNames);
    }

    public void startGame() {
        List<InitialPlayerData> initialPlayerData = new ArrayList<>();
        // Player names for players playing on this computer
        String identifier = Network.getInstance().getIdentifier();
        List<String> currentPlayerNames = otherClientsPlayerNames.get(identifier);
        System.out.println("reach here ,size is " + currentPlayerNames.size());
        for (String name : currentPlayerNames) {
            InitialPlayerData data = new InitialPlayerData(initialPlayerData.size(), name, Network.getInstance().getIdentifier(), false);
            initialPlayerData.add(data);
        }
        otherClientsPlayerNames.remove(identifier);
        for (String clientId : otherClientsPlayerNames.keySet()) {
            List<String> players = otherClientsPlayerNames.get(clientId);
            players.forEach((name) -> {
                InitialPlayerData data = new InitialPlayerData(initialPlayerData.size(), name, clientId, false);
                initialPlayerData.add(data);
            });
        }
        StartGameCommand command = new StartGameCommand(initialPlayerData);
        NetworkController networkController = Network.getInstance().getNetworkController();
        networkController.sendCommand(command);
    }

    public Observable<Boolean> getConnectionObservable() {
        return connectionObservable;
    }
}
