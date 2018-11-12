package cabernet1.monopoly.domain.game.die.util;

import cabernet1.monopoly.domain.game.die.RegularDie;
import cabernet1.monopoly.domain.game.die.enumerators.JailDiceCupStatus;

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
			return JailDiceCupStatus.DOUBLES;

		return JailDiceCupStatus.NOT_DOUBLES;
	}
	public int getFacesValue() {
		return die1.getDiceValue().getValue()+die2.getDiceValue().getValue();
	}
}
