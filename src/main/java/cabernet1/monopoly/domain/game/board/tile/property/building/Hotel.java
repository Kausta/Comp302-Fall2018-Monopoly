package cabernet1.monopoly.domain.game.board.tile.property.building;

public class Hotel extends Building {
    private int rent;

    public Hotel(int price, int sellPrice, int rent) {
        super(price, sellPrice, "hotel", "hotels");
        this.rent = rent;
        limit = 1;
    }

    @Override
    public int getRent() {
        return rent;
    }

    public boolean repOk() {
        return super.repOK() && rent > 0;
    }
}
