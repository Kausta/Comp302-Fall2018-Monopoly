package cabernet1.monopoly.logging;

import java.time.Instant;

public class ClassLogger extends Logger {
    private static final long serialVersionUID = -5912565034684272404L;
    private final Class<?> clazz;

    public ClassLogger(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    protected void print(String logLevel, String message) {
        System.out.println(String.format("[%s] [%s] [%s] %s",
                Instant.now(),
                clazz.getSimpleName(),
                logLevel,
                message));
    }
}
