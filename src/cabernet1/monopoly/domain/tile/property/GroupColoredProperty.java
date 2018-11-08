package cabernet1.monopoly.domain.tile.property;

import java.util.ArrayList;

import cabernet1.monopoly.domain.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.tile.property.building.Hotel;
import cabernet1.monopoly.domain.tile.property.building.House;
import cabernet1.monopoly.domain.tile.property.building.Skyscraper;

public class GroupColoredProperty extends Property {

    public ColorGroup color;

    public int mortgageValue;
    public House house;
    public Hotel hotel;
    public Skyscraper skyscraper;
    
    
    public GroupColoredProperty(String name, int price, int mortgageValue, ColorGroup color,
                                int housePrice, int houseSellPrice, ArrayList<Integer> houseRents,
                                int hotelPrice, int hotelSellPrice, int hotelRent,
                                int skyscraperPrice, int skyscraperSellPrice, int skyscraperRent){
        super(name, price);
        this.mortgageValue = mortgageValue;
        this.color = color;
        house = new House(housePrice, houseSellPrice, houseRents);
        hotel = new Hotel(hotelPrice, hotelSellPrice, hotelRent);
        skyscraper = new Skyscraper(skyscraperPrice, skyscraperSellPrice, skyscraperRent);
    }

    /**
     * Adds a house to the property
     * @return status message to be shown
     */
    public String buyHouse(){
        if(house.limitReached()){
            return "Already at the maximum number of houses.";
        }
        house.increaseAmount();
        return "Successfully purchased the house.";
    }

    /**
     * Removes a house from the property
     * @return status message to be shown
     */
    public String demolishHouse(){
        if(house.exists()){
            house.decreaseAmount();
            return "Successfully demolished the house.";
        }
        return "There are no jouses to demolish";
    }


}