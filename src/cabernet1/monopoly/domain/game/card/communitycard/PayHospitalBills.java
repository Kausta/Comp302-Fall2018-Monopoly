package cabernet1.monopoly.domain.game.card.communitycard;

import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.NetworkController;
import cabernet1.monopoly.domain.game.card.IimmediateAction;
import cabernet1.monopoly.domain.game.command.AnnounceMessageCommand;
import cabernet1.monopoly.domain.game.command.PayRentCommand;
import cabernet1.monopoly.domain.game.command.PoolIncreaseCommand;
import cabernet1.monopoly.domain.game.player.IPlayer;

public class PayHospitalBills extends CommunityChestCard implements IimmediateAction {

    public PayHospitalBills() {
        super("Pay Hospital Bills");
    }

    @Override
    public void action(IPlayer player) {
        NetworkController nc = Network.getInstance().getNetworkController();

        nc.sendCommand(new PoolIncreaseCommand(100));
        nc.sendCommand(new PayRentCommand(player, 100));

        String message = player.getName() + " has to pay 100 for the hospital bills";
        nc.sendCommand(new AnnounceMessageCommand(message));

    }

}
