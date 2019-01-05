package cabernet1.monopoly.domain.game.board;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.NetworkController;
import cabernet1.monopoly.domain.game.board.tile.FreeTile;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.actiontile.ChanceTile;
import cabernet1.monopoly.domain.game.board.tile.actiontile.CommunityChestTile;
import cabernet1.monopoly.domain.game.board.tile.actiontile.Go;
import cabernet1.monopoly.domain.game.board.tile.actiontile.Jail;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.property.Property;
import cabernet1.monopoly.domain.game.board.tile.tunnels.TransitStation;
import cabernet1.monopoly.domain.game.card.chancecard.ChanceCard;
import cabernet1.monopoly.domain.game.card.chancecard.HolidayBonus;
import cabernet1.monopoly.domain.game.card.communitycard.CommunityChestCard;
import cabernet1.monopoly.domain.game.card.communitycard.PayHospitalBills;
import cabernet1.monopoly.domain.game.command.*;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.game.player.Player;
import cabernet1.monopoly.domain.game.player.enumerators.PlayerMovementStatus;
import cabernet1.monopoly.lib.persistence.Saveable;
import cabernet1.monopoly.utils.RepresentationInvariant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Saveable
public class Board implements RepresentationInvariant, Serializable {
    private static final long serialVersionUID = 5361865266782383461L;
    private static volatile Board _instance = null;
    private final Random r = new Random();
    List<Tile> boardTiles; //made package-private for testing purposes
    private Pool poolTile;
    private List<CommunityChestCard> communityChestCards;
    private List<ChanceCard> chanceCards;

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
        boardTiles.add(0, new Go(1560, 1560));
        boardTiles.add(1, new FreeTile("Tile1", 1397, 1560));
        boardTiles.add(2, new CommunityChestTile(1287, 1560, Track.Center));
        boardTiles.add(3, new FreeTile("Tile3", 1177, 1560));
        boardTiles.add(4, new FreeTile("Tile4", 1067, 1560));
        boardTiles.add(5, new TransitStation(957, 1560, Track.Center));
        boardTiles.add(6, new FreeTile("Tile6", 847, 1560));
        boardTiles.add(7, new ChanceTile(737, 1560, Track.Center));
        boardTiles.add(8, new FreeTile("Tile8", 627, 1560));
        boardTiles.add(9, new FreeTile("Tile9", 517, 1560));
        boardTiles.add(10, new FreeTile("Tile10", 354, 1560));
        boardTiles.add(11, new FreeTile("Tile11", 354, 1397));
        boardTiles.add(12, new FreeTile("Tile12", 354, 1287));
        boardTiles.add(13, new FreeTile("Tile13", 354, 1177));
        boardTiles.add(14, new FreeTile("Tile14", 354, 1067));
        boardTiles.add(15, new TransitStation(354, 957, Track.Center));
        boardTiles.add(16, new FreeTile("Tile16", 354, 847));
        boardTiles.add(17, new CommunityChestTile(354, 737, Track.Center));
        boardTiles.add(18, new FreeTile("Tile18", 354, 627));
        boardTiles.add(19, new FreeTile("Tile19", 354, 517));
        boardTiles.add(20, new FreeTile("Tile20", 354, 354));
        boardTiles.add(21, new FreeTile("Tile21", 517, 354));
        boardTiles.add(22, new ChanceTile(627, 354, Track.Center));
        boardTiles.add(23, new FreeTile("Tile23", 737, 354));
        boardTiles.add(24, new FreeTile("Tile24", 847, 354));
        boardTiles.add(25, new TransitStation(957, 354, Track.Center));
        boardTiles.add(26, new FreeTile("Tile26", 1067, 354));
        boardTiles.add(27, new FreeTile("Tile27", 1177, 354));
        boardTiles.add(28, new FreeTile("Tile28", 1287, 354));
        boardTiles.add(29, new FreeTile("Tile29", 1397, 354));
        boardTiles.add(30, new FreeTile("Tile30", 1560, 354));
        boardTiles.add(31, new FreeTile("Tile31", 1560, 517));
        boardTiles.add(32, new FreeTile("Tile32", 1560, 627));
        boardTiles.add(33, new CommunityChestTile(1560, 737, Track.Center));
        boardTiles.add(34, new FreeTile("Tile34", 1560, 847));
        boardTiles.add(35, new TransitStation(1560, 957, Track.Center));
        boardTiles.add(36, new ChanceTile(1560, 1067, Track.Center));
        boardTiles.add(37, new FreeTile("Tile37", 1560, 1177));
        boardTiles.add(38, new FreeTile("Tile38", 1560, 1287));
        boardTiles.add(39, new FreeTile("Tile39", 1560, 1397));


