package cabernet1.monopoly.lib.persistance;

import cabernet1.monopoly.TestBase;
import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.game.bot.BotStrategyFactory;
import cabernet1.monopoly.domain.game.die.cup.JailDiceCup;
import cabernet1.monopoly.domain.game.die.cup.NormalDiceCup;
import cabernet1.monopoly.domain.game.die.cup.RollThreeDiceCup;
import cabernet1.monopoly.domain.game.player.InitialPlayerData;
import cabernet1.monopoly.lib.persistence.GameSerializer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameSerializerTests extends TestBase {

    @Test
    public void gameSerializesCorrectly() {
        // Create all instances
        List<InitialPlayerData> initialPlayerData = new ArrayList<>();
        initialPlayerData.add(new InitialPlayerData(0, "Test Name", "127.0.0.1:1000", false));
        Game.getInstance().initialize(initialPlayerData);
        RollThreeDiceCup.getInstance();
        NormalDiceCup.getInstance();
        JailDiceCup.getInstance();
        BotStrategyFactory.getInstance();
        // Test serialization
        assertDoesNotThrow(() -> {
            Map<String, String> serialized = GameSerializer.getInstance().serializeGame();
            assertNotNull(serialized);
            System.out.println("Serialized into: ");
            System.out.println(serialized);
        });
    }

}
