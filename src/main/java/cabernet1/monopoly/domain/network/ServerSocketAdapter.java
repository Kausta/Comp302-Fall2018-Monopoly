package cabernet1.monopoly.domain.network;

import cabernet1.monopoly.domain.network.command.ClientDisconnectedCommand;
import cabernet1.monopoly.domain.network.command.ICommand;
import cabernet1.monopoly.domain.network.command.NetworkCommand;
import cabernet1.monopoly.domain.network.command.WelcomeCommand;
import cabernet1.monopoly.utils.Observable;
import cabernet1.monopoly.utils.Observer;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ServerSocketAdapter implements INetworkAdapter {
    private final Observable<NetworkCommand> commandObservable = new Observable<>();
    /**
     * Thread pool for waiting connections
     */
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    private final ServerSocket serverSocket;
    private final ArrayList<ClientSocketAdapter> connectedClients;

    public ServerSocketAdapter(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.connectedClients = new ArrayList<>();
        executor.execute(this::waitForConnections);
    }

    @Override
    public void sendCommand(ICommand command) {
        command.execute();
        for(ClientSocketAdapter s : connectedClients){
            try {
                s.sendCommand(command);
            } catch (Exception e) {
                connectedClients.remove(s);
                sendCommand(new ClientDisconnectedCommand(s.getClientSocket().getIpAddress()));
            }
        }
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

            String id = clientSocket.getIpAddress() + ":" + clientSocket.getPort();
            WelcomeCommand command = new WelcomeCommand(id);
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
}
