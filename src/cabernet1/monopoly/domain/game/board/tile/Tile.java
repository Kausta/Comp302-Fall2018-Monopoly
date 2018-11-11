package cabernet1.monopoly.domain.game.board.tile;

public abstract class Tile{
    protected String name;

    public Tile(String name){
        this.name = name;
    }
    public String getName() {
    	return name;
    }
}
