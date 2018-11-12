package cabernet1.monopoly.domain.game.board.tile.property.building;


public abstract class Building {

	protected int price, sellPrice;
    protected int amount, limit;

    public Building(int price, int sellPrice){
        this.price = price;
        this.sellPrice = sellPrice;
        amount = 0;
    }
    public int getPrice() {
    	return price;
    }
    public int getSellPrice() {
    	return sellPrice;
    }
    /**
     * Increases the amount of the building by 1
     */
    public void increaseAmount(){
        amount++;
    }

    public void decreaseAmount(){
        amount--;
    }

    /**
     * Checks is the maximum building amount is reached for that building
     * @return boolean which states that the building limit is reached for that building
     */
    public boolean limitReached(){
        return amount == limit;
    }

    /**
     * Checks whether there are any of the specific building
     * @return boolean that there are any of the specific building
     */
    public boolean exists(){
        return amount != 0;
    }

    /**
     * Returns the rent
     * @return the rent
     */
    public abstract int getRent();
}
