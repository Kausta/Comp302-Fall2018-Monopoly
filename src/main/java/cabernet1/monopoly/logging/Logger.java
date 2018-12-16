package cabernet1.monopoly.logging;

public abstract class Logger {
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
