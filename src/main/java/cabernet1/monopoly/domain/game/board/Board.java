package cabernet1.monopoly.domain.game.board;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.NetworkController;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.actiontile.ChanceTile;
import cabernet1.monopoly.domain.game.board.tile.actiontile.CommunityChestTile;
import cabernet1.monopoly.domain.game.board.tile.actiontile.Go;
import cabernet1.monopoly.domain.game.board.tile.actiontile.Jail;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.property.MediterraneanAvenue;
import cabernet1.monopoly.domain.game.board.tile.property.Property;
import cabernet1.monopoly.domain.game.card.chancecard.ChanceCard;
import cabernet1.monopoly.domain.game.card.chancecard.HolidayBonus;
import cabernet1.monopoly.domain.game.card.communitycard.CommunityChestCard;
import cabernet1.monopoly.domain.game.card.communitycard.PayHospitalBills;
import cabernet1.monopoly.domain.game.command.*;
import cabernet1.monopoly.domain.game.player.Player;
import cabernet1.monopoly.domain.game.player.enumerators.PlayerMovementStatus;
import cabernet1.monopoly.utils.RepresentationInvariant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board implements RepresentationInvariant {
    private static volatile Board _instance = null;
    private Pool poolTile;

    private List<CommunityChestCard> communityChestCards;
    private List<ChanceCard> chanceCards;
    private Random r = new Random();
    public  List<Tile> boardTiles; //made public for testing purposes

    private Board() {
    }

    public static synchronized Board getInstance() {
        if (_instance == null) {
            _instance = new Board();
        }
        return _instance;
    }

    public void initialize() {
        boardTiles = new ArrayList<>();
        communityChestCards = new ArrayList<>();
        chanceCards = new ArrayList<>();
        initiateTiles();
        initializeCards();
    }

    private void initiateTiles() {
        // manually add all the information about the board's tile
        // first the low, right corner of middle then inner, then outer
        poolTile = new Pool();
        for (int i = 0; i < 120; i++) {
            boardTiles.add(i, new MediterraneanAvenue());
        }

        boardTiles.set(0, new Go());
        boardTiles.set(2, new CommunityChestTile());
        boardTiles.set(7, new ChanceTile());
        boardTiles.set(10, new Jail());
        boardTiles.set(17, new CommunityChestTile());
        boardTiles.set(22, new ChanceTile());
        boardTiles.set(33, new CommunityChestTile());
        boardTiles.set(36, new ChanceTile());
        boardTiles.set(45, new CommunityChestTile());
        boardTiles.set(57, new ChanceTile());
        boardTiles.set(67, new CommunityChestTile());
        boardTiles.set(75, new ChanceTile());
        boardTiles.set(86, new ChanceTile());
        boardTiles.set(89, new CommunityChestTile());
        boardTiles.set(95, new ChanceTile());
        boardTiles.set(101, new CommunityChestTile());
        boardTiles.set(111, new CommunityChestTile());
        boardTiles.set(119, new ChanceTile());
    }

    private void initializeCards() {
        communityChestCards.add(new PayHospitalBills());
        chanceCards.add(new HolidayBonus());
    }

    public ChanceCard getChanceCard() {
        return chanceCards.get(r.nextInt(chanceCards.size()));
    }

    public CommunityChestCard getCommunityChestCard() {
        return communityChestCards.get(r.nextInt(communityChestCards.size()));
    }

    /**
     * Return the number of tiles on the board.
     * @return the number of tiles on the board, i.e the size of the boardTiles list.*/
    public int getNumberOfTiles() {
        return boardTiles.size();
    } //made public for testing

    /**
     * Returns the position of a specified tile on the board.
     * @return the position of the tile if the tile exists on the board, -1 otherwise.*/
    public int getPositionOfTile(Tile tile) { //made public for testing
        int counter = 0;
        for (Tile singleTile : boardTiles) {
            if (singleTile.equals(tile))
                return counter;
            ++counter;
        }
        return -1;
    }

    /**
     * Returns the tile at an indicated position
     * @return the tile at the "position"th index of the boardTiles list.*/
    public Tile getTileAtPosition(int position) {
        return boardTiles.get(position);
    } //made public for testing

    /**
     * Returns the tile, indicated number of steps after an indicated starting point.
     * @return the tile numberOfSteps steps after curTile.*/
    public Tile getNextTile(Tile curTile, int numberOfSteps) {
        // TODO: re-implement this function to support the multi-layer board, based on
        // the parity of number of steps
        int currentIdx = getPositionOfTile(curTile);
        currentIdx = (currentIdx + numberOfSteps) % getNumberOfTiles();
        return getTileAtPosition(currentIdx);
    }

    /**
     * Returns the jail tile.
     * @return the jail tile on the board.*/
    public Tile getJailTile() {
        return boardTiles.get(10);
    }

    public Pool getPool() {
        return poolTile;
    }

    /**
     * Return the starting tile, namely the "GO" Tile.
     * @return the tile at the initial position, i.e. 0th index of the boardTiles list.*/
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
        GameController controller = Game.getInstance().getGameController();
        NetworkController nc = Network.getInstance().getNetworkController();

        if (property.getOwner() == null && property.getPrice() < player.getMoney()) {
            controller.enableBuyProperty();
        } else if (property.getOwner().equals(player)) {

            if (property instanceof GroupColoredProperty) {
                GroupColoredProperty gcp = (GroupColoredProperty) property;
                if (gcp.getUpgradeAmount() <= player.getMoney())
                    controller.enableUpgradeBuilding();
            }
        } else {
            int rent = property.getRent();
            nc.sendCommand(new PayRentCommand(player, rent));
            nc.sendCommand(new GainMoneyCommand(property.getOwner(), rent));

            String message = player.getName() + " has paid a rent to " + property.getOwner().getName();
            nc.sendCommand(new AnnounceMessageCommand(message));
        }
    }

    public void handleTile(Player player, Tile destTile) {
        String message = "";
        NetworkController nc = Network.getInstance().getNetworkController();
        GameController controller = Game.getInstance().getGameController();
        if (destTile instanceof Property) {
            handleProperty(player, (Property) destTile);
        } else if (destTile instanceof Jail) {
            message = player.getName() + " has got into jail!";
            nc.sendCommand(new ChangeJailStatusCommand(player, true));
        } else if (destTile instanceof ChanceTile) {
            ((ChanceTile) destTile).landingAction(player);
        } else if (destTile instanceof CommunityChestTile) {
            ((CommunityChestTile) destTile).landingAction(player);
        } else {// other types, do nothing for now
            message = player.getName() + " has nothing to do now,will take a rest";
        }
        if (player.getMovementStatus() == PlayerMovementStatus.NORMAL_MOVE) {
            controller.enableSpecialAction();
        } else {
            controller.enableEndTurn();
        }

        if (!message.equals(""))
            nc.sendCommand(new AnnounceMessageCommand(message));

    }

    public void upgradeBuilding(Player player, GroupColoredProperty property) {
        NetworkController nc = Network.getInstance().getNetworkController();
        int upgradeAmount = property.getUpgradeAmount();
        nc.sendCommand(new PayRentCommand(player, upgradeAmount));
        nc.sendCommand(new UpgradePropertyCommand(property));
        String message = "Building on " + property.getName() + " has been upgraded";
        nc.sendCommand(new AnnounceMessageCommand(message));
    }

    /**
     * This method will only be called when it's possible to do so
     *
     * @param player
     * @param property
     */
    public void buyProperty(Player player, Property property) {
        player.loseMoney(property.getPrice());
        player.ownProperty(property);
        property.setOwner(player);
        String message = player.getName() + " has bought " + property.getName();
        NetworkController nc = Network.getInstance().getNetworkController();
        nc.sendCommand(new AnnounceMessageCommand(message));
    }

    public boolean repOK(){
        return _instance != null;
    }

    public String toString(){
        return "Board{" +
                "" +
                "}";
    }
}
