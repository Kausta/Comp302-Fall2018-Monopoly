package cabernet1.monopoly.domain.game.board;

public class Pool {
	private int totalMoney;
	public int getTotalMoney() {
		return totalMoney;
	}
	public void reduceToHalf() {
		totalMoney/=2;
	}
}
