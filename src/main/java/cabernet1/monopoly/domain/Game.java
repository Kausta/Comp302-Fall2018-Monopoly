package cabernet1.monopoly.domain;

import cabernet1.monopoly.domain.game.Constants;
import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.property.colorgroups.black.BoylstonStreet;
import cabernet1.monopoly.domain.game.board.tile.property.colorgroups.darkblue.Boardwalk;
import cabernet1.monopoly.domain.game.board.tile.property.colorgroups.darkred.MulhollandDrive;
import cabernet1.monopoly.domain.game.board.tile.property.colorgroups.green.NorthCarolinaAvenue;
import cabernet1.monopoly.domain.game.board.tile.property.colorgroups.green.PacificAvenue;
import cabernet1.monopoly.domain.game.board.tile.property.colorgroups.grey.FifthAvenue;
import cabernet1.monopoly.domain.game.board.tile.property.colorgroups.orange.StJamesPlace;
import cabernet1.monopoly.domain.game.board.tile.property.colorgroups.pink.VirginiaAvenue;
import cabernet1.monopoly.domain.game.bot.BotPlayer;
import cabernet1.monopoly.domain.game.command.AnnounceMessageCommand;
import cabernet1.monopoly.domain.game.command.NextTurnCommand;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.game.player.InitialPlayerData;
import cabernet1.monopoly.domain.game.player.Player;
import cabernet1.monopoly.domain.game.player.PlayerFactory;
import cabernet1.monopoly.lib.persistence.Saveable;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Saveable
public class Game implements Serializable {
    private static final long serialVersionUID = -3452240765331746220L;
    private static volatile Game _instance = null;
    private final Logger logger = LoggerFactory.getInstance().getLogger(getClass());
    private GameController controller;
    private List<InitialPlayerData> initialPlayerData;
    private String nextPossibleServer;
    private ArrayList<IPlayer> player;
    private List<String> playersOnDevice;
    private int playerPointer = 0;

    private Game() {
        // Manually initialize the board here
        Board board = Board.getInstance();
        board.initialize();
    }

    public static synchronized Game getInstance() {
        if (_instance == null) {
            _instance = new Game();
        }
        return _instance;
    }

    public void initialize(List<InitialPlayerData> initialPlayerData) {
        this.initialPlayerData = initialPlayerData;
        logger.i("Registering players to the game");
        this.player = initialPlayerData.stream().map(playerData -> {
            logger.i("Registered " + playerData.getName());
            return PlayerFactory.getInstance().createFromInitialData(playerData);
        }).collect(toCollection(ArrayList::new));
        setNextServer();
    }

    public void makeSelfIneligibleForServer(String identifier){
        for(InitialPlayerData i : initialPlayerData){
            if(i.getOrigin().equals(identifier)){
                i.setEligibleForServer(false);
            }
        }
    }

    public void changePlayerToBot(IPlayer p){
        BotPlayer b = (BotPlayer) p;
        for (int i = 0; i < player.size(); i++) {
            if(player.get(i).equals(p)){
                player.remove(i);
                player.add(i, b);
            }
        }
    }

    public void setNextServer(){
        Collections.sort(initialPlayerData);
        for(InitialPlayerData i : initialPlayerData){
            if(i.getEligibleForServer()){
                nextPossibleServer = i.getOrigin();
            }
        }
    }

    public List<String> getPlayersOnDevice() {
        return this.playersOnDevice;
    }

    public String getNextPossibleServer(){
        return this.nextPossibleServer;
    }

    public List<InitialPlayerData> getPlayerData(){
        return this.initialPlayerData;
    }

    public void setPlayersOnDevice(List<String> playersOnDevice) {
        this.playersOnDevice = playersOnDevice;
    }

    public synchronized GameController getGameController() {
        if (controller == null) {
            controller = new GameController();
        }
        return controller;
    }

    public void endTurn() {
        String message = getCurrentPlayer().getName() +
                " turn has ended\n"+ Constants.SEPERATING_lINE+"\n\n\n";

        NetworkController nc = Network.getInstance().getNetworkController();
        nc.sendCommand(new AnnounceMessageCommand(message));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        nc.sendCommand(new NextTurnCommand());
    }

    public void nextTurn() {
        playerPointer = (playerPointer + 1) % player.size();
        configureTurn();
    }

    public void configureTurn() {
        IPlayer player=getCurrentPlayer();
        controller.playerInfo(player);
        String message="Player: "+player.getName()+" will play now\n" + Constants.SEPERATING_lINE+"\n\n";
        controller.announceMessage(message);
        controller.tileListObservable.setValue(Board.getInstance().getBoardTiles());
        if (player instanceof BotPlayer){
            player.playTurn();
        }
    }

    public IPlayer getCurrentPlayer() {
        return player.get(playerPointer);
    }

    public ArrayList<IPlayer> getPlayers() {
        return this.player;
    }

}
