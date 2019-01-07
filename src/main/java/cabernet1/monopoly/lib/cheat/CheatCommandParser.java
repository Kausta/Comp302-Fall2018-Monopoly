package cabernet1.monopoly.lib.cheat;

import cabernet1.monopoly.lib.cheat.command.CheatCommand;
import cabernet1.monopoly.lib.cheat.parser.*;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

import java.util.HashMap;

public class CheatCommandParser {
    private static volatile CheatCommandParser _instance = null;
    private Logger logger = LoggerFactory.getInstance().getLogger(CheatCommandParser.class);
    private HashMap<String, Parser> classes = new HashMap<>();

    private CheatCommandParser() {
        classes.put("gainmoney", new GainMoneyParser());
        classes.put("buyproperty", new BuyPropertyParser());
        classes.put("jump", new JumpCommandParser());
        classes.put("rolldice", new RollDiceParser());
    }

    public static synchronized CheatCommandParser getInstance() {
        if (_instance == null) {
            _instance = new CheatCommandParser();
        }
        return _instance;
    }

    /**
     * Tries to parse the given command, returns null if it cannot
     *
     * @param command Command to parse
     * @return Command if parsed successfully, null otherwise
     */
    public CheatCommand tryParse(String command) {
        if (command == null || command.trim().isEmpty()) {
            return null;
        }
        command = command.trim();
        if (!command.startsWith("/")) {
            return null;
        }
        command = command.substring(1);
        String[] entries = command.split("\\s+");
        logger.d("Parsing command " + command);
        if (entries.length == 0) {
            return null;
        }
        String commandName = entries[0];
        Parser parser = classes.get(commandName.toLowerCase());
        return parser != null ? parser.parse(entries) : null;
    }
}
