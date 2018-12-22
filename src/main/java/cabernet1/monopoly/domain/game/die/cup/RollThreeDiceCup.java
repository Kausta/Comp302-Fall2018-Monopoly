package cabernet1.monopoly.domain.game.die.cup;

import cabernet1.monopoly.domain.game.die.RegularDie;
import cabernet1.monopoly.domain.game.die.enumerators.RollThreeCupStatus;

public class RollThreeDiceCup implements DiceCup {
    private static volatile RollThreeDiceCup _instance = null;
    private RegularDie die1;
    private RegularDie die2;
    private RegularDie die3;

    private RollThreeDiceCup() {
        die1 = new RegularDie();
        die2 = new RegularDie();
        die3 = new RegularDie();
    }

    public static synchronized RollThreeDiceCup getInstance() {
        if (_instance == null) {
            _instance = new RollThreeDiceCup();
        }
        return _instance;
    }

    @Override
    public int getFacesValue() {
        return die1.getDiceValue().getValue() + die2.getDiceValue().getValue() + die3.getDiceValue().getValue();
    }

    public RollThreeCupStatus rollCup() {
        die1.rollDice();
        die2.rollDice();
        die3.rollDice();

        return new RollThreeCupStatus(die1.getDiceValue(), die2.getDiceValue(), die3.getDiceValue());
    }
}
