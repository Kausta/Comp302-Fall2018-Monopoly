package cabernet1.monopoly.domain.game.player;

import java.io.Serializable;

public class InitialPlayerData implements Serializable, Comparable {
    private static final long serialVersionUID = 297984012968679918L;
    private final int id;
    private final String name;
    private final String origin;
    private final boolean botPlayer;
    private boolean eligibleForServer;

    public InitialPlayerData(int id, String name, String origin, boolean botPlayer) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.botPlayer = botPlayer;
        this.eligibleForServer = true;
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

    public boolean getEligibleForServer() {
        return eligibleForServer;
    }

    public void setEligibleForServer(boolean b) {
        eligibleForServer = b;
    }

    /**
     * For deciding to choose successor server
     */
    @Override
    public int compareTo(Object o) {
        InitialPlayerData c = (InitialPlayerData) o;
        return origin.compareTo(c.origin);
    }
}
