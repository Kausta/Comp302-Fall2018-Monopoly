package cabernet1.monopoly.domain.game.board.tile;

import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;

public abstract class Tile {
    protected Board board = Board.getInstance();
    private String name;
    private TileType tileType;

    public Tile(String name, TileType tileType) {
        this.name = name;
        this.tileType = tileType;
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

    @Override
    public String toString() {
        return "Tile{ " +
                "name: " + name + '\'' +
                ", tileType: " + tileType +
                " }";
    }
}
