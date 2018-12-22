package cabernet1.monopoly.lib.persistence;

import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GameSerializer {
    private static GameSerializer _instance = null;

    private Logger logger = LoggerFactory.getInstance().getLogger(getClass());

    private GameSerializer() {
    }

    public static GameSerializer getInstance() {
        if (_instance == null) {
            _instance = new GameSerializer();
        }
        return _instance;
    }

    public Map<String, String> serializeGame() {
        GameSaverRegistry registry = GameSaverRegistry.getInstance();
        Set<String> serializableClassNames = registry.getSaveableClassNames();
        return serializableClassNames.stream()
                .collect(Collectors.toMap(name -> name, name -> {
                    try {
                        Serializable instance = registry.getClassInstance(name);
                        if(instance == null) {
                            return "null";
                        }
                        String serialized = ObjectSerializer.serializeObject(instance);
                        if (serialized == null) {
                            throw new NullPointerException("Input/Output Exception while serializing");
                        }
                        return serialized;
                    } catch (NoSuchFieldException | IllegalAccessException | NullPointerException e) {
                        logger.e("Cannot serialize class " + name + ": " + e.getMessage());
                        throw new RuntimeException("Serialization error for class " + name);
                    }
                }));
    }
}
