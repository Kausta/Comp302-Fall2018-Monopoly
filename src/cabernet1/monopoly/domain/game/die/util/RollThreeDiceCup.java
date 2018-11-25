package cabernet1.monopoly.domain.game.die.util;

import cabernet1.monopoly.domain.game.die.RegularDie;

public class RollThreeDiceCup implements DiceCup {
    private static volatile RollThreeDiceCup _instance = null;
    RegularDie die1;
    RegularDie die2;
    RegularDie die3;

    private RollThreeDiceCup() {

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

}
