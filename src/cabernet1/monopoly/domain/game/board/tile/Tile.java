package cabernet1.monopoly.domain.game.board.tile;

public abstract class Tile{
    private String name;

    public Tile(String name){
        this.name = name;
    }

	public String getName() {
		return name;
	}
}
