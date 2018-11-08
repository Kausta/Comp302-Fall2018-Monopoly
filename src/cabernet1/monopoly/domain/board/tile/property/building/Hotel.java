package cabernet1.monopoly.domain.board.tile.property.building;

public class Hotel extends Building{
    int rent;

    public Hotel(int price, int sellPrice, int rent){
        super(price, sellPrice);
        this.rent = rent;
        limit = 1;
    }

    @Override
    public int getRent(){
        return rent;
    }
}