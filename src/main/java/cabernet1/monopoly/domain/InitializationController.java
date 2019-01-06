package cabernet1.monopoly.domain;

import cabernet1.monopoly.Application;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.game.player.InitialPlayerData;
import cabernet1.monopoly.domain.network.command.InformNamesCommand;
import cabernet1.monopoly.domain.network.command.LoadGameCommand;
import cabernet1.monopoly.domain.network.command.StartGameCommand;
import cabernet1.monopoly.domain.network.initial.InitialPlayerInfo;
import cabernet1.monopoly.lib.persistence.GameLoader;
import cabernet1.monopoly.lib.persistence.GameSerializer;
import cabernet1.monopoly.utils.Observable;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

// import sun.nio.ch.Net;

public class InitializationController {
    private static volatile InitializationController _instance = null;

    private boolean isServer = false;

    private Observable<Boolean> connectionObservable;
    private Observable<Boolean> wasFileLoadSuccessful;
    private List<InitialPlayerInfo> playerNames;
    private HashMap<String, List<InitialPlayerInfo>> otherClientsPlayerNames = new HashMap<>();
    private boolean isLoadedGame = false;

    private InitializationController() {
        connectionObservable = new Observable<>();
        wasFileLoadSuccessful = new Observable<>();
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

    public void startServerLoaded(int port, File file) {
        Path filePath = Paths.get(file.getAbsolutePath());
        try {
            GameLoader.getInstance().loadFromFile(filePath);
            isLoadedGame = true;
            wasFileLoadSuccessful.setValue(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            wasFileLoadSuccessful.setValue(false);
            return;
        }
        startServer(port);
    }

    public void startClient(String clientName, String ip, int port) {
        boolean connected = Application.getInstance().startClient(clientName, ip, port);
        if (!connected) {
            connectionObservable.setValue(false);
        }
    }

    public void startClientFromWelcomeCommand(boolean loadedGame) {
        isLoadedGame = loadedGame;
        connectionObservable.setValue(true);
    }

    public void initializePlayerNames(List<InitialPlayerInfo> players) {
        this.playerNames = players.stream()
                .map(player -> {
                    String name = player.getPlayerName().trim();
                    if (player.isBot() && name.isEmpty()) {
                        name = "Bot " + UUID.randomUUID().toString().substring(0, 5);
                    }
                    return new InitialPlayerInfo(name, player.isBot(), player.getBotLevel());
                })
                .collect(Collectors.toList());

        Network network = Network.getInstance();
        String identifier = network.getIdentifier();
        InformNamesCommand command = new InformNamesCommand(
                identifier,
                this.playerNames);
        NetworkController networkController = network.getNetworkController();
        networkController.sendCommand(command);
    }

    public void initializePlayerNamesFor(String client, List<InitialPlayerInfo> playerNames) {
        this.otherClientsPlayerNames.put(client, playerNames);
    }

    public void startGame() {
        List<InitialPlayerData> initialPlayerData = new ArrayList<>();
        // Player names for players playing on this computer
        String identifier = Network.getInstance().getIdentifier();
        List<InitialPlayerInfo> currentPlayerNames = otherClientsPlayerNames.get(identifier);
        for (InitialPlayerInfo player : currentPlayerNames) {
            InitialPlayerData data = new InitialPlayerData(initialPlayerData.size(), player.getPlayerName(), Network.getInstance().getIdentifier(), player.isBot(), player.getBotLevel());
            initialPlayerData.add(data);
        }
        otherClientsPlayerNames.remove(identifier);
        for (String clientId : otherClientsPlayerNames.keySet()) {
            List<InitialPlayerInfo> players = otherClientsPlayerNames.get(clientId);
            players.forEach((player) -> {
                InitialPlayerData data = new InitialPlayerData(initialPlayerData.size(), player.getPlayerName(), clientId, player.isBot(), player.getBotLevel());
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

    public Observable<Boolean> getWasFileLoadSuccessful() {
        return wasFileLoadSuccessful;
    }

    public boolean isLoadedGame() {
        return isLoadedGame;
    }

    public void startLoadedGame(List<IPlayer> players, List<String> identifiers) {
        Map<Integer, String> playerLocation = new HashMap<>();
        for (int i = 0; i < players.size(); i++) {
            int id = players.get(i).getID();
            String identifier = identifiers.get(i);
            playerLocation.put(id, identifier);
        }
        GameController controller = Game.getInstance().getGameController();
        for (int id : playerLocation.keySet()) {
            String identifier = playerLocation.get(id);
            controller.getPlayer(id).setOrigin(identifier);
        }
        Map<String, String> serializedThing = GameSerializer.getInstance().serializeGame();
        LoadGameCommand command = new LoadGameCommand(serializedThing);
        Network.getInstance().getNetworkController().sendCommand(command);
    }
}
