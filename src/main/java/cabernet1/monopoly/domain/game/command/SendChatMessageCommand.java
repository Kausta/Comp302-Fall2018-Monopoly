package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.network.command.ICommand;

public class SendChatMessageCommand extends ICommand {
    private static final long serialVersionUID = 5111162030342201877L;
    private final String message;

    public SendChatMessageCommand(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        game.chat.setValue(message);
    }
}
