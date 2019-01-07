package cabernet1.monopoly.domain.network;

import cabernet1.monopoly.domain.InitializationController;
import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.network.command.ClientDisconnectedCommand;
import cabernet1.monopoly.domain.network.command.ICommand;
import cabernet1.monopoly.domain.network.command.NetworkCommand;
import cabernet1.monopoly.domain.network.command.WelcomeCommand;
import cabernet1.monopoly.utils.Observable;
import cabernet1.monopoly.utils.Observer;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ServerSocketAdapter implements INetworkAdapter {
    private final Observable<NetworkCommand> commandObservable = new Observable<>();
    /**
     * Thread pool for waiting connections
     */
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    private final ServerSocket serverSocket;
    private final HashMap<String, ClientSocketAdapter> connectedClients;

    public ServerSocketAdapter(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.connectedClients = new HashMap<>();
        executor.execute(this::waitForConnections);
    }

    @Override
    public void sendCommand(ICommand command) {
        command.execute();
        Set<String> identifiersToRemove = new HashSet<>();
        for (ClientSocketAdapter s : connectedClients.values()) {
            try {
                s.sendCommand(command);
            } catch (Exception e) {
                System.out.println("Client disconnected: " + s.getClientSocket().getIdentifier());
                identifiersToRemove.add(s.getClientSocket().getIdentifier());
            }
        }
        if (identifiersToRemove.size() > 0) {
            // Remove every disconnected client
            identifiersToRemove.forEach(connectedClients::remove);
            // Then actually send disconnected identifiers to every client
            Network.getInstance().getNetworkController().sendCommand(new ClientDisconnectedCommand(identifiersToRemove));
        }
    }

    @Override
    public void onReceiveCommand(Observer<NetworkCommand> observer) {
        // Subscribe to both this and clients, so the command executes locally and remotely
        commandObservable.addObserver(observer);
        connectedClients.values().forEach(socket -> socket.onReceiveCommand(observer));
    }

    /**
     * Waits for connections, creates adapters for them and stores the adapters
     */
    private void waitForConnections() {
        try {
            Socket socket = serverSocket.waitForConnection();
            ClientSocket clientSocket = new ClientSocket(socket);
            ClientSocketAdapter adapter = new ClientSocketAdapter(clientSocket);
            connectedClients.put(clientSocket.getIdentifier(), adapter);

            String id = clientSocket.getIpAddress() + ":" + clientSocket.getPort();
            WelcomeCommand command = new WelcomeCommand(id, InitializationController.getInstance().isLoadedGame());
            adapter.sendCommand(new NetworkCommand(command));

            adapter.onReceiveCommand(this::resendCommand);
        } catch (IOException e) {
            e.printStackTrace();
        }

        executor.execute(this::waitForConnections);
    }

    /**
     * Re-sends a received command to other clients and executes on the server
     */
    private void resendCommand(NetworkCommand command) {
        this.sendCommand(command);
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void removeClient(String identifier) {
        connectedClients.remove(identifier);
    }
}
