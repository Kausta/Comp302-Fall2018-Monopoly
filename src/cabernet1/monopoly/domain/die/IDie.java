package cabernet1.monopoly.domain.die;

import java.security.ProtectionDomain;
import java.util.Random;

public abstract class IDie {
	protected String faceValue;
	protected static Random diceGen;
	public IDie() {
		if (diceGen==null)
			diceGen=new Random();
	}
	public String getDiceValue() {
		return faceValue;
	}
	public abstract void rollDice();
	
}
