package cabernet1.monopoly.domain.network.initial;

import cabernet1.monopoly.domain.game.bot.BotLevel;

import java.io.Serializable;

public class InitialPlayerInfo implements Serializable {
    private static final long serialVersionUID = 30406251982161984L;
    private final String playerName;
    private final boolean isBot;
    private final BotLevel botLevel;

    public InitialPlayerInfo(String playerName, boolean isBot, BotLevel botLevel) {
        this.playerName = playerName;
        this.isBot = isBot;
        this.botLevel = botLevel;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isBot() {
        return isBot;
    }

    public BotLevel getBotLevel() {
        return botLevel;
    }
}
