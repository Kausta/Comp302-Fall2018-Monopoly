package cabernet1.monopoly.domain.game.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.NetworkController;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.actiontile.*;
import cabernet1.monopoly.domain.game.board.tile.property.MediterraneanAvenue;
import cabernet1.monopoly.domain.game.board.tile.property.Property;
import cabernet1.monopoly.domain.game.card.chancecard.ChanceCard;
import cabernet1.monopoly.domain.game.card.chancecard.HolidayBonus;
import cabernet1.monopoly.domain.game.card.communitycard.CommunityChestCard;
import cabernet1.monopoly.domain.game.card.communitycard.PayHospitalBills;
import cabernet1.monopoly.domain.game.player.Player;
import cabernet1.monopoly.domain.game.player.enumerators.PlayerMovementStatus;
import cabernet1.monopoly.domain.network.command.commands.AnnounceMessageCommand;

public class Board {
	private static volatile Board _instance = null;
	private Pool poolTile;

	private List<CommunityChestCard> communityChestCards;
	private List<ChanceCard> chanceCards;
	private Random r = new Random();


	private Board() {
		boardTiles = new ArrayList<>();
		communityChestCards = new ArrayList<>();
		chanceCards = new ArrayList<>();
		initiateTiles();
		initializeCards();
	}

	public static synchronized Board getInstance() {
		if (_instance == null) {
			_instance = new Board();
		}
		return _instance;
	}

	private List<Tile> boardTiles;

	private void initiateTiles() {
		// manually add all the information about the board's tile
		poolTile = new Pool();
		for(int i = 0; i < 120; i++) {
			boardTiles.add(i, new MediterraneanAvenue());
		}
		boardTiles.add(0, new Go());
		boardTiles.add(80, new Jail());
	}

	private void initializeCards(){
		communityChestCards.add(new PayHospitalBills());
		chanceCards.add(new HolidayBonus());
	}

	public ChanceCard getChanceCard(){
		return chanceCards.get(r.nextInt(chanceCards.size()));
	}

	public CommunityChestCard getCommunityChestCard(){
		return communityChestCards.get(r.nextInt(communityChestCards.size()));
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
		// TODO: re-implement this function to support the multi-layer board, based on
		// the parity of number of steps
		int currentIdx = getPositionOfTile(curTile);
		currentIdx = (currentIdx + numberOfSteps) % getNumberOfTiles();
		return getTileAtPosition(currentIdx);
	}

	public Tile getJailTile() {
		return boardTiles.get(80);
	}

	public Pool getPoolTile() {
		return poolTile;
	}

	public Tile getInitialTile() {
		return boardTiles.get(0);
	}

	public Tile nextUnownedProperty(Tile curTile, int direction, int diceResult) {
		// TODO: implement nextUnownedProperty method
		// implement based on Monopoly rules, check handle Mr.Monopoly use case
		// return null if not found
		return null;
	}

	public Tile nextRentableProperty(Tile curTile, int direction, int diceResult) {
		// TODO: implement nextRentableProperty method
		// implement based on Monopoly rules, check handle Mr.Monopoly use case
		// return null if not found
		return null;
	}

	public Tile nextNearestCommunityChestOrChanceTile(Tile curTile, boolean direction, int diceResult) {
		// TODO: implement nextNearestCommunityChestOrChanceTile method
		// implement based on monopoly rules, check handle roll dice use case
		// return null if not found
		return null;
	}

	public void handleProperty(Player player, Property property) {
		GameController controller=Game.getInstance().getGameController();
		if(property.getOwner() == null && property.getPrice() < player.getMoney()){
			controller.enableBuyProperty();
		}else if(property.getOwner().equals(player)){
			// check if can buy building;
			// if so call controller.enableUpgradeBuilding  
		}else{
			int rent = property.getRent();
			player.payRent(rent);
			property.getOwner().gainMoney(rent);
		}
		// TODO finish implementing handleProperty method
	}
	public void handleTile(Player player,Tile destTile) {
		// TODO implement handleTile method
		String message ="";
		GameController controller=Game.getInstance().getGameController();
		/*  
		 *  this method will handle landing on the following tiles:
		 *  	- Property -> handleProperty ->
		 *  	- Jail -> call player.goJail
		 *  	- Chance or Community Tile -> use card.drawCard (TODO implement drawCard)
		 *  	- else, call the suitable class (it will do nothing)
		 *  
		 *  announce whatever is the suitable message using the code below
		 *  
		 *   
		 */
		if (player.getMovementStatus()==PlayerMovementStatus.NORMAL_MOVE) {
			controller.enableSpecialAction();
		}else {
			controller.enableEndTurn();
		}
		
		NetworkController nc=Network.getInstance().getNetworkController();
		nc.sendCommand(new AnnounceMessageCommand(message));
		
	}
	public void upgradeBuilding(Player player,Property property) {
		// TODO implement upgradeBuilding method
		// detect what is the type of the building (since there is only one definitive way to upgrade the building (if possible))
		// this method will only be called when it's possible to do so
		// upgrade that building on the corresponding property
		String message ="Building on "+property.getName()+" has been upgraded";
		NetworkController nc=Network.getInstance().getNetworkController();
		nc.sendCommand(new AnnounceMessageCommand(message));
	}

	/**
	 * This method will only be called when it's possible to do so
	 * @param player
	 * @param property
	 */
	public void buyProperty(Player player,Property property) {
		player.loseMoney(property.getPrice());
		player.ownProperty(property);
		property.setOwner(player);
		String message =player.getName() + " has bought "+ property.getName();
		NetworkController nc=Network.getInstance().getNetworkController();
		nc.sendCommand(new AnnounceMessageCommand(message));
	}
}
