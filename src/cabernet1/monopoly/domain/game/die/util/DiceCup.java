package cabernet1.monopoly.domain.game.die.util;

import cabernet1.monopoly.domain.game.die.enumerators.DiceCupStatus;

public interface DiceCup {
	public DiceCupStatus rollCup();
	public int getFacesValue();
}
