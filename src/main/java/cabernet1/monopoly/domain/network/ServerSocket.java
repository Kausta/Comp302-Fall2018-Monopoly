package cabernet1.monopoly.domain.network;

import java.io.IOException;
import java.net.Socket;

public class ServerSocket extends BaseSocket {
    private int port;
    private java.net.ServerSocket socket;

    public ServerSocket(int port) {
        this.port = port;
    }

    @Override
    public void connect() throws IOException {
        this.socket = new java.net.ServerSocket(port);
    }

    public int getPort() {
        return port;
    }

    public java.net.ServerSocket getServerSocket() {
        return socket;
    }

    public Socket waitForConnection() throws IOException {
        return socket.accept();
    }
}
