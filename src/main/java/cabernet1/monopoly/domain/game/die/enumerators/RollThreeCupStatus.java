package cabernet1.monopoly.domain.game.die.enumerators;

public class RollThreeCupStatus implements DiceCupStatus {
    private final DieFaces roll1;
    private final DieFaces roll2;
    private final DieFaces roll3;

    public RollThreeCupStatus(DieFaces roll1, DieFaces roll2, DieFaces roll3) {
        this.roll1 = roll1;
        this.roll2 = roll2;
        this.roll3 = roll3;
    }

    public int getRollSum() {
        return roll1.getValue() + roll2.getValue() + roll3.getValue();
    }

    public DieFaces getRoll1() {
        return roll1;
    }

    public DieFaces getRoll2() {
        return roll2;
    }

    public DieFaces getRoll3() {
        return roll3;
    }
}
