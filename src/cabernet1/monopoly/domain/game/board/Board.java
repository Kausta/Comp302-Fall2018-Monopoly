package cabernet1.monopoly.domain.game.board;

import cabernet1.monopoly.domain.game.board.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class Board {
    List<Tile> boardTiles;

    private void initiateTiles() {
        //manually add all the information about the board's tile
    }

    private int getNumberOfTiles() {
        return boardTiles.size();
    }

    private int getPositionOfTile(Tile tile) {
        int counter = 0;
        for (Tile singleTile : boardTiles) {
            if (singleTile.equals(tile))
                return counter;
            ++counter;
        }
        return -1;
    }

    private Tile getTileAtPosition(int position) {
        return boardTiles.get(position);
    }

    public Board() {
        boardTiles = new ArrayList<>();
        initiateTiles();
    }

    public Tile getNextTile(Tile curTile, int numberOfSteps) {
        //TODO: re-implement this function to support the multi-layer board, based on the parity of number of steps
        int currentIdx = getPositionOfTile(curTile);
        currentIdx = (currentIdx + numberOfSteps) % getNumberOfTiles();
        return getTileAtPosition(currentIdx);
    }

    public Tile getJailTile() {
        //TODO implement getJailTile
        //just return the Jail Tile
        return null;
    }
}
