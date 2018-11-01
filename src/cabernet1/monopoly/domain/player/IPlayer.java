package cabernet1.monopoly.domain.player;

public abstract class IPlayer {
	private String name;
	private int money;
	private boolean isActive;
	private int playerOrder;
	public IPlayer(String name,int money,int defaultOrder){
		this.name=name;
		this.money=money;
		this.isActive=true;
		this.playerOrder=defaultOrder;
	}
    public void playTurn() {
    	
    }
    
}
