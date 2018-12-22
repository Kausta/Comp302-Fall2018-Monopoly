package cabernet1.monopoly.domain.game.board;

import cabernet1.monopoly.utils.RepresentationInvariant;

import java.io.Serializable;

/**
 * Pool class represents the pool at the middle of the board, holding money from cards
 *
 * @overview Holds and handles the pool money
 */
public class Pool implements RepresentationInvariant, Serializable {
    private static final long serialVersionUID = -1288174310744044354L;
    /**
     * The total money in the pool
     */
    private int totalMoney;

    /**
     * Creates the pool with no money in it
     *
     * @modifies totalMoney
     * @effects Sets the money of the pool to zero
     */
    public Pool() {
        totalMoney = 0;
    }

    /**
     * Returns the total amount of money in the pool
     *
     * @return The total amount of money in the pool, always >= 0
     */
    public int getTotalMoney() {
        return totalMoney;
    }

    /**
     * Reduces the money in the pool to half
     *
     * @modifies totalMoney
     * @effects Reduces totalMoney to half, rounded down
     */
    public void reduceToHalf() {
        totalMoney /= 2;
    }

    /**
     * Adds the specified amount of money to the pool
     *
     * @param amount Amount of money to add
     * @requires amount >= 1
     * @modifies totalMoney
     * @effects Adds amount to totalMoney
     */
    public void addMoney(int amount) {
        totalMoney += amount;
    }

    @Override
    public boolean repOK() {
        return totalMoney >= 0;
    }

    @Override
    public String toString() {
        return "Pool{ " +
                "totalMoney: " + totalMoney +
                " }";
    }
}
