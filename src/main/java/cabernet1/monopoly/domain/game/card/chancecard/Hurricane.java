package cabernet1.monopoly.domain.game.card.chancecard;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.NetworkController;
import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.property.Property;
import cabernet1.monopoly.domain.game.card.IimmediateAction;
import cabernet1.monopoly.domain.game.command.AnnounceMessageCommand;
import cabernet1.monopoly.domain.game.command.GainMoneyCommand;
import cabernet1.monopoly.domain.game.player.IPlayer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Hurricane extends ChanceCard implements IimmediateAction{

    public Hurricane(){super("Hurricane");}

    @Override
    public void action(IPlayer player){
        GameController gc = Game.getInstance().getGameController();
        NetworkController nc = Network.getInstance().getNetworkController();
        ArrayList<IPlayer> players = gc.playerList();
        players.remove(player);
        for(int i=0; i<players.size(); i++){
            if(players.get(i).getOwnedProperty().size() == 0){
                players.remove(i);
                i--;
            }
        }
        if(players.size() == 0)
        {
            nc.sendCommand(new AnnounceMessageCommand("Hurricane card can't be used as no player owns property!"));
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
        )+targetColors.length) % targetColors.length    ];

        //decrease upgrade levels of all the tiles in selected color

        //broadcast message to network
        nc.sendCommand(new GainMoneyCommand(target.getID(), 100));
        nc.sendCommand(new AnnounceMessageCommand("Hurricane wrecked" + target.getName() +
                "'s properties of color: " + targetColor));
    }
}
