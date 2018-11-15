package cabernet1.monopoly.domain.game.board;

import cabernet1.monopoly.domain.game.board.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Tile> boardTiles;

    public Board() {
        boardTiles = new ArrayList<>();
        initiateTiles();
    }

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

    public Tile nextUnownedProperty(Tile curTile, boolean direction, int diceResult) {
        //TODO: implement nextUnownedProperty method
        // implement based on Monopoly rules, check handle Mr.Monopoly use case
        //		return null if not found
        return null;
    }

    public Tile nextRentableProperty(Tile curTile, boolean direction, int diceResult) {
        //TODO: implement nextRentableProperty method
        // implement based on Monopoly rules, check handle Mr.Monopoly use case
        //		return null if not found
        return null;
    }

    public Tile nextNearestCommunityChestOrChanceTile(Tile curTile, boolean direction, int diceResult) {
        //TODO: implement nextNearestCommunityChestOrChanceTile method
        // implement based on monopoly rules, check handle roll dice use case
        //		return null if not found
        return null;
    }

}
