package cabernet1.monopoly.domain.game.card.chancecard;

import cabernet1.monopoly.domain.game.card.IimmediateAction;
import cabernet1.monopoly.domain.game.player.IPlayer;

public class GoToJail extends ChanceCard implements IimmediateAction {

    public GoToJail(){
        super("Go to Jail!");
    }

    @Override
    public void action(IPlayer player) {
        // player.move(Jail);
	}

}