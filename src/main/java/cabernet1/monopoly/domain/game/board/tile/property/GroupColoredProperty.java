package cabernet1.monopoly.domain.game.board.tile.property;

import cabernet1.monopoly.domain.game.Constants;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.board.tile.property.building.Hotel;
import cabernet1.monopoly.domain.game.board.tile.property.building.House;
import cabernet1.monopoly.domain.game.board.tile.property.building.Skyscraper;

import java.util.ArrayList;

public class GroupColoredProperty extends Property {

    private ColorGroup color;

    private int mortgageValue;
    private House house;
    private Hotel hotel;
    private Skyscraper skyscraper;


    public GroupColoredProperty(String name, int price, int x, int y, int mortgageValue, ColorGroup color,
                                int housePrice, int houseSellPrice, ArrayList<Integer> houseRents,
                                int hotelPrice, int hotelSellPrice, int hotelRent,
                                int skyscraperPrice, int skyscraperSellPrice, int skyscraperRent) {
        super(name, TileType.ColoredGroupProperty, price,x,y);
        this.mortgageValue = mortgageValue;
        this.color = color;
        house = new House(housePrice, houseSellPrice, houseRents);
        hotel = new Hotel(hotelPrice, hotelSellPrice, hotelRent);
        skyscraper = new Skyscraper(skyscraperPrice, skyscraperSellPrice, skyscraperRent);
    }

    /**
     * Adds a house to the property
     *
     * @return status message to be shown
     */
    public String buyHouse() {
        return house.increaseAmount();
    }

    /**
     * Removes a house from the property
     *
     * @return status message to be shown
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

    public void upgrade() {
        if (house.limitReached()) {
            if (hotel.limitReached()) {
                buySkyScraper();
            }
            buyHotel();
        }
        buyHouse();
    }


}
