package cabernet1.monopoly.domain.game.card.chancecard;

import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.NetworkController;
import cabernet1.monopoly.domain.game.card.IimmediateAction;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.game.command.AnnounceMessageCommand;
import cabernet1.monopoly.domain.game.command.GainMoneyCommand;

public class HolidayBonus extends ChanceCard implements IimmediateAction{

    public HolidayBonus(){
        super("Holiday Bonus");
    }

    @Override
    public void action(IPlayer player) {
        NetworkController nc = Network.getInstance().getNetworkController();
        nc.sendCommand(new GainMoneyCommand(player, 100));
        String message=player.getName()+" has gain 100 as a holiday bonus";
        nc.sendCommand(new AnnounceMessageCommand(message));

	}
}
