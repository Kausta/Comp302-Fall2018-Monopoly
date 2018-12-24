package cabernet1.monopoly.domain.game.board.tile;

import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;

import java.io.Serializable;

public abstract class Tile implements Serializable {
    private static final long serialVersionUID = -4643593178838420041L;
    private static volatile int IDCounter = 0;
    protected final Board board = Board.getInstance();
    final int ID;
    private final String name;
    private final TileType tileType;
    private final int x;
    private final int y;
    private Tile nextTile;
    private Tile prevTile;

    public Tile(String name, TileType tileType, int x, int y) {
        this.name = name;
        this.tileType = tileType;
        this.x = x;
        this.y = y;
        synchronized (Tile.class) {
            this.ID = IDCounter++;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * @return the name of the tile
     */
    public String getName() {
        return name;
    }

    /**
     * @return the tile type
     */
    public TileType getTileType() {
        return tileType;
    }


    /**
     * Gets the next neighbor tile of this tile
     *
     * @return the next tile
     */
    public Tile getNextTile(boolean direction) {
        if (direction) {
            return nextTile;
        }
        return prevTile;
    }

    /**
     * Sets the neighbor tiles of this tile
     *
     * @param direction the direction of the neighborhood: 1 for next Tile -1 for previous tile
     * @param tile      the neighborhood tile
     */
    public void setNextTile(boolean direction, Tile tile) {
        if (direction) {
            nextTile = tile;
        } else {
            prevTile = tile;
        }
    }

    @Override
    public String toString() {
        return "Tile{ name=\"" + name + "\", x=" + x + ", y=" + y + " }";
    }

    /**
     * Gets the ID of the tile
     *
     * @return the ID of the tile
     */
    public int getID() {
        return ID;
    }
}
