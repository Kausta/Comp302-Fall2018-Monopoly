/*
 * TODO: add endTurn function that calls the endTurn in the game
 */
package cabernet1.monopoly.domain;

import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.property.Property;
import cabernet1.monopoly.domain.game.command.*;
import cabernet1.monopoly.domain.game.die.RegularDie;
import cabernet1.monopoly.domain.game.die.SpeedDie;
import cabernet1.monopoly.domain.game.die.cup.NormalDiceCup;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.game.player.enumerators.PlayerMovementStatus;
import cabernet1.monopoly.domain.network.command.PauseCommand;
import cabernet1.monopoly.domain.network.command.ResumeCommand;
import cabernet1.monopoly.lib.persistence.GameSaver;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;
import cabernet1.monopoly.utils.Observable;

import javax.swing.*;
import java.io.File;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GameController implements Serializable {
    private static final long serialVersionUID = 8999488415439250201L;
    // To add announcements to UI
    public ArrayList<Observable<Boolean>> interactableObservableList = new ArrayList<>();
    public ArrayList<Observable<Boolean>> disabledObservableList = new ArrayList<>();
    public final Observable<String> announcement = new Observable<>();
    public final Observable<String> chat = new Observable<>();
    public final Observable<Integer> die1Observable = new Observable<>();
    public final Observable<Integer> die2Observable = new Observable<>();
    public final Observable<Integer> speedDieObservable = new Observable<>();
    public final Observable<MovePlayerObservableInfo> movePlayerObservable = new Observable<>();
    public final Observable<Boolean> upgradeButton = new Observable<>();
    public final Observable<Boolean> buyButton = new Observable<>();
    public final Observable<Boolean> specialButton = new Observable<>();
    public final Observable<Boolean> endButton = new Observable<>();
    public final Observable<Boolean> rollButton = new Observable<>();
    public final Observable<Boolean> resumeButton = new Observable<>();
    public final Observable<Boolean> pauseButton = new Observable<>();
    public final Observable<Boolean> saveButton = new Observable<>();
    public final Observable<IPlayer> playerObserver = new Observable<>();
    public final Observable<ArrayList<IPlayer>> playerListObservable = new Observable<>();
    public final Observable<ArrayList<Tile>> tileListObservable = new Observable<>();
    private final RegularDie die1 = NormalDiceCup.getInstance().die1;
    private final RegularDie die2 = NormalDiceCup.getInstance().die2;
    private final SpeedDie die3 = NormalDiceCup.getInstance().die3;
    private final Logger logger = LoggerFactory.getInstance().getLogger(getClass());
    private final Observable<String[]> showPlayerSelect = new Observable<String[]>();
    private final Observable<Object[]> colorSelect = new Observable<>();

    public GameController() {
        updateInfoObservables();
        initializeInteractableObservableList();
        logger.i("Created Game Controller");
    }

    public IPlayer getCurrentPlayer() {
        return Game.getInstance().getCurrentPlayer();
    }

    public void announceMessage(String message) {
        announcement.setValue(message);
    }

    public void rollDice() {
        IPlayer currentPlayer = getCurrentPlayer();
        logger.d("current player is "+currentPlayer+" and he is inJail: "+currentPlayer.isInJail());
        if (currentPlayer.isInJail()) {
            currentPlayer.playJailturn();
        } else {
            currentPlayer.rollDice();
        }
        // showDiceValue();
        rollButton.setValue(false);
    }

    public IPlayer getPlayer(int ID) {
        List<IPlayer> players = playerList();
        for (IPlayer player : players) {
            if (player.getID() == ID) {
                return player;
            }
        }
        throw new RuntimeException("Player with given ID not found!");
    }

    private Tile getTile(int ID) {
        return Board.getInstance().getTileById(ID);
    }

    public void chooseTile(IPlayer player) {
        // TODO implement the chooseTile method
        // call the chooseTile method in the UI using observer
    }

    public void showDiceValue() {
        NetworkController nc = Network.getInstance().getNetworkController();
        nc.sendCommand(new AnnounceMessageCommand("The dice result is " + NormalDiceCup.getInstance().getFacesValue()));
        nc.sendCommand(new showDiceFacesCommand(die1.getDiceValue().getValue(), die2.getDiceValue().getValue()
                , die3.getAbsoluteDieValue()));
    }

    public void showDiceFaces(int die1Value, int die2Value, int die3Value) {
        die1Observable.setValue(die1Value);
        die2Observable.setValue(die2Value);
        speedDieObservable.setValue(die3Value);
    }

    public void movePlayer(int playerId, int newTileId, boolean takeRailRoads) {
        logger.d("move player command received, player id is, newTiledId is "+playerId+" "+newTileId);
        if (movePlayerObservable==null){
            logger.d("ERRor empty obersvable");
        }
        MovePlayerObservableInfo info=new MovePlayerObservableInfo(getTile(newTileId), takeRailRoads,false);
        logger.d("created observable info");
        movePlayerObservable.setValue(info);
        playerListObservable.setValue(playerList());
        logger.d("move player command changed value");
        getPlayer(playerId).setCurrentTile(getTile(newTileId));
        logger.d("move player command finished");
    }
    public void jumpPlayer(int playerId,int newTileId){
        movePlayerObservable.setValue(new MovePlayerObservableInfo(getTile(newTileId), false,true));
        getPlayer(playerId).setCurrentTile(getTile(newTileId));
    }
    public void jumpToTile(IPlayer player, Tile newTile) {
        player.jumpToTile(newTile);
    }

    public void changeCurrentTile(IPlayer player, Tile newTile) {
        player.setCurrentTile(newTile);
    }

    public void changeJailStatus(int playerId, boolean inJail) {
        getPlayer(playerId).changeJailStatus(inJail);
    }

    public void changeMovementStatus(int playerId, PlayerMovementStatus status) {
        getPlayer(playerId).setMovementStatus(status);
    }

    public void increaseNumberOfConsecutiveDoubleRolls(int playerId) {
        getPlayer(playerId).increaseNumberOfConsecutiveDoublesRolls();
    }

    public void playTurn() {
        getCurrentPlayer().rollDice();
    }

    public void playerPayRent(int playerId, int rentAmount) {
        getPlayer(playerId).payRent(rentAmount);
    }

    public void playerGainMoney(int playerId, int amount) {
        getPlayer(playerId).gainMoney(amount);
    }

    public void endTurn() {
        Game.getInstance().endTurn();
    }

    public void nextTurn() {
        Game.getInstance().nextTurn();
    }

    public void enableUpgradeBuilding() {
        upgradeButton.setValue(true);
        buyButton.setValue(false);
    }

    public void upgradeBuilding() {
        Board.getInstance().upgradeBuilding(getCurrentPlayer(), (GroupColoredProperty) getCurrentPlayer().getCurrentTile());
    }

    public void activateBuyProperty(int tileID) {
        Board.getInstance().buyProperty(getCurrentPlayer(), tileID);
        playerListObservable.setValue(playerList());
        tileListObservable.setValue(Board.getInstance().getBoardTiles());
    }

    public void buyProperty() {
        NetworkController nc = Network.getInstance().getNetworkController();
        nc.sendCommand(new BuyPropertyCommand(getCurrentPlayer().getCurrentTile().getID()));
    }

    //initial states are disabled.
    public void enableBuyProperty() {
        buyButton.setValue(true);
        upgradeButton.setValue(false);
    }
    // All the enableX methods below are set to update the observer with "true" assuming the specified buttons'

    public void enableSpecialAction() {
        specialButton.setValue(true);

    }
    public void finishedMovingPlayer(){
        logger.i("Finished moving players");
        //TODO execute when on current device
        IPlayer player = getCurrentPlayer();
        if (player.isOnThisDevice()) {
            player.handleTile(player.getCurrentTile(), Board.getInstance());
        }
    }
    public void finishedRollingDice(){
        IPlayer player = getCurrentPlayer();
        if (player.isOnThisDevice()) {
            player.playTurn();
        }
    }
    public void enableEndTurn() {
        endButton.setValue(true);
    }

    public void enableRollDice() {
        rollButton.setValue(true);
    }

    public void disableUpgradeBuilding() {
        rollButton.setValue(false);
    }

    public void disableBuyProperty() {
        buyButton.setValue(false);
    }

    public void disableSpecialAction() {
        specialButton.setValue(false);
    }

    public void disableEndTurn() {
        endButton.setValue(false);
    }

    public void disableRollDice() {
        rollButton.setValue(false);
    }

    public void playerInfo(IPlayer player) {
        playerObserver.setValue(player);
    }

    public ArrayList<IPlayer> playerList() {
        return Game.getInstance().getPlayers();
    }

    public void increasePool(int amount) {
        Board.getInstance().getPool().addMoney(amount);
    }

    public void completeUpgradeBuilding(int propertyId) {
        ((GroupColoredProperty) getTile(propertyId)).upgrade();
    }

    public void downgradeBuilding(int propertyId){
        ((GroupColoredProperty) getTile(propertyId)).downgrade();
        updateInfoObservables();

    }

    public void updateInfoObservables() {
        playerListObservable.setValue(playerList());
        tileListObservable.setValue(Board.getInstance().getBoardTiles());
    }

    private void initializeInteractableObservableList() {
        interactableObservableList.add(upgradeButton);
        interactableObservableList.add(buyButton);
        interactableObservableList.add(specialButton);
        interactableObservableList.add(endButton);
        interactableObservableList.add(rollButton);
        for (Observable<Boolean> o : interactableObservableList) {
            o.setValue(false);
        }
        resumeButton.setValue(false);
        pauseButton.setValue(true);
        saveButton.setValue(false);
    }

    public Observable<String[]> getShowPlayerSelect() {
        return showPlayerSelect;
    }

    private IPlayer target = null;
    public void continueHurricane1(int selectedOption) {
        ArrayList<IPlayer> playerList = playerList();
        target = playerList().get((selectedOption + playerList.size()) % playerList.size());
        HashSet<Property> targetProperty = target.getOwnedProperty();
        HashSet<ColorGroup> colors = new HashSet<ColorGroup>();
        for(Property p: targetProperty)
            if(((GroupColoredProperty)p).getHouse().exists())
                colors.add(((GroupColoredProperty)p).getColorGroup());
        Object[] targetColors = colors.toArray();
        colorSelect.setValue(targetColors);
    }

    public Observable<Object[]> getColorSelect() {
        return colorSelect;
    }

    public void finishHurricane(int selectedOption) {
        NetworkController nc = Network.getInstance().getNetworkController();
        Object[] targetColors = colorSelect.getValue();
        ColorGroup targetColor = (ColorGroup) targetColors[(selectedOption+targetColors.length) % targetColors.length];
        //decrease upgrade levels of all the tiles owned by target player in selected color
        List<Tile> boardTiles = Board.getInstance().getBoardTiles();
        for(Tile t: boardTiles){
            if(t instanceof GroupColoredProperty){
                GroupColoredProperty tile = (GroupColoredProperty)t;
                if(tile.getColorGroup().equals(targetColor) && tile.getOwner().getID()==target.getID()) {
                    nc.sendCommand(new DowngradePropertyCommand(tile.getID()));
                }
            }
        }

        //broadcast message to network
        nc.sendCommand(new AnnounceMessageCommand("Hurricane wrecked" + target.getName() +
                "'s properties of color: " + targetColor));
    }

    public static class MovePlayerObservableInfo implements Serializable {
        private static final long serialVersionUID = 1495053868551960438L;
        public Tile tile;
        public boolean takeRailRoads;
        public boolean jump;
        private MovePlayerObservableInfo(Tile tile, boolean takeRailRoads,boolean jump) {
            this.tile = tile;
            this.takeRailRoads = takeRailRoads;
            this.jump=jump;
        }
    }

    /**
     * Pauses the game via PauseCommand.
     * Restricts all user interactions on UI.
     */
    public void pauseGame() {
        Network.getInstance().getNetworkController().sendCommand(new PauseCommand());
    }

    /**
     * Resumes the game via ResumeCommand.
     * Enables all UI elements which is disabled when the game is paused.
     */
    public void resumeGame() {
        Network.getInstance().getNetworkController().sendCommand(new ResumeCommand());
    }

    public void sendChatMessage(String rawMessage, String message) {
        Network.getInstance().getNetworkController().sendCommand(new SendChatMessageCommand(rawMessage, message));
    }

    public boolean canBeUpgraded(ColorGroup color, GroupColoredProperty p) {
        ArrayList<GroupColoredProperty> a = Board.getInstance().groupedColorGroupProperties.get(color);
        IPlayer owner = p.getOwner();
        for (GroupColoredProperty g : a) {
            if (owner != g.getOwner()) {
                return false;
            }
        }
        int level = getPropertyLevel(p);
        if (level == 3) {
            return false;
        } else {
            for (GroupColoredProperty g : a) {
                int tmpLevel = getPropertyLevel(g);
                if (level == tmpLevel || tmpLevel - level == 1) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public int getPropertyLevel(GroupColoredProperty p) {
        int level = -1;
        if (p.getHouse().limitReached()) {
            if (p.getHotel().limitReached()) {
                if (p.getSkyscraper().limitReached()) {
                    level = 3;
                } else {
                    level = 2;
                }
            } else {
                level = 1;
            }
        } else {
            level = 0;
        }
        return level;
    }

    /**
     * Saves the game using the persistence classes
     */
    public void saveGame(File saveFile) {
        GameSaver.getInstance().saveToFile(Paths.get(saveFile.getAbsolutePath()));
    }

    public void startGame() {
        if(getCurrentPlayer().isOnThisDevice()) {
            rollButton.setValue(true);
            System.out.println("I'm here");
        }
        else {
            rollButton.setValue(false);
        }
    }
}
