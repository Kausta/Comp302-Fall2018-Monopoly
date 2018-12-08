package cabernet1.monopoly.domain.network;

import java.io.IOException;

public abstract class BaseSocket {
    public abstract void connect() throws IOException;
}
