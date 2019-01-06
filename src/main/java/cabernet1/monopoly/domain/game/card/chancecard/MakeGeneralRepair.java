package cabernet1.monopoly.domain.game.card.chancecard;

import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.NetworkController;
import cabernet1.monopoly.domain.game.board.tile.property.CabCo;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.property.Property;
import cabernet1.monopoly.domain.game.board.tile.tunnels.TransitStation;
import cabernet1.monopoly.domain.game.card.IimmediateAction;
import cabernet1.monopoly.domain.game.command.AnnounceMessageCommand;
import cabernet1.monopoly.domain.game.command.GainMoneyCommand;
import cabernet1.monopoly.domain.game.player.IPlayer;

public class MakeGeneralRepair extends ChanceCard implements IimmediateAction {

    private static final long serialVersionUID = -2856669947155490126L;

    public MakeGeneralRepair() {
        super("Make General Repair");
    }

    @Override
    public void action(IPlayer player) {
        int amount = 0;
        for(Property p: player.getOwnedProperties()){
            if(p instanceof GroupColoredProperty){
                GroupColoredProperty gp = (GroupColoredProperty) p;
                amount += 25 * gp.getHouse().getAmount();
                amount += 100 * (gp.getHotel().getAmount() + gp.getSkyscraper().getAmount());
            }
            if(p instanceof CabCo){
                CabCo c = (CabCo) p;
                if(c.getRent() != 0){
                    amount += 25;
                }
            }
            if(p instanceof TransitStation){
                TransitStation c = (TransitStation) p;
                if(c.getRent() != 0){
                    amount += 25;
                }
            }
        }
        NetworkController nc = Network.getInstance().getNetworkController();
        
        nc.sendCommand(new GainMoneyCommand(player.getID(), -amount));
        String message = player.getName() + " has paid "+ amount +" as repair fee";
        nc.sendCommand(new AnnounceMessageCommand(message));

    }
}
