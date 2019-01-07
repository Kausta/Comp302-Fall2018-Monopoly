package cabernet1.monopoly.domain.network;

import java.io.IOException;
import java.net.Socket;

public class ClientSocket extends BaseSocket {
    private final String ipAddress;
    private final int port;
    private Socket socket;

    /**
     * Constructor for creating a new client socket
     *
     * @param ipAddress
     * @param port
     */
    public ClientSocket(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    /**
     * Constructor for creating a client socket from an already initialized socket
     *
     * @param socket
     */
    public ClientSocket(Socket socket) {
        this.ipAddress = socket.getInetAddress().getHostAddress();
        this.port = socket.getPort();
        this.socket = socket;
    }

    @Override
    public void connect() throws IOException {
        this.socket = new Socket(ipAddress, port);
    }

    public String getIdentifier() {
        return getIpAddress() + ":" + getPort();
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return port;
    }

    public Socket getSocket() {
        return socket;
    }
}
