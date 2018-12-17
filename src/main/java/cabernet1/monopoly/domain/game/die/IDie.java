package cabernet1.monopoly.domain.game.die;

import cabernet1.monopoly.domain.game.die.enumerators.DieFaces;
import cabernet1.monopoly.utils.Observable;
import cabernet1.monopoly.utils.RepresentationInvariant;

import java.util.Random;

public abstract class IDie extends Observable<Integer> implements RepresentationInvariant {
    private static final Random diceGen = new Random();
    private DieFaces faceValue;

    public IDie() {
        rollDice();
    }

    protected static synchronized int generateRollResult() {
        return diceGen.nextInt(6) + 1;
    }

    public DieFaces getDiceValue() {
        return faceValue;
    }

    protected void setDiceValue(DieFaces value) {
        if (value == null) {
            throw new NullPointerException("DieFaces value cannot be null!");
        }
        this.faceValue = value;
        this.setValue(faceValue.getValue());
    }

    public abstract void rollDice();

    @Override
    public boolean repOK() {
        return faceValue != null;
    }

    @Override
    public String toString() {
        String valueStr = faceValue == null ? "" : getValue().toString();
        return "IDie{ " +
                "faceValue: " + faceValue +
                ", value: " + valueStr +
                " }";
    }
}
