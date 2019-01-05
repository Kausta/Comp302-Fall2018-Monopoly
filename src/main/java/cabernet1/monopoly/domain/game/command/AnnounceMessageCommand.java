package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.network.command.ICommand;

public class AnnounceMessageCommand extends ICommand {
    private static final long serialVersionUID = -8568196689964120267L;
    private final String message;

    public AnnounceMessageCommand(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        game.announceMessage(message);

    }
}
