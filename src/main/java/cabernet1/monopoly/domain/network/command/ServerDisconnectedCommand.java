package cabernet1.monopoly.domain.network.command;

import java.util.List;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.InitializationController;
import cabernet1.monopoly.domain.Network;

public class ServerDisconnectedCommand extends ICommand {

    public ServerDisconnectedCommand() {
    }

    @Override
    public void execute() {
        Game game = Game.getInstance();
        String identifier = Network.getInstance().getIdentifier();
        if(game.getNextPossibleServer().equals(identifier)){
            InitializationController.getInstance().startServer(8008);
            InitializationController.getInstance().setServer(true);
        }else{
            try {
                InitializationController.getInstance().startClient(game.getNextPossibleServer().split(":")[0], 8008);                
            } catch (Exception e) {
                
            }
            game.makeSelfIneligibleForServer(identifier);
            game.setNextServer();
        }
    }

    
}