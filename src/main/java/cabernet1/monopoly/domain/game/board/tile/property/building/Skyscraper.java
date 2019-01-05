package cabernet1.monopoly.domain.game.board.tile.property.building;

public class Skyscraper extends Building {
    private static final long serialVersionUID = -2882801801826119478L;
    private final int rent;

    public Skyscraper(int price, int sellPrice, int rent) {
        super(price, sellPrice, "skyscraper", "skyscrapers");
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
