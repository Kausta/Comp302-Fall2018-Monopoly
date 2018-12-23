package cabernet1.monopoly.domain.game.board.tile;

import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;

public abstract class Tile {
    protected Board board = Board.getInstance();
    private String name;
    private TileType tileType;
    private Tile nextTile;
    private Tile prevTile;
    private int x;
    int ID;
    static int IDCounter=0;
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int y;

    public Tile(String name, TileType tileType, int x, int y) {
        this.name = name;
        this.tileType = tileType;
        this.x=x;
        this.y=y;
        this.ID=IDCounter++;
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
     * @return the next tile
     */
    public Tile getNextTile(int direction){
        if (direction==1){
            return nextTile;
        }
        return prevTile;
    }
    /**
     * Sets the neighbor tiles of this tile
     * @param direction the direction of the neighborhood: 1 for next Tile -1 for previous tile
     * @param tile the neighborhood tile
     */
    public void setNextTile(int direction,Tile tile){
        if (direction==1){
            nextTile=tile;
        }else{
            prevTile=tile;
        }
    }

    @Override
    public String toString() {
        return "Tile[name=\""+name+"\",x="+x+",y="+y+"]";
    }

    /**
     * Gets the ID of the tile
     * @return the ID of the tile
     */
    public int getID(){
        return ID;
    }
}
