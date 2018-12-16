package cabernet1.monopoly.domain.game.die;

import cabernet1.monopoly.domain.game.die.enumerators.RegularDieFaces;
import cabernet1.monopoly.domain.game.die.enumerators.SpeedDieFaces;

public class SpeedDie extends IDie {
    public SpeedDie() {
        super();
    }

    @Override
    public void rollDice() {
        int value = generateRollResult();
        SpeedDieFaces speedDieFaces = rollResultToSpeedDieFaces(value);
        if (speedDieFaces != null) {
            setDiceValue(speedDieFaces);
        }
    }

    public int speedDieValue() {
        return getValue();
    }

    private SpeedDieFaces rollResultToSpeedDieFaces(int result) {
        switch (result) {
            case 1:
                return SpeedDieFaces.One;
            case 2:
                return SpeedDieFaces.Two;
            case 3:
                return SpeedDieFaces.Three;
            case 4:
            case 5:
                return SpeedDieFaces.MrMonopoly;
            case 6:
                return SpeedDieFaces.BusIcon;
            default:
                return null;
        }
    }

    @Override
    public boolean repOK() {
        if(!super.repOK()) {
            return false;
        }
        int value = getValue();
        return getDiceValue() instanceof SpeedDieFaces && value >= 0 && value <= 3;
    }

    @Override
    public String toString() {
        return "SpeedDie{ " + super.toString() + " }";
    }
}
