package cabernet1.monopoly.domain.game.die;

import cabernet1.monopoly.domain.game.die.enumerators.RegularDieFaces;

public class RegularDie extends IDie {
    private static final long serialVersionUID = 5446477803100561978L;

    public RegularDie() {
        super();
    }

    @Override
    public void rollDice() {
        int rollResult = generateRollResult();
        RegularDieFaces faceValue = rollResultToRegularDieFace(rollResult);
        if (faceValue != null) {
            this.setDiceValue(faceValue);
        }
    }

    private RegularDieFaces rollResultToRegularDieFace(int result) {
        switch (result) {
            case 1:
                return RegularDieFaces.One;
            case 2:
                return RegularDieFaces.Two;
            case 3:
                return RegularDieFaces.Three;
            case 4:
                return RegularDieFaces.Four;
            case 5:
                return RegularDieFaces.Five;
            case 6:
                return RegularDieFaces.Six;
            default:
                return null;
        }
    }

    @Override
    public boolean repOK() {
        if (!super.repOK()) {
            return false;
        }
        int value = getValue();
        return getDiceValue() instanceof RegularDieFaces && value >= 1 && value <= 6;
    }

    @Override
    public String toString() {
        return "RegularDie{ " + super.toString() + " }";
    }
}
