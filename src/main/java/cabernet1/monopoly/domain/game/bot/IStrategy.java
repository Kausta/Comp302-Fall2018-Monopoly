package cabernet1.monopoly.domain.game.bot;

import java.io.Serializable;

public abstract class IStrategy implements Serializable {
    private static final long serialVersionUID = -7531755309069614061L;

    public abstract void execute();
}
