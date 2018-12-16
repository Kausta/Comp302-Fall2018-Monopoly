package cabernet1.monopoly.domain.game.die.cup;

import cabernet1.monopoly.domain.game.die.RegularDie;
import cabernet1.monopoly.domain.game.die.enumerators.JailDiceCupStatus;

public class JailDiceCup implements DiceCup {
    private static volatile JailDiceCup _instance = null;

    RegularDie die1;
    RegularDie die2;

    private JailDiceCup() {

    }

    public static synchronized JailDiceCup getInstance() {
        if (_instance == null) {
            _instance = new JailDiceCup();
        }
        return _instance;
    }

    private boolean isDoubles() {
        // when determining doubles, only the first two dice are consider
        return die1.getDiceValue() == die2.getDiceValue();
    }

    public JailDiceCupStatus rollCup() {
        die1.rollDice();
        die2.rollDice();
        return JailDiceCupStatus.NOT_DOUBLES;
    }

    public int getFacesValue() {
        return die1.getDiceValue().getValue() + die2.getDiceValue().getValue();
    }
}
