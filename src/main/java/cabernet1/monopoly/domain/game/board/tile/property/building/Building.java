package cabernet1.monopoly.domain.game.board.tile.property.building;


public abstract class Building {

    protected int price, sellPrice;
    protected int amount, limit;
    protected String singularName, pluralName;

    public Building(int price, int sellPrice, String singularName, String pluralName) {
        this.price = price;
        this.sellPrice = sellPrice;
        this.singularName = singularName;
        this.pluralName = pluralName;
        amount = 0;
    }

    /**
     * @return price of building the building
     */
    public int getPrice() {
        return price;
    }

    /**
     * @return price of selling the building
     */
    public int getSellPrice() {
        return sellPrice;
    }

    /**
     * Checks is the maximum building amount is reached for that building
     *
     * @return boolean which states that the building limit is reached for that building
     */
    public boolean limitReached() {
        return amount == limit;
    }

    /**
     * Checks whether there are any of the specific building
     *
     * @return boolean that there are any of the specific building
     */
    public boolean exists() {
        return amount != 0;
    }

    /**
     * Increases the amount of the building by 1
     */
    public String increaseAmount() {
        if (limitReached()) {
            return "Already at the maximum number of " + getPluralName() + ".";
        }
        amount++;
        return "Successfully purchased the " + getSingularName() + ".";
    }

    /**
     * Decreases the amount of the building by 1
     */
    public String decreaseAmount() {
        if (exists()) {
            return "Successfully demolished the " + getSingularName() + ".";
        }
        amount--;
        return "There are no " + getPluralName() + " to demolish.";
    }

    /**
     * @return name of the building in singular form
     */
    public String getSingularName() {
        return singularName;
    }

    /**
     * @return name of the building in plural form
     */
    public String getPluralName() {
        return pluralName;
    }

    /**
     * Returns the rent
     *
     * @return the rent
     */
    public abstract int getRent();
}
