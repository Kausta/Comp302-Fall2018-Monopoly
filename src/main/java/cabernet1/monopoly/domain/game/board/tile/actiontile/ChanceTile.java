package cabernet1.monopoly.domain.game.board.tile.actiontile;

import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;
import cabernet1.monopoly.domain.game.bot.BotPlayer;
import cabernet1.monopoly.domain.game.card.IimmediateAction;
import cabernet1.monopoly.domain.game.card.chancecard.ChanceCard;
import cabernet1.monopoly.domain.game.card.chancecard.Hurricane;
import cabernet1.monopoly.domain.game.player.IPlayer;

public class ChanceTile extends ActionTile {

    private static final long serialVersionUID = 6497731286751407759L;

    public ChanceTile(int x, int y, Track track) {
        super("Chance", TileType.Chance, x, y, track);
    }

    @Override
    public void landingAction(IPlayer player) {
        ChanceCard card = board.getChanceCard();
        while(card instanceof Hurricane && player instanceof BotPlayer){
            card = board.getChanceCard();
        }
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
