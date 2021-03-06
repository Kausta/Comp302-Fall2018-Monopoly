package cabernet1.monopoly.domain.game.board;

import cabernet1.monopoly.TestBase;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.actiontile.Jail;
import cabernet1.monopoly.domain.game.board.tile.actiontile.PayDay;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest extends TestBase {

    List<Tile> tiles;
    private Board board;

    @BeforeEach
    public void initBoard() {
        board = Board.getInstance();
        board.initialize();
        tiles = board.boardTiles;
    }

    @AfterEach
    public void checkRepOKAfterTesting() {
        testRepOK(board);
    }

    @Test
    public void testNextTile() {
        Tile startTile = tiles.get(0);
        for (int i = 0; i < 5; i++) {
            assertEquals(board.getNextTile(startTile, true, i, false), tiles.get(i));
        }

        startTile = board.getJailTile(); //jailTile's position is hardcoded to be 10, TODO: Change this when we add jail
        for (int i = 0; i <= 5; i++) {
            assertEquals(board.getNextTile(startTile, true, i, false), tiles.get(10 + i));
        }
    }

    @Test
    public void testTileAtPosition() {
        for (int i = 0; i < 10; i++) {
            assertEquals(tiles.get(i), board.getTileAtPosition(i));
        }
        // test preset tiles with known positions
        assertEquals(board.getTileAtPosition(10), board.getJailTile());
        assertEquals(board.getTileAtPosition(0), board.getInitialTile());
    }

    @Test
    public void testPositionOfTile() {
        // test with preset tiles with known positions
        assertEquals(board.getPositionOfTile(board.getJailTile()), 10);
        assertEquals(board.getPositionOfTile(board.getInitialTile()), 0);
        assertEquals(board.getPositionOfTile(new PayDay(1, 1)), -1);
    }
    /* TODO: Enable this when we get jail
    @Test
    public void testGetJailTile() {
        Tile jail = board.getJailTile();
        assertTrue(jail instanceof Jail);
        assertEquals(jail, tiles.get(10));
    }*/

    @Test
    public void testGetInitTile() {
        Tile start = board.getInitialTile();
        assertEquals(start, tiles.get(0));
    }

    @Test
    public void testNumberOfTiles() {
        assertEquals(board.getNumberOfTiles(), tiles.size());
    }


}
