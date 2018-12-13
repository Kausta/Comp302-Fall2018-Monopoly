package cabernet1.monopoly.domain.game.board.tile.property.building;

public class Skyscraper extends Building {
    private int rent;

    public Skyscraper(int price, int sellPrice, int rent) {
        super(price, sellPrice, "skyscraper", "skyscrapers");
        this.rent = rent;
        limit = 1;
    }

    @Override
    public int getRent() {
        return rent;
    }

}
