package cabernet1.monopoly.domain.die;

import java.util.Random;

import cabernet1.monopoly.domain.die.enumerators.DieFaces;

public abstract class IDie {
    protected static Random diceGen;
    protected DieFaces faceValue;

    public IDie() {
        diceGen = new Random();
    }

    public DieFaces getDiceValue() {
        return faceValue;
    }

    public abstract void rollDice();

}
