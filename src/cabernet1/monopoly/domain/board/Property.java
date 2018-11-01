package cabernet1.monopoly.domain.board;

public class Property extends Tile{
	private int price;
	private int amountOfRent;
	public Property(String name,int price,int amountOfRent) {
		super(name);
		this.price=price;
		this.amountOfRent=amountOfRent;
	}
}
