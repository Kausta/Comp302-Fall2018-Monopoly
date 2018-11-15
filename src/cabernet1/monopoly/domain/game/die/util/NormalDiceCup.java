package cabernet1.monopoly.domain.game.die.util;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.game.die.RegularDie;
import cabernet1.monopoly.domain.game.die.SpeedDie;
import cabernet1.monopoly.domain.game.die.enumerators.NormalDiceCupStatus;
import cabernet1.monopoly.domain.game.die.enumerators.SpeedDieFaces;

public class NormalDiceCup implements DiceCup{
	private static volatile NormalDiceCup _instance = null;
    
    RegularDie die1;
    RegularDie die2;
    SpeedDie die3;
  	private NormalDiceCup() {
  		
  	}
    public static synchronized NormalDiceCup getInstance() {
        if (_instance == null) {
            _instance = new NormalDiceCup();
        }
        return _instance;
    }

    private boolean isDoubles() {
        // when determining doubles, only the first two dice are consider
        return die1.getDiceValue() == die2.getDiceValue();
    }

    private boolean isTriples() {
        return die1.getDiceValue().getValue() == die2.getDiceValue().getValue() && die1.getDiceValue().getValue() == die3.getDiceValue().getValue();
    }

    private boolean isMrMonopolyMove() {
        return die3.getDiceValue() == SpeedDieFaces.MrMonopoly;
    }

    private boolean isBusMove() {
        return die3.getDiceValue() == SpeedDieFaces.BusIcon;
    }

    public NormalDiceCupStatus rollCup() {
        die1.rollDice();
        die2.rollDice();
        die3.rollDice();

        if (isTriples())
            return NormalDiceCupStatus.TRIPLE_MOVE;
        if (isDoubles())
            return NormalDiceCupStatus.DOUBLE_MOVE;
        if (isMrMonopolyMove())
            return NormalDiceCupStatus.MR_MONOPOLY_MOVE;
        if (isBusMove())
            return NormalDiceCupStatus.BUS_MOVE;

        return NormalDiceCupStatus.NORMAL_MOVE;
    }

    public int getFacesValue() {
        return die1.getDiceValue().getValue() + die2.getDiceValue().getValue() + die3.getDiceValue().getValue();
    }
}
