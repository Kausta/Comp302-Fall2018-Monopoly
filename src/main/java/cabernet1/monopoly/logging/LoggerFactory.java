package cabernet1.monopoly.logging;

public class LoggerFactory {
    private static volatile LoggerFactory _instance = null;

    private LoggerFactory() {

    }

    public static synchronized LoggerFactory getInstance() {
        if (_instance == null) {
            _instance = new LoggerFactory();
        }
        return _instance;
    }

    public Logger getLogger(Class<?> clazz) {
        return new ClassLogger(clazz);
    }
}
