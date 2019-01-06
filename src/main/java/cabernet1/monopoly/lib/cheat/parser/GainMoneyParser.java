package cabernet1.monopoly.lib.cheat.parser;

import cabernet1.monopoly.lib.cheat.command.CheatCommand;
import cabernet1.monopoly.lib.cheat.command.GainMoneyCheatCommand;

public class GainMoneyParser extends Parser {
    @Override
    public CheatCommand parse(String[] arguments) {
        if (arguments.length < 2) {
            return null;
        }
        String first = arguments[1];
        Integer money = parseInt(first);
        return money != null ? new GainMoneyCheatCommand(money) : null;
    }
}
