package cabernet1.monopoly.lib.persistence;

import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GameLoader {
    private static GameLoader _instance = null;

    private Logger logger = LoggerFactory.getInstance().getLogger(getClass());

    private GameLoader() {
    }

    public static GameLoader getInstance() {
        if (_instance == null) {
            _instance = new GameLoader();
        }
        return _instance;
    }

    public void loadFromFile(String fileName) {
        Set<String> classNames = GameSaverRegistry.getInstance().getSaveableClassNames();
        Map<String, String> savedData = parseJson(Paths.get(GameSaver.SAVE_FILE_DIRECTORY, fileName), classNames);
        GameSerializer.getInstance().deserializeGameAndLoad(savedData);
    }

    private Map<String, String> parseJson(Path pathToFile, Set<String> keys) {
        try (InputStream is = Files.newInputStream(pathToFile);
             JsonReader reader = Json.createReader(is)) {
            JsonObject obj = reader.readObject();
            return keys.stream()
                    .collect(Collectors.toMap(name -> name, name -> obj.getString(name, "null")));
        } catch (IOException ex) {
            logger.e("Error occurred while parsing the json: " + ex.getMessage());
            return null;
        }
    }
}
