package cabernet1.monopoly.domain.die;

import java.util.Random;

public class RegularDie extends IDie{

	@Override
	public void rollDice() {
		faceValue = String.valueOf(diceGen.nextInt(6)+1);
	}

}
