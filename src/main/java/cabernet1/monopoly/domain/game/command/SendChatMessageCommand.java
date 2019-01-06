package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.network.command.ICommand;
import cabernet1.monopoly.lib.cheat.CheatCommandParser;
import cabernet1.monopoly.lib.cheat.command.CheatCommand;

public class SendChatMessageCommand extends ICommand {
    private static final long serialVersionUID = 5111162030342201877L;
    private final String rawMessage;
    private final String message;

    public SendChatMessageCommand(String rawMessage, String message) {
        this.rawMessage = rawMessage;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        CheatCommand command = CheatCommandParser.getInstance().tryParse(rawMessage);
        if(command != null) {
            command.execute();
            game.playerListObservable.setValue(game.playerList());
            game.chat.setValue("Current player cheated, shame!");
        }
        game.chat.setValue(message);
    }
}
