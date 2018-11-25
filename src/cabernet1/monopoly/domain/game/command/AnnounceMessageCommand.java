package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.network.command.ICommand;

public class AnnounceMessageCommand extends ICommand {
    private String message;

    public String getMessage() {
        return message;
    }

    public AnnounceMessageCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        game.announceMessage(message);

    }
}
