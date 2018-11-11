package cabernet1.monopoly.domain.game.board;

import java.util.ArrayList;
import java.util.List;

import cabernet1.monopoly.domain.game.board.tile.Tile;

public class Board {
	List<Tile> boardTiles;
	private void initiateTiles() {
		//manually add all the information about the board's tile
	}
	private int getNumberOfTiles() {
		return boardTiles.size();
	}
	private int getPositionOfTile(Tile tile) {
		int counter=0;
		for (Tile singleTile:boardTiles) {
			if (singleTile.equals(tile))
				return counter;
			++counter;
		}
		return -1;
	}
	private Tile getTileAtPosition(int position) {
		return boardTiles.get(position);
	}
	public Board(){
		boardTiles=new ArrayList<>();
		initiateTiles();
	}
	Tile getNextTile(Tile curTile, int numberOfSteps) {
		int currentIdx=getPositionOfTile(curTile);
		currentIdx=(currentIdx+numberOfSteps)%getNumberOfTiles();
		return getTileAtPosition(currentIdx);
	}
}
