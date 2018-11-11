package cabernet1.monopoly.domain.network;

import cabernet1.monopoly.domain.network.command.ICommand;
import cabernet1.monopoly.domain.network.command.NetworkCommand;
import cabernet1.monopoly.utils.Observable;
import cabernet1.monopoly.utils.Observer;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ServerSocketAdapter implements INetworkAdapter {
    private Observable<NetworkCommand> commandObservable = new Observable<>();
    /**
     * Thread pool for waiting connections
     */
    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    private ServerSocket serverSocket;
    private ArrayList<ClientSocketAdapter> connectedClients;

    public ServerSocketAdapter(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.connectedClients = new ArrayList<>();
        executor.execute(this::waitForConnections);
    }

    @Override
    public void sendCommand(ICommand command) {
        connectedClients.forEach(socket -> socket.sendCommand(command));
    }

    @Override
    public void onReceiveCommand(Observer<NetworkCommand> observer) {
        // Subscribe to both this and clients, so the command executes locally and remotely
        commandObservable.addObserver(observer);
        connectedClients.forEach(socket -> socket.onReceiveCommand(observer));
    }

    /**
     * Waits for connections, creates adapters for them and stores the adapters
     */
    private void waitForConnections() {
        try {
            Socket socket = serverSocket.waitForConnection();
            ClientSocket clientSocket = new ClientSocket(socket);
            ClientSocketAdapter adapter = new ClientSocketAdapter(clientSocket);
            connectedClients.add(adapter);
            adapter.onReceiveCommand(this::resendCommand);
        } catch (IOException e) {
            e.printStackTrace();
        }

        executor.execute(this::waitForConnections);
    }

    /**
     * Re-sends a received command to other clients
     */
    private void resendCommand(NetworkCommand command) {
        this.sendCommand(command);
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}
