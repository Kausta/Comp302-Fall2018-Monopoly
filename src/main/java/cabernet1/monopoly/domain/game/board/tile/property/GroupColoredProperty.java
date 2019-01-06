package cabernet1.monopoly.domain.game.board.tile.property;

import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.NetworkController;
import cabernet1.monopoly.domain.game.Constants;
import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.board.tile.property.building.Hotel;
import cabernet1.monopoly.domain.game.board.tile.property.building.House;
import cabernet1.monopoly.domain.game.board.tile.property.building.Skyscraper;
import cabernet1.monopoly.domain.game.command.AnnounceMessageCommand;
import sun.nio.ch.Net;

import java.util.ArrayList;

public class GroupColoredProperty extends Property {
    private static final long serialVersionUID = 1582062981898061771L;
    private final ColorGroup color;

    private final int mortgageValue;
    private final House house;
    private final Hotel hotel;
    private final Skyscraper skyscraper;


    public GroupColoredProperty(String name, int price, int x, int y, int mortgageValue, ColorGroup color,
                                int housePrice, int houseSellPrice, ArrayList<Integer> houseRents,
                                int hotelPrice, int hotelSellPrice, int hotelRent,
                                int skyscraperPrice, int skyscraperSellPrice, int skyscraperRent) {
        super(name, TileType.ColoredGroupProperty, price, x, y);
        this.mortgageValue = mortgageValue;
        this.color = color;
        house = new House(housePrice, houseSellPrice, houseRents);
        hotel = new Hotel(hotelPrice, hotelSellPrice, hotelRent);
        skyscraper = new Skyscraper(skyscraperPrice, skyscraperSellPrice, skyscraperRent);
        if(Board.getInstance().groupedColorGroupProperties.get(color) != null) {
            ArrayList<GroupColoredProperty> a = Board.getInstance().groupedColorGroupProperties.get(color);
            a.add(this);
            Board.getInstance().groupedColorGroupProperties.put(color, a);
        }
        else {
            ArrayList<GroupColoredProperty> a = new ArrayList<>();
            a.add(this);
            Board.getInstance().groupedColorGroupProperties.put(color, a);
        }
    }

    /**
     * Adds a house to the property
     *
     * @return status message to be shown
     * @modifies house
     * @requires to have less than maximum houses
     * @effects house number on the property increases
     */
    public String buyHouse() {
        return house.increaseAmount();
    }

    /**
     * Removes a house from the property
     *
     * @return status message to be shown
     * @modifies house
     * @requires to have more than 0 houses
     * @effects house number on the property decreases
     */
    public String demolishHouse() {
        return house.decreaseAmount();
    }

    /**
     * Adds a hotel to the property
     *
     * @return status message to be shown
     */
    public String buyHotel() {
        return hotel.increaseAmount();
    }

    /**
     * Removes a hotel from the property
     *
     * @return status message to be shown
     */
    public String demolishHotel() {
        return hotel.decreaseAmount();
    }

    /**
     * Adds a skyscraper to the property
     *
     * @return status message to be shown
     */
    public String buySkyScraper() {
        return skyscraper.increaseAmount();
    }

    /**
     * Removes a skyscraper from the property
     *
     * @return status message to be shown
     */
    public String demolishSkyscraper() {
        return skyscraper.decreaseAmount();
    }

    /**
     * @return The mortgage Value
     */
    public int getMortgageValue() {
        return mortgageValue;
    }

    /**
     * @return the current rent for landing on the tile
     */
    public int getRent() {
        if (skyscraper.exists()) {
            return skyscraper.getRent();
        } else if (hotel.exists()) {
            return hotel.getRent();
        } else {
            return house.getRent();
        }
    }

    /**
     * @return the color group of the tile
     */
    public ColorGroup getColorGroup() {
        return color;
    }

    public int getUpgradeAmount() {
        if (house.limitReached()) {
            if (hotel.limitReached()) {
                if (skyscraper.limitReached()) {
                    return Constants.INF;
                }
                return skyscraper.getPrice();
            }
            return hotel.getPrice();
        }
        return house.getPrice();
    }

    /**
     * upgrades the buildings
     *
     * @modifies house, hotel or skyscraper
     * @requires to not have a skyscraper
     * @effects increases the house, hotel or the scyscraper number
     */
    public void upgrade() {
        if (house.limitReached()) {
            if (hotel.limitReached()) {
                buySkyScraper();
            }
            buyHotel();
        }
        buyHouse();
    }

    public void downgrade(){
        NetworkController nc = Network.getInstance().getNetworkController();
        String message = "";
        if(skyscraper.limitReached()){
            skyscraper.decreaseAmount();
            message = "Demolished a skyscraper at " + getName();
        }
        else if(hotel.limitReached()){
            hotel.decreaseAmount();
            message = "Demolished a hotel at " + getName();
        } else{
            if(house.exists()){
                house.decreaseAmount();
                message = "Demolished a house at " + getName();
            } else {
                message = "There are no buildings to demolish at " + getName();
            }
        }
        nc.sendCommand(new AnnounceMessageCommand(message));
    }

    public House getHouse() {
        return house;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Skyscraper getSkyscraper() {
        return skyscraper;
    }

    @Override
    public boolean repOK() {
        return super.repOK() && color != null && mortgageValue > 0 && house.repOK() && hotel.repOK() && skyscraper.repOK();
    }

    @Override
    public String toString() {
        return "GroupColoredProperty{ " +
                "color: " + color +
                ", mortgageValue: " + mortgageValue +
                ", house: " + house +
                ", hotel: " + hotel +
                ", skyscraper: " + skyscraper +
                ", " + super.toString() + " }";
    }
}
