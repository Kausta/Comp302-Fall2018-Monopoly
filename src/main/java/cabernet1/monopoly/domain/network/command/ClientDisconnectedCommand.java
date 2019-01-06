package cabernet1.monopoly.domain.network.command;

import java.util.List;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.InitializationController;
import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.game.player.InitialPlayerData;

public class ClientDisconnectedCommand extends ICommand {

    private String id;

    public ClientDisconnectedCommand(String identifier) {
        id = identifier;
    }

    @Override
    public void execute() {
        Game game = Game.getInstance();
        for(InitialPlayerData d : game.getPlayerData()){
            if(d.getOrigin().split(":")[0].equals(id) && !d.isBotPlayer() ){
                for(IPlayer p : game.getPlayers()){
                    if(p.getID() == d.getId()){
                        game.changePlayerToBot(p);
                    }
                }
            }
        }
    }

    
}