package cabernet1.monopoly.domain.game.card.chancecard;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.NetworkController;
import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.property.Property;
import cabernet1.monopoly.domain.game.card.IimmediateAction;
import cabernet1.monopoly.domain.game.command.AnnounceMessageCommand;
import cabernet1.monopoly.domain.game.command.DowngradePropertyCommand;
import cabernet1.monopoly.domain.game.command.GainMoneyCommand;
import cabernet1.monopoly.domain.game.player.IPlayer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Hurricane extends ChanceCard implements IimmediateAction{

    public Hurricane(){super("Hurricane");}

    @Override
    public void action(IPlayer player){
        GameController gc = Game.getInstance().getGameController();
        NetworkController nc = Network.getInstance().getNetworkController();
        ArrayList<IPlayer> players = (ArrayList<IPlayer>) gc.playerList().clone();
        players.remove(player);
        for(int i=0; i<players.size(); i++){
            HashSet<Property> properties = players.get(i).getOwnedProperty();
            if(properties.size() == 0){
                players.remove(i);
                i--;
            }
            boolean flag = false;
            for(Property property: properties) {
                if(property instanceof GroupColoredProperty) {
                    GroupColoredProperty prop = (GroupColoredProperty) property;
                    if(prop.getHouse().getAmount() > 0) {
                       flag = true;
                       break;
                    }
                }
            }
            if(!flag) {
                players.remove(i);
                i--;
            }
        }
        if(players.size() == 0)
        {
            nc.sendCommand(new AnnounceMessageCommand("Hurricane card can't be used as no player owns a property with a house!"));
            return;
        }
        String[] names = new String[players.size()];
        for(int i=0; i<names.length; i++)
            names[i] = players.get(i).getName();
        IPlayer target = players.get((JOptionPane.showOptionDialog(
                null,
                "Choose which player's property you would like to downgrade",
                "Hurricane!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                names,
                null) + players.size()) % players.size());
        HashSet<Property> targetProperty = target.getOwnedProperty();
        HashSet<ColorGroup> colors = new HashSet<ColorGroup>();
        for(Property p: targetProperty)
            if(((GroupColoredProperty)p).getHouse().exists())
                colors.add(((GroupColoredProperty)p).getColorGroup());
        Object[] targetColors = colors.toArray();
        ColorGroup targetColor = (ColorGroup) targetColors[(JOptionPane.showOptionDialog(
                null,
                "Choose which color you want to demolish",
                "Choose Color",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                targetColors,
                null
        )+targetColors.length) % targetColors.length];

        //decrease upgrade levels of all the tiles owned by target player in selected color
        List<Tile> boardTiles = Board.getInstance().getBoardTiles();
        for(Tile t: boardTiles){
            if(t instanceof GroupColoredProperty){
                GroupColoredProperty tile = (GroupColoredProperty)t;
                if(tile.getColorGroup().equals(targetColor) && tile.getOwner().equals(target))
                    nc.sendCommand(new DowngradePropertyCommand(tile.getID()));
            }
        }

        //broadcast message to network
        nc.sendCommand(new AnnounceMessageCommand("Hurricane wrecked" + target.getName() +
                "'s properties of color: " + targetColor));
    }
}
