package cabernet1.monopoly.domain.game.die.enumerators;

public enum RegularDieFaces implements DieFaces {
	One (1),
	Two (2),
	Three (3),
	Four (4),
	Five (5),
	Six (6);
	private int value;
	RegularDieFaces(int value){
		this.value=value;
	}

	@Override
	public int getValue() {
		return this.value;
	}
}