        boardTiles.add(40, new FreeTile("Tile40", 1800, 1800));
        boardTiles.add(41, new FreeTile("Tile41", 1634, 1800));
        boardTiles.add(42, new CommunityChestTile(1522, 1800, Track.Outer));
        boardTiles.add(43, new FreeTile("Tile43", 1410, 1800));
        boardTiles.add(44, new FreeTile("Tile44", 1298, 1800));
        boardTiles.add(45, new FreeTile("Tile45", 1186, 1800));
        boardTiles.add(46, new FreeTile("Tile46", 1074, 1800));
        boardTiles.add(47, new TransitStation(962, 1800, Track.Center));
        boardTiles.add(48, new FreeTile("Tile48", 850, 1800));
        boardTiles.add(49, new FreeTile("Tile49", 738, 1800));
        boardTiles.add(50, new ChanceTile(626, 1800, Track.Outer));
        boardTiles.add(51, new FreeTile("Tile51", 514, 1800));
        boardTiles.add(52, new FreeTile("Tile52", 402, 1800));
        boardTiles.add(53, new FreeTile("Tile53", 290, 1800));
        boardTiles.add(54, new FreeTile("Tile54", 124, 1800));
        boardTiles.add(55, new FreeTile("Tile55", 124, 1634));
        boardTiles.add(56, new FreeTile("Tile56", 124, 1522));
        boardTiles.add(57, new FreeTile("Tile57", 124, 1410));
        boardTiles.add(58, new FreeTile("Tile58", 124, 1298));
        boardTiles.add(59, new FreeTile("Tile59", 124, 1186));
        boardTiles.add(60, new FreeTile("Tile60", 124, 1074));
        boardTiles.add(61, new ChanceTile(124, 962, Track.Outer));
        boardTiles.add(62, new FreeTile("Tile62", 124, 850));
        boardTiles.add(63, new FreeTile("Tile63", 124, 738));
        boardTiles.add(64, new CommunityChestTile(124, 626, Track.Outer));
        boardTiles.add(65, new FreeTile("Tile65", 124, 514));
        boardTiles.add(66, new FreeTile("Tile66", 124, 402));
        boardTiles.add(67, new FreeTile("Tile67", 124, 290));
        boardTiles.add(68, new FreeTile("Tile68", 124, 124));
        boardTiles.add(69, new FreeTile("Tile69", 290, 124));
        boardTiles.add(70, new ChanceTile(402, 124, Track.Outer));
        boardTiles.add(71, new FreeTile("Tile71", 514, 124));
        boardTiles.add(72, new FreeTile("Tile72", 626, 124));
        boardTiles.add(73, new FreeTile("Tile73", 738, 124));
        boardTiles.add(74, new FreeTile("Tile74", 850, 124));
        boardTiles.add(75, new TransitStation(962, 124, Track.Center));
        boardTiles.add(76, new CommunityChestTile(1074, 124, Track.Outer));
        boardTiles.add(77, new FreeTile("Tile77", 1186, 124));
        boardTiles.add(78, new FreeTile("Tile78", 1298, 124));
        boardTiles.add(79, new FreeTile("Tile79", 1410, 124));
        boardTiles.add(80, new FreeTile("Tile80", 1522, 124));
        boardTiles.add(81, new FreeTile("Tile81", 1634, 124));
        boardTiles.add(82, new FreeTile("Jail", 1800, 124));
        boardTiles.add(83, new FreeTile("Tile83", 1800, 290));
        boardTiles.add(84, new FreeTile("Tile84", 1800, 402));
        boardTiles.add(85, new FreeTile("Tile85", 1800, 514));
        boardTiles.add(86, new CommunityChestTile(1800, 626, Track.Outer));
        boardTiles.add(87, new FreeTile("Tile87", 1800, 738));
        boardTiles.add(88, new FreeTile("Tile88", 1800, 850));
        boardTiles.add(89, new FreeTile("Tile89", 1800, 962));
        boardTiles.add(90, new FreeTile("Tile90", 1800, 1074));
        boardTiles.add(91, new FreeTile("Tile91", 1800, 1186));
        boardTiles.add(92, new FreeTile("Tile92", 1800, 1298));
        boardTiles.add(93, new FreeTile("Tile93", 1800, 1410));
        boardTiles.add(94, new ChanceTile(1800, 1522, Track.Outer));
        boardTiles.add(95, new FreeTile("Tile95", 1800, 1634));


