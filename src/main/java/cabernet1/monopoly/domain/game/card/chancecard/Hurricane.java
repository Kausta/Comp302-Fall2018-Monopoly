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

    private static final long serialVersionUID = -252667589202448424L;

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
        Game.getInstance().getGameController().getShowPlayerSelect().setValue(names);
    }
}
