package cabernet1.monopoly.lib.cheat;

import cabernet1.monopoly.lib.cheat.command.CheatCommand;
import cabernet1.monopoly.lib.cheat.command.GainMoneyCheatCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheatCommandParserTests {
    @Test
    public void gainMoneyCommandParsesSuccessfully() {
        String commandStr = "/gainMoney 100";
        CheatCommand command = CheatCommandParser.getInstance().tryParse(commandStr);
        assertNotNull(command);
        assertTrue(command instanceof GainMoneyCheatCommand);
        GainMoneyCheatCommand gainMoneyCheatCommand = (GainMoneyCheatCommand) command;
        assertEquals(100, gainMoneyCheatCommand.getMoneyToGain());
    }
}
