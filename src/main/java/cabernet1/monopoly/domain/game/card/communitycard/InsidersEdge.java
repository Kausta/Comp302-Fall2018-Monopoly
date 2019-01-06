package cabernet1.monopoly.domain.game.card.communitycard;

import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.NetworkController;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;
import cabernet1.monopoly.domain.game.card.IimmediateAction;
import cabernet1.monopoly.domain.game.command.AnnounceMessageCommand;
import cabernet1.monopoly.domain.game.command.GainMoneyCommand;
import cabernet1.monopoly.domain.game.command.PayRentCommand;
import cabernet1.monopoly.domain.game.command.PoolIncreaseCommand;
import cabernet1.monopoly.domain.game.player.IPlayer;

public class InsidersEdge extends CommunityChestCard implements IimmediateAction {

    private static final long serialVersionUID = -6021063949315616757L;

    public InsidersEdge() {
        super("The Insider's Edge");
    }

    @Override
    public void action(IPlayer player) {
        NetworkController nc = Network.getInstance().getNetworkController();
        Track t = player.getCurrentTile().getTrack();
        String message = "";
        if(t == Track.Inner){
            nc.sendCommand(new GainMoneyCommand(player.getID(), 250));
            message = player.getName() + " has gained 250 for being in the inner layer";
        }else if(t == Track.Outer){
            nc.sendCommand(new GainMoneyCommand(player.getID(), -50));
            nc.sendCommand(new PoolIncreaseCommand(50));
            message = player.getName() + " has to pay 50 to the Pool";
        }else{
            message = "Nothing happened!";
        }

        nc.sendCommand(new AnnounceMessageCommand(message));

    }

}
