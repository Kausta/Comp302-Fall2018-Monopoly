package cabernet1.monopoly.lib.persistence;

import java.io.Serializable;
import java.util.HashMap;

public class GameSaverRegistry {
    private static GameSaverRegistry _instance = null;
    private HashMap<String, Class<? extends Serializable>> savedClasses;

    private GameSaverRegistry() {

    }

    public static synchronized GameSaverRegistry getInstance() {
        if (_instance == null) {
            _instance = new GameSaverRegistry();
        }
        return _instance;
    }
}
