package cabernet1.monopoly.domain.game.card;

import java.io.Serializable;

public class Card implements Serializable {
    private static final long serialVersionUID = 4305140065314261783L;
    String name;

    public Card(String name) {
        this.name = name;
    }
}