        boardTiles.add(96, new FreeTile("Tile96", 1333, 1333));
        boardTiles.add(97, new FreeTile("Tile97", 1173, 1333));
        boardTiles.add(98, new FreeTile("Tile98", 1065, 1333));
        boardTiles.add(99, new FreeTile("Tile99", 957, 1333));
        boardTiles.add(100, new CommunityChestTile(849, 1333, Track.Inner));
        boardTiles.add(101, new FreeTile("Tile101", 741, 1333));
        boardTiles.add(102, new FreeTile("Tile102", 581, 1333));
        boardTiles.add(103, new FreeTile("Tile103", 581, 1173));
        boardTiles.add(104, new FreeTile("Tile104", 581, 1065));
        boardTiles.add(105, new TransitStation(581, 957, Track.Center));
        boardTiles.add(106, new FreeTile("Tile106", 581, 849));
        boardTiles.add(107, new FreeTile("Tile107", 581, 741));
        boardTiles.add(108, new FreeTile("Tile108", 581, 581));
        boardTiles.add(109, new FreeTile("Tile109", 741, 581));
        boardTiles.add(110, new FreeTile("Tile110", 849, 581));
        boardTiles.add(111, new FreeTile("Tile111", 957, 581));
        boardTiles.add(112, new ChanceTile(1065, 581, Track.Inner));
        boardTiles.add(113, new FreeTile("Tile113", 1173, 581));
        boardTiles.add(114, new FreeTile("Tile114", 1333, 581));
        boardTiles.add(115, new FreeTile("Tile115", 1333, 741));
        boardTiles.add(116, new FreeTile("Tile116", 1333, 849));
        boardTiles.add(117, new TransitStation(1333, 957, Track.Center));
        boardTiles.add(118, new FreeTile("Tile118", 1333, 1065));
        boardTiles.add(119, new FreeTile("Tile119", 1333, 1173));


        int[][] circles = {{0, 39}, {40, 95}, {96, 119}};
        for (int[] circle : circles) {
            boardTiles.get(circle[0]).setNextTile(true, boardTiles.get(circle[0] + 1));
            boardTiles.get(circle[0]).setNextTile(false, boardTiles.get(circle[1]));
            boardTiles.get(circle[1]).setNextTile(true, boardTiles.get(circle[0]));
            boardTiles.get(circle[1]).setNextTile(false, boardTiles.get(circle[1] - 1));
            for (int j = circle[0] + 1; j <= circle[1] - 1; ++j) {
                boardTiles.get(j).setNextTile(true, boardTiles.get(j + 1));
                boardTiles.get(j).setNextTile(false, boardTiles.get(j - 1));
            }
        }
        ((TransitStation) boardTiles.get(5)).setOtherEnd((TransitStation) boardTiles.get(47));
        ((TransitStation) boardTiles.get(47)).setOtherEnd((TransitStation) boardTiles.get(5));
        ((TransitStation) boardTiles.get(15)).setOtherEnd((TransitStation) boardTiles.get(105));
        ((TransitStation) boardTiles.get(105)).setOtherEnd((TransitStation) boardTiles.get(15));
        ((TransitStation) boardTiles.get(25)).setOtherEnd((TransitStation) boardTiles.get(75));
        ((TransitStation) boardTiles.get(75)).setOtherEnd((TransitStation) boardTiles.get(25));
        ((TransitStation) boardTiles.get(35)).setOtherEnd((TransitStation) boardTiles.get(117));
        ((TransitStation) boardTiles.get(117)).setOtherEnd((TransitStation) boardTiles.get(35));
      /*   boardTiles.add(0,new FreeTile("name",10,10));
        boardTiles.set(0, new Go());

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
        boardTiles.set(119, new ChanceTile());*/
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
     *
     * @return the number of tiles on the board, i.e the size of the boardTiles list.
     */
    public int getNumberOfTiles() {
        return boardTiles.size();
    } //made public for testing

    /**
     * Returns the position of a specified tile on the board.
     *
     * @return the position of the tile if the tile exists on the board, -1 otherwise.
     */
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
     *
     * @return the tile at the "position"th index of the boardTiles list.
     */
    public Tile getTileAtPosition(int position) {
        return boardTiles.get(position);
    } //made public for testing

    /**
     * Returns the tile, indicated number of steps after an indicated starting point.
     *
     * @return the tile numberOfSteps steps after curTile.
     */
    public Tile getNextTile(Tile curTile, boolean direction, int numberOfSteps, boolean takeRailRoads) {
        while (numberOfSteps > 0) {
            curTile = moveStep(curTile, direction, takeRailRoads);
            --numberOfSteps;
        }
        return curTile;
    }

