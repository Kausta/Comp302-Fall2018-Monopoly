package cabernet1.monopoly.utils;

import java.net.URL;

public class ResourceManager {
    private static volatile ResourceManager _instance = null;

    private ResourceManager() {
    }

    public static synchronized ResourceManager getInstance() {
        if (_instance == null) {
            _instance = new ResourceManager();
        }
        return _instance;
    }

    public URL getResourcePath(String resourceName) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        return classloader.getResource(resourceName);
    }
}
