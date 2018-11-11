package cabernet1.monopoly.domain.game.die.util;

import cabernet1.monopoly.domain.game.die.RegularDie;
import cabernet1.monopoly.domain.game.die.SpeedDie;
import cabernet1.monopoly.domain.game.die.enumerators.NormalDiceCupStatus;
import cabernet1.monopoly.domain.game.die.enumerators.SpeedDieFaces;

public class NormalDiceCup {
	RegularDie die1;
	RegularDie die2;
	SpeedDie die3;
	private boolean isDoubles() {
		// when determining doubles, only the first two dice are consider
		return die1.getDiceValue()==die2.getDiceValue();
	}
	private boolean isTriples() {
		return die1.getDiceValue().getValue()==die2.getDiceValue().getValue() && die1.getDiceValue().getValue()==die3.getDiceValue().getValue();
	}

	private boolean isMrMonopolyMove() {
		return die3.getDiceValue()==SpeedDieFaces.MrMonopoly;
	}

	private boolean isBusMove() {
		return die3.getDiceValue()==SpeedDieFaces.BusIcon;
	}

	public NormalDiceCupStatus rollCup() {
		die1.rollDice();
		die2.rollDice();
		die3.rollDice();

		if (isTriples())
			return NormalDiceCupStatus.TripleMove;
		if (isDoubles())
			return NormalDiceCupStatus.DoubleMove;
		if (isMrMonopolyMove())
			return NormalDiceCupStatus.MrMonopolyMove;
		if (isBusMove())
			return NormalDiceCupStatus.BusMove;

		return NormalDiceCupStatus.normalMove;

	}
}
