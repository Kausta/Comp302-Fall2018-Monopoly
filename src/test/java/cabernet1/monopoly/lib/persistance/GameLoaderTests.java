package cabernet1.monopoly.lib.persistance;

import cabernet1.monopoly.TestBase;
import cabernet1.monopoly.lib.persistence.GameLoader;
import cabernet1.monopoly.lib.persistence.GameSaver;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameLoaderTests extends TestBase {
    private static final String TEST_LOAD_NAME = "test_load_file.json";

    @Test
    public void gameLoadsWithoutException() {
        Path filePath = Paths.get(TEST_LOAD_NAME);
        try {
            Files.delete(filePath);
        } catch (IOException ignored) {
            // DO nothing
        }
        GameSerializerTests tests = new GameSerializerTests();
        // Make sure the serialization runs correctly before testing this
        tests.gameSerializesAndDeserializesCorrectly();
        assertDoesNotThrow(() -> GameSaver.getInstance().saveToFile(filePath));
        assertTrue(Files.exists(filePath));
        assertDoesNotThrow(() -> GameLoader.getInstance().loadFromFile(filePath));
    }

}
