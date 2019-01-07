package cabernet1.monopoly.domain.network;

import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.network.command.ClientDisconnectedCommand;
import cabernet1.monopoly.domain.network.command.ICommand;
import cabernet1.monopoly.domain.network.command.NetworkCommand;
import cabernet1.monopoly.utils.Observable;
import cabernet1.monopoly.utils.Observer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashSet;
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

    public ClientSocketAdapter(ClientSocket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        Socket socket = clientSocket.getSocket();
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
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

        executor.execute(this::waitForCommand);
    }

    public ClientSocket getClientSocket() {
        return clientSocket;
    }

    private void recover() {
        if (Network.getInstance().isServerMode()) {
            // Client disconnected from server
            ServerSocketAdapter adapter = (ServerSocketAdapter) Network.getInstance().getNetworkAdapter();
            adapter.removeClient(clientSocket.getIdentifier());
            HashSet<String> identifiersToRemove = new HashSet<>();
            identifiersToRemove.add(clientSocket.getIdentifier());
            Network.getInstance().getNetworkController().sendCommand(new ClientDisconnectedCommand(identifiersToRemove));
        } else {
            // Server disconnected, we need a new server

        }
    }
}
