package cabernet1.monopoly.domain.game.die;

import java.util.Random;

public abstract class IDie {
    protected static Random diceGen;
    protected String faceValue;

    public IDie() {
        diceGen = new Random();
    }

    public String getDiceValue() {
        return faceValue;
    }

    public abstract void rollDice();

}
