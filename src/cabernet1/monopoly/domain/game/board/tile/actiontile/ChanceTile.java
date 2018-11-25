package cabernet1.monopoly.domain.game.board.tile.actiontile;

import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.card.IimmediateAction;
import cabernet1.monopoly.domain.game.card.chancecard.ChanceCard;
import cabernet1.monopoly.domain.game.player.IPlayer;

public class ChanceTile extends ActionTile {

    public ChanceTile() {
        super("Chance", TileType.Chance);
    }

    @Override
    public void landingAction(IPlayer player) {
        ChanceCard card = board.getChanceCard();
        if (card instanceof IimmediateAction) {
            ((IimmediateAction) card).action(player);
        } else {
            // TODO cards that are storeable
        }
    }

    @Override
    public void passingAction(IPlayer player) {

    }

}
