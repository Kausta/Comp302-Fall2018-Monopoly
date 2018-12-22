package cabernet1.monopoly.domain.game.card;

import cabernet1.monopoly.domain.game.player.IPlayer;

import java.io.Serializable;

public interface IimmediateAction extends Serializable {
    void action(IPlayer player);
}
