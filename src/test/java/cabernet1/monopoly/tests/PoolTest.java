package cabernet1.monopoly.tests;

import cabernet1.monopoly.domain.game.board.Pool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PoolTest extends TestBase {

    private Pool pool;

    @BeforeEach
    public void createPoolBeforeEachTest() {
        pool = new Pool();
    }

    @AfterEach
    public void checkRepOKAfterEachTest() {
        testRepOK(pool);
    }

    @Test
    public void testInitialMoneyInThePool() {
        assertEquals(0, pool.getTotalMoney());
    }

    @Test
    public void testAddingMoneyIntoThePool() {
        final int[] amountsToAdd = new int[]{10, 5, 20, 24, 1};
        int money = pool.getTotalMoney();
        for (int amountToAdd : amountsToAdd) {
            money += amountToAdd;
            pool.addMoney(amountToAdd);
            assertEquals(money, pool.getTotalMoney());
        }
        assertEquals(money, pool.getTotalMoney());
    }

    @Test
    public void testHalvingTheMoneyInThePool() {
        int amount = 1000;
        pool.addMoney(amount);
        while (amount > 10) {
            pool.reduceToHalf();
            amount /= 2;
            assertEquals(amount, pool.getTotalMoney());
        }
        assertEquals(amount, pool.getTotalMoney());
    }

    @Test
    public void testHalvingWhenThereIsNoMoney() {
        pool.reduceToHalf();
        assertEquals(0, pool.getTotalMoney());
    }

    @Test
    public void testThatMoneyEqualsZeroAfterHalvingEnoughTimes() {
        int startingMoney = 1 << 10; // Get 2 ^ 10
        pool.addMoney(startingMoney);
        for(int i = 0;i < 11;i++) { // Half 11 times, which should make the pool 0
            pool.reduceToHalf();
        }
        assertEquals(0, pool.getTotalMoney());
    }
}
