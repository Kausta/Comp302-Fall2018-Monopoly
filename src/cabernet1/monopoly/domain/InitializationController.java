package cabernet1.monopoly.domain;

import cabernet1.monopoly.Application;

public class InitializationController {
    private static volatile InitializationController _instance = null;

    private boolean isServer = false;

    private InitializationController() {}

    public static synchronized InitializationController getInstance() {
        if(_instance == null) {
            _instance = new InitializationController();
        }
        return _instance;
    }

    public boolean isServer() {
        return isServer;
    }

    public void setServer(boolean server) {
        isServer = server;
    }

    public void startServer(int port) {
        boolean started = Application.getInstance().startServer(port);
    }

    public void startClient(String ip, int port) {
        boolean started = Application.getInstance().startClient(ip, port);
    }
}
