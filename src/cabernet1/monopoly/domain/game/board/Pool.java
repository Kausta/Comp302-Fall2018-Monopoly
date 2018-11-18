package cabernet1.monopoly.domain.game.board;

public class Pool {
    private int totalMoney;

    public Pool(){
        totalMoney = 0;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void reduceToHalf() {
        totalMoney /= 2;
    }

    public void addMoney(int amount){
        totalMoney += amount;
    }
}
