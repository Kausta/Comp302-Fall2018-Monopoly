package cabernet1.monopoly.logging;

import java.io.Serializable;

public abstract class Logger implements Serializable {
    private static final long serialVersionUID = -114114847506501578L;

    protected abstract void print(String logLevel, String message);

    public void d(String msg) {
        print("DEBUG", msg);
    }

    public void i(String msg) {
        print("INFO", msg);
    }

    public void w(String msg) {
        print("WARNING", msg);

    }

    public void e(String msg) {
        print("ERROR", msg);
    }

}
