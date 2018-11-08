package cabernet1.monopoly.domain.board.tile.property.building;

public class Skyscraper extends Building{
    int rent;

    public Skyscraper(int price, int sellPrice, int rent){
        super(price, sellPrice);
        this.rent = rent;
        limit = 1;
    }

    @Override
    public int getRent(){
        return rent;
    }
}