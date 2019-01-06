package cabernet1.monopoly.utils;


import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeoutManager {
    private static volatile TimeoutManager _instance = null;

    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

    private TimeoutManager() {
    }

    public static synchronized TimeoutManager getInstance() {
        if (_instance == null) {
            _instance = new TimeoutManager();
        }
        return _instance;
    }

    public void setTimeout(Runnable runnable, long delayMs) {
        executor.schedule(runnable, delayMs, TimeUnit.MILLISECONDS);
    }

}
