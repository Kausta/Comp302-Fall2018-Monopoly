package cabernet1.monopoly.domain.game.player;

import java.io.Serializable;

public class InitialPlayerData implements Serializable {
    private final static int serialVersionUID = 1;

    private final int id;
    private final String name;
    private final String origin;
    private final boolean botPlayer;

    public InitialPlayerData(int id, String name, String origin, boolean botPlayer) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.botPlayer = botPlayer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public Boolean isBotPlayer() {
        return this.botPlayer;
    }
}
