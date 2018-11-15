package cabernet1.monopoly.domain.game.die.enumerators;

public enum SpeedDieFaces implements DieFaces {
    One(1),
    Two(2),
    Three(3),
    MrMonopoly(0),
    BusIcon(0);
    private int value;

    SpeedDieFaces(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