    public Tile getTileById(int ID) {
        for (Tile tile : boardTiles) {
            if (tile.getID() == ID) {
                return tile;
            }
        }
        assert (false);
        return null;
    }

    public ArrayList<ArrayList<Integer>> getPath(Tile from, Tile to, boolean direction, boolean takeRailRoads) {
        ArrayList<Integer> xArr = new ArrayList<>();
        ArrayList<Integer> yArr = new ArrayList<>();
        Tile cur = from;
        while (!cur.equals(to)) {
            xArr.add(cur.getX());
            yArr.add(cur.getY());
            cur = moveStep(cur, direction, takeRailRoads);
        }
        xArr.add(to.getX());
        yArr.add(to.getY());

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        res.add(xArr);
        res.add(yArr);
        return res;
    }

    /**
     * Returns the jail tile.
     *
     * @return the jail tile on the board.
     */
    public Tile getJailTile() {
        return boardTiles.get(10);
    }

    public Pool getPool() {
        return poolTile;
    }

    /**
     * Return the starting tile, namely the "GO" Tile.
     *
     * @return the tile at the initial position, i.e. 0th index of the boardTiles list.
     */
    public Tile getInitialTile() {
        return boardTiles.get(0);
    }

    private Tile moveStep(Tile curTile, boolean direction, boolean takeRailRoads) {
        if (curTile instanceof TransitStation) {
            return ((TransitStation) curTile).passThroughTunnel(direction, takeRailRoads);
        }
        return curTile.getNextTile(direction);
    }

    public Tile nextUnownedProperty(Tile curTile, boolean direction, boolean takeRailRoads) {
        Tile startTile = curTile;
        do {
            if (curTile instanceof Property && ((Property) curTile).getOwner() == null) {
                return curTile;
            }
            curTile = moveStep(curTile, direction, takeRailRoads);
        } while (!startTile.equals(curTile));
        return null;
    }

    public Tile nextRentableProperty(Tile curTile, IPlayer player, boolean direction, boolean takeRailRoads) {
        Tile startTile = curTile;
        do {
            if (curTile instanceof Property && !((Property) curTile).getOwner().equals(player)) {
                return curTile;
            }
            curTile = moveStep(curTile, direction, takeRailRoads);
        } while (!startTile.equals(curTile));
        return null;
    }

    public Tile nextNearestCommunityChestOrChanceTile(Tile curTile, boolean direction, boolean takeRailRoads) {
        Tile startTile = curTile;
        do {
            if (curTile instanceof CommunityChestTile || curTile instanceof ChanceTile) {
                return curTile;
            }
            curTile = moveStep(curTile, direction, takeRailRoads);
        } while (!startTile.equals(curTile));
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
            nc.sendCommand(new PayRentCommand(player.getID(), rent));
            nc.sendCommand(new GainMoneyCommand(property.getOwner().getID(), rent));

            String message = player.getName() + " has paid a rent to " + property.getOwner().getName();
            nc.sendCommand(new AnnounceMessageCommand(message));
        }
    }

    public Tile getTileAtIndex(int idx) {
        return boardTiles.get(idx);
    }

    public void handleTile(Player player, Tile destTile) {
        String message = "";
        NetworkController nc = Network.getInstance().getNetworkController();
        GameController controller = Game.getInstance().getGameController();
        if (destTile instanceof Property) {
            handleProperty(player, (Property) destTile);
        } else if (destTile instanceof Jail) {
            message = player.getName() + " has got into jail!";
            nc.sendCommand(new ChangeJailStatusCommand(player.getID(), true));
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
        nc.sendCommand(new PayRentCommand(player.getID(), upgradeAmount));
        nc.sendCommand(new UpgradePropertyCommand(property.getID()));
        String message = "Building on " + property.getName() + " has been upgraded";
        nc.sendCommand(new AnnounceMessageCommand(message));
    }

    /**
     * This method will only be called when it's possible to do so
     *
     * @param player   the player to buy the property
     * @param property the property to be bought by the player
     */
    public void buyProperty(Player player, Property property) {
        player.loseMoney(property.getPrice());
        player.ownProperty(property);
        property.setOwner(player);
        String message = player.getName() + " has bought " + property.getName();
        NetworkController nc = Network.getInstance().getNetworkController();
        nc.sendCommand(new AnnounceMessageCommand(message));
    }

    public boolean repOK() {
        return _instance != null;
    }

    public String toString() {
        return "Board{" +
                "" +
                "}";
    }
}
