package cabernet1.monopoly.lib.cheat.parser;

import cabernet1.monopoly.lib.cheat.command.BuyPropertyCheatCommand;
import cabernet1.monopoly.lib.cheat.command.CheatCommand;

public class BuyPropertyParser extends Parser {
    @Override
    public CheatCommand parse(String[] arguments) {
        if (arguments.length < 2) {
            return null;
        }
        String first = arguments[1];
        Integer propertyId = parseInt(first);
        return propertyId != null ? new BuyPropertyCheatCommand(propertyId) : null;
    }
}
