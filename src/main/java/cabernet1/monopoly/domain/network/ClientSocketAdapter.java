package cabernet1.monopoly.domain.network;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.network.command.ClientDisconnectedCommand;
import cabernet1.monopoly.domain.network.command.ICommand;
import cabernet1.monopoly.domain.network.command.NetworkCommand;
import cabernet1.monopoly.domain.network.command.RestartGame;
import cabernet1.monopoly.ui.ContainerView;
import cabernet1.monopoly.ui.NetworkRecoveryView;
import cabernet1.monopoly.utils.Observable;
import cabernet1.monopoly.utils.Observer;
import cabernet1.monopoly.utils.TimeoutManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ClientSocketAdapter implements INetworkAdapter {
    private final Observable<NetworkCommand> commandObservable = new Observable<>();
    /**
     * Thread pool for waiting commands
     */
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    private final ClientSocket clientSocket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private boolean running = false;

    public ClientSocketAdapter(ClientSocket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        Socket socket = clientSocket.getSocket();
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        running = true;
        executor.execute(this::waitForCommand);
    }

    @Override
    public void sendCommand(ICommand command) {
        try {
            this.objectOutputStream.writeObject(command);
        } catch (IOException e) {
            recover();
            e.printStackTrace();
        }
    }

    @Override
    public void onReceiveCommand(Observer<NetworkCommand> observer) {
        commandObservable.addObserver(observer);
    }

    private void waitForCommand() {
        try {
            Object command = this.objectInputStream.readObject();
            if (!(command instanceof NetworkCommand)) {
                throw new IOException("Incorrect command received: " + command);
            }
            this.commandObservable.setValue((NetworkCommand) command);
        } catch (IOException e) {
            this.recover();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (running) {
            executor.execute(this::waitForCommand);
        }
    }

    public ClientSocket getClientSocket() {
        return clientSocket;
    }

    private void recover() {
        running = false;
        String identifier = Network.getInstance().getIdentifier();
        String ip = clientSocket.getIpAddress();
        int port = clientSocket.getPort();
        try {
            clientSocket.getSocket().close();
        } catch (IOException ignored) {
            // Ignore if it is closed
        }
        if (Network.getInstance().isServerMode()) {
            // Client disconnected from server
            ServerSocketAdapter adapter = (ServerSocketAdapter) Network.getInstance().getNetworkAdapter();
            adapter.removeClient(clientSocket.getIdentifier());
            HashSet<String> identifiersToRemove = new HashSet<>();
            identifiersToRemove.add(clientSocket.getIdentifier());
            Network.getInstance().getNetworkController().sendCommand(new ClientDisconnectedCommand(identifiersToRemove));
        } else {
            // Server disconnected, we need a new server
            String nextClientIdentifier = Network.getInstance().nextClientCandidateForServer();
            System.out.println("Next identifier is: " + nextClientIdentifier + ", current identifier: " + identifier);
            if (identifier.equals(nextClientIdentifier)) {
                recoverClientBeingServer(identifier, Network.getInstance().getServerPort());
            } else {
                String[] clientInfo = nextClientIdentifier.split(":");
                recoverClientBeingClient(identifier, nextClientIdentifier, clientInfo[0], Network.getInstance().getServerPort());
            }
        }
    }

    private void recoverClientBeingClient(String myIdentifier, String identifierForRecovery, String clientIp, int port) {
        ContainerView.getInstance().setCurrentView(NetworkRecoveryView.getInstance());
        TimeoutManager.getInstance().setTimeout(() -> {
            boolean connected = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Network.getInstance().initializeClient("--Not Used--", clientIp, port);
                    connected = true;
                    break;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Tried " + clientIp + ":" + port);
                }
            }
            if (connected) {
                return;
            }
            Network.getInstance().removeClient(identifierForRecovery);
            String nextClientIdentifier = Network.getInstance().nextClientCandidateForServer();
            System.out.println("Next identifier is: " + nextClientIdentifier + ", current identifier: " + myIdentifier);
            if (myIdentifier.equals(nextClientIdentifier)) {
                recoverClientBeingServer(myIdentifier, port);
            } else {
                String[] clientInfo = nextClientIdentifier.split(":");
                recoverClientBeingClient(myIdentifier, nextClientIdentifier, clientInfo[0], Integer.parseInt(clientInfo[1]));
            }
        }, 15000);
    }

    private void recoverClientBeingServer(String identifier, int port) {
        Game game = Game.getInstance();
        List<IPlayer> players = game.getPlayers();
        if (players != null) {
            // Move servers players to me
            players.forEach(player -> {
                if (player.getOrigin().equals(identifier)) {
                    player.setOrigin("Server");
                }
            });
        }

        ContainerView.getInstance().setCurrentView(NetworkRecoveryView.getInstance());
        Network.getInstance().setServerMode(true);
        try {
            Network.getInstance().initializeServer(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TimeoutManager.getInstance().setTimeout(() -> {
            Set<String> identifiers = new HashSet<>(Network.getInstance().getAllClientIdentifiers());
            Network.getInstance().getNetworkController().sendCommand(new RestartGame(identifiers));
        }, 20000);
    }
}
