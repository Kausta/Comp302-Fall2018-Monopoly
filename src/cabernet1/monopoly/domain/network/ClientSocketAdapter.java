package cabernet1.monopoly.domain.network;

import cabernet1.monopoly.domain.network.command.ICommand;
import cabernet1.monopoly.domain.network.command.NetworkCommand;
import cabernet1.monopoly.utils.Observable;
import cabernet1.monopoly.utils.Observer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ClientSocketAdapter implements INetworkAdapter {
    private Observable<NetworkCommand> commandObservable = new Observable<>();
    /**
     * Thread pool for waiting commands
     */
    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    private ClientSocket clientSocket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public ClientSocketAdapter(ClientSocket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        Socket socket = clientSocket.getSocket();
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        executor.execute(this::waitForCommand);
    }

    @Override
    public void sendCommand(ICommand command) {
        try {
            this.objectOutputStream.writeObject(command);
        } catch (IOException e) {
            throw new RuntimeException("Cannot send command through the socket");
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
                throw new IOException("Incorrect command received");
            }
            this.commandObservable.setValue((NetworkCommand) command);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        executor.execute(this::waitForCommand);
    }

    public ClientSocket getClientSocket() {
        return clientSocket;
    }
}