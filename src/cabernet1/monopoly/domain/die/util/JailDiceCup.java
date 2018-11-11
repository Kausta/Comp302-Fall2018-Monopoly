package cabernet1.monopoly.domain.die.util;

import cabernet1.monopoly.domain.die.RegularDie;
import cabernet1.monopoly.domain.die.SpeedDie;
import cabernet1.monopoly.domain.die.enumerators.JailDiceCupStatus;

public class JailDiceCup {
	RegularDie die1;
	RegularDie die2;
	private boolean isDoubles() {
		// when determining doubles, only the first two dice are consider
		return die1.getDiceValue()==die2.getDiceValue();
	}
	public JailDiceCupStatus rollCup() {
		die1.rollDice();
		die2.rollDice();
		
		if (isDoubles())
			return JailDiceCupStatus.doubles;
		
		return JailDiceCupStatus.notDoubles;
	}
	public int getFacesValue() {
		return die1.getDiceValue().getValue()+die2.getDiceValue().getValue();
	}
}
