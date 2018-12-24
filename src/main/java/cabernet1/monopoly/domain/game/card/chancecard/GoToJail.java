package cabernet1.monopoly.domain.game.card.chancecard;

import cabernet1.monopoly.domain.game.card.IimmediateAction;
import cabernet1.monopoly.domain.game.player.IPlayer;

public class GoToJail extends ChanceCard implements IimmediateAction {

    private static final long serialVersionUID = -6605115124262832534L;

    public GoToJail() {
        super("Go to Jail!");
    }

    @Override
    public void action(IPlayer player) {
        // player.move(Jail);
    }

}
