package cabernet1.monopoly.domain.die.util;

import cabernet1.monopoly.domain.die.enumerators.DiceCupStatus;

public interface DiceCup {
	public DiceCupStatus rollCup();
	public int getFacesValue();
}
