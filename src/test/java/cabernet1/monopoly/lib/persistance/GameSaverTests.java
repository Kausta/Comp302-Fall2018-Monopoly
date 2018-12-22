package cabernet1.monopoly.lib.persistance;

import cabernet1.monopoly.TestBase;
import cabernet1.monopoly.lib.persistence.GameSaver;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameSaverTests extends TestBase {
    private static final String TEST_SAVE_NAME = "test_save_file.json";

    @Test
    public void gameSavesWithoutException() {
        Path filePath = Paths.get(GameSaver.SAVE_FILE_DIRECTORY, TEST_SAVE_NAME);
        try {
            Files.delete(filePath);
        } catch (IOException ignored) {
            // DO nothing
        }
        GameSerializerTests tests = new GameSerializerTests();
        // Make sure the serialization runs correctly before testing this
        tests.gameSerializesCorrectly();
        assertDoesNotThrow(() -> {
            GameSaver.getInstance().saveToFile(TEST_SAVE_NAME);
        });
        assertTrue(Files.exists(filePath));
    }

}
