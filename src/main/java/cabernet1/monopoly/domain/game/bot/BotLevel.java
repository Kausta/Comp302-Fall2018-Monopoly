package cabernet1.monopoly.domain.game.bot;

public enum BotLevel {
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard");

    private final String visibleText;

    BotLevel(String visibleText) {
        this.visibleText = visibleText;
    }

    public String getVisibleText() {
        return visibleText;
    }

    @Override
    public String toString() {
        return visibleText;
    }
}
