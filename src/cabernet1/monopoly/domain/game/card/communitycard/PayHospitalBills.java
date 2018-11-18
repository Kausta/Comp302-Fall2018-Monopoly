package cabernet1.monopoly.domain.game.card.communitycard;

import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.Pool;
import cabernet1.monopoly.domain.game.card.IimmediateAction;
import cabernet1.monopoly.domain.game.player.Player;

public class PayHospitalBills extends CommunityChestCard implements IimmediateAction {

    public PayHospitalBills(){
        super("Pay Hospital Bills");
    }
    @Override
    public void action(Player player) {
        Board board = Board.getInstance();
        Pool pool = board.getPoolTile();
        pool.addMoney(100);
        player.loseMoney(100);
    }

}