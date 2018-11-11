package cabernet1.monopoly.domain.game.die;

import cabernet1.monopoly.domain.game.die.enumerators.DieFaces;

import java.util.Random;

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
