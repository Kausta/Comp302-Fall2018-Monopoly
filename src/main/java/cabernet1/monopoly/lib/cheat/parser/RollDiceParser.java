package cabernet1.monopoly.lib.cheat.parser;

import cabernet1.monopoly.lib.cheat.command.BuyPropertyCheatCommand;
import cabernet1.monopoly.lib.cheat.command.CheatCommand;
import cabernet1.monopoly.lib.cheat.command.RollDiceCheatCommand;

public class RollDiceParser extends Parser {
    @Override
    public CheatCommand parse(String[] arguments) {
        return new RollDiceCheatCommand();
    }
}
