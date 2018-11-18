package cabernet1.monopoly.domain.game.card.chancecard;

import cabernet1.monopoly.domain.game.card.IimmediateAction;
import cabernet1.monopoly.domain.game.player.Player;

public class HolidayBonus extends ChanceCard implements IimmediateAction{
    
    public HolidayBonus(){
        super("Holiday Bonus");
    }

    @Override
    public void action(Player player) {
        player.gainMoney(100);
	}
}