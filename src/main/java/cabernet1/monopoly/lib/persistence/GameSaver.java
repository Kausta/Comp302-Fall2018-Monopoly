package cabernet1.monopoly.lib.persistence;

import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class GameSaver {
    public static final String SAVE_FILE_DIRECTORY = "saves";
    private static GameSaver _instance = null;

    private Logger logger = LoggerFactory.getInstance().getLogger(getClass());

    private GameSaver() {
    }

    public static GameSaver getInstance() {
        if (_instance == null) {
            _instance = new GameSaver();
        }
        return _instance;
    }

    public void saveToFile(String fileName) {
        Map<String, String> serializedObjects = GameSerializer.getInstance().serializeGame();
        String jsonContent = buildJson(serializedObjects);
        if (jsonContent == null) {
            throw new RuntimeException("Cannot serialize the game and convert to json");
        }
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAVE_FILE_DIRECTORY, fileName))) {
            writer.write(jsonContent);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write serialized json: " + e.getMessage());
        }
    }

    private String buildJson(Map<String, String> serializedObjects) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        for (String key : serializedObjects.keySet()) {
            String value = serializedObjects.get(key);
            builder.add(key, value);
        }
        JsonObject object = builder.build();
        try (StringWriter sw = new StringWriter()) {
            Json.createWriter(sw).writeObject(object);
            return sw.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
