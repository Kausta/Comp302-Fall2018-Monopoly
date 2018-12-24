package cabernet1.monopoly.domain.game.board.tile.property.building;

public class Hotel extends Building {
    private static final long serialVersionUID = 5154645883176335341L;
    private final int rent;

    public Hotel(int price, int sellPrice, int rent) {
        super(price, sellPrice, "hotel", "hotels");
        this.rent = rent;
        limit = 1;
    }

    @Override
    public int getRent() {
        return rent;
    }

    @Override
    public boolean repOK() {
        return super.repOK() && rent > 0;
    }
}
