package cabernet1.monopoly.lib.cheat.parser;

import cabernet1.monopoly.lib.cheat.command.CheatCommand;
import cabernet1.monopoly.lib.cheat.command.GainMoneyCheatCommand;
import cabernet1.monopoly.lib.cheat.command.JumpTileCheatCommand;

public class JumpCommandParser extends Parser {
    @Override
    public CheatCommand parse(String[] arguments) {
        if (arguments.length < 2) {
            return null;
        }
        String first = arguments[1];
        Integer jumpTileId = parseInt(first);
        return jumpTileId != null ? new JumpTileCheatCommand(jumpTileId) : null;
    }
}
