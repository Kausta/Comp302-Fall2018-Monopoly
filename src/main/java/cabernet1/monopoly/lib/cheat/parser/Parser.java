package cabernet1.monopoly.lib.cheat.parser;

import cabernet1.monopoly.lib.cheat.command.CheatCommand;

public abstract class Parser {
    public abstract CheatCommand parse(String[] arguments);

    protected Integer parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException ignored) {
            return null;
        }
    }
}
