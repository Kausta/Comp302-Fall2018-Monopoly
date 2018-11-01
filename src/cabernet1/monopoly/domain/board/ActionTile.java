package cabernet1.monopoly.domain.board;

public class ActionTile extends Tile{

	private String actionType;
	public ActionTile(String name,String actionType) {
		super(name);
		this.actionType=actionType;
	}

	
	
}
