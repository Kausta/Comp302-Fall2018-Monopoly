package cabernet1.monopoly.domain.network.command;

import java.util.List;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.InitializationController;

public class ServerDisconnectedCommand extends ICommand {

    public ServerDisconnectedCommand() {
    }

    @Override
    public void execute() {
        // TODO: connect to server with nextpossible server from the game
        Game.getInstance();
    }

    
}
