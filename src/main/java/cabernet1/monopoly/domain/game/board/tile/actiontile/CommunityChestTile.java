package cabernet1.monopoly.domain.game.board.tile.actiontile;

import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;
import cabernet1.monopoly.domain.game.card.IimmediateAction;
import cabernet1.monopoly.domain.game.card.communitycard.CommunityChestCard;
import cabernet1.monopoly.domain.game.player.IPlayer;

public class CommunityChestTile extends ActionTile {

    private static final long serialVersionUID = -7335170086860824724L;

    public CommunityChestTile(int x, int y, Track track) {
        super("Community Chest", TileType.CommunityChest, x, y, track);
    }

    @Override
    public void landingAction(IPlayer player) {
        CommunityChestCard card = board.getCommunityChestCard();
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
