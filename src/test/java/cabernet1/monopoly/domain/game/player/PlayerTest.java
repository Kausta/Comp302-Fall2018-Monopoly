package cabernet1.monopoly.domain.game.player;

import cabernet1.monopoly.TestBase;
import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.property.colorgroups.purple.MediterraneanAvenue;
import cabernet1.monopoly.domain.game.board.tile.property.Property;
import cabernet1.monopoly.domain.game.player.enumerators.PlayerMovementStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest extends TestBase {
    private Player player;

    //helper methods
    private void resetMoney() {
        player.loseMoney(player.getMoney());//to reset back to zero
    }

    private void payRent(int startingMoney, int moneyToPay, int moneyToExpect) {
        if (startingMoney >= 0) {
            resetMoney();
            player.gainMoney(startingMoney);
        }
        player.payRent(moneyToPay);
        assertEquals(moneyToExpect, player.getMoney());
    }

    @BeforeEach
    public void createPlayerBeforeEachTest() {
        Board.getInstance().initialize();
        player = new Player(0, "player1", 100, 1, Board.getInstance().getInitialTile());
    }

    @AfterEach
    public void checkRepOkAfterEachTest() {
        testRepOK(player);
    }

    @Test
    void setMovementStatus() {
        player.setMovementStatus(PlayerMovementStatus.BUS_MOVE);
        assertEquals(PlayerMovementStatus.BUS_MOVE, player.getMovementStatus());
        player.setMovementStatus(PlayerMovementStatus.NORMAL_MOVE);
        assertEquals(PlayerMovementStatus.NORMAL_MOVE, player.getMovementStatus());
        player.setMovementStatus(PlayerMovementStatus.MRMONOPOLY_MOVE);
        assertEquals(PlayerMovementStatus.MRMONOPOLY_MOVE, player.getMovementStatus());
        player.setMovementStatus(PlayerMovementStatus.DOUBLE_MOVE);
        assertEquals(PlayerMovementStatus.DOUBLE_MOVE, player.getMovementStatus());
    }

    @Test
    void increaseNumberOfConsecutiveDoublesRolls() {
        int cur = player.getNumberOfConsecutiveDoublesRolls();
        player.increaseNumberOfConsecutiveDoublesRolls();
        ++cur;
        assertEquals(cur, player.getNumberOfConsecutiveDoublesRolls());
        ++cur;
        player.increaseNumberOfConsecutiveDoublesRolls();
        assertEquals(cur, player.getNumberOfConsecutiveDoublesRolls());
        assertEquals(cur, player.getNumberOfConsecutiveDoublesRolls());
    }

    @Test
    void ownProperty() {
        Property property = new MediterraneanAvenue(1, 1);
        assertFalse(player.isOwningProperty(property));
        player.ownProperty(property);
        assertTrue(player.isOwningProperty(property));
    }

    @Test
    void testInitialConfigurations() {
        assertTrue(player.repOK());
        assertEquals(0, player.getNumberOfConsecutiveDoublesRolls());
        assertFalse(player.isInJail());
        assertTrue(player.isActive());
        assertEquals(Board.getInstance().getInitialTile(), player.getCurrentTile());
        assertTrue(player.direction);

    }

    @Test
    void setCurrentTile() {
        Tile curTile = Board.getInstance().getInitialTile();
        player.setCurrentTile(curTile);
        assertEquals(curTile, player.getCurrentTile());
        curTile = Board.getInstance().getNextTile(curTile, true, 1, false);
        player.setCurrentTile(curTile);
        assertEquals(curTile, player.getCurrentTile());
        curTile = Board.getInstance().getJailTile();
        player.setCurrentTile(curTile);
        assertEquals(curTile, player.getCurrentTile());
    }

    @Test
    void isInteger() {
        assertTrue(player.isInteger("2"));
        assertTrue(player.isInteger("+2"));
        assertTrue(player.isInteger("24324"));
        assertFalse(player.isInteger("e"));
        assertFalse(player.isInteger("2e"));
        assertFalse(player.isInteger("e2"));
        assertFalse(player.isInteger("e4"));
        assertFalse(player.isInteger("3.4"));
    }

    @Test
    void changeJailStatus() {
        player.changeJailStatus(true);
        assertTrue(player.isInJail());
        assertEquals(0, player.getNumberOfConsecutiveDoublesRolls());

        player.increaseNumberOfConsecutiveDoublesRolls();
        player.changeJailStatus(true);
        assertTrue(player.isInJail());
        assertEquals(0, player.getNumberOfConsecutiveDoublesRolls());

        player.changeJailStatus(false);
        assertFalse(player.isInJail());
    }

    @Test
    void payRent() {
        payRent(100, 100, 0);
        payRent(200, 100, 100);
        payRent(100, 200, 0);
        payRent(1000, 0, 1000);
        payRent(-1, 200, 800);
        payRent(-1, 100, 700);
        payRent(-1, 300, 400);
        payRent(-1, 500, 0);
    }

    @Test
    void gainMoney() {
        resetMoney();
        player.gainMoney(100);
        assertEquals(100, player.getMoney());
        player.gainMoney(100);
        assertEquals(200, player.getMoney());
        resetMoney();
        player.gainMoney(0);
        assertEquals(0, player.getMoney());
    }

    @Test
    void loseMoney() {
        int curMoney = player.getMoney();
        //to ensure the player has money = 1000
        player.gainMoney(1000 - curMoney);
        curMoney = 1000;

        player.loseMoney(300);
        curMoney -= 300;
        assertEquals(curMoney, player.getMoney());
        player.loseMoney(500);
        curMoney -= 500;
        assertEquals(curMoney, player.getMoney());
    }
}
