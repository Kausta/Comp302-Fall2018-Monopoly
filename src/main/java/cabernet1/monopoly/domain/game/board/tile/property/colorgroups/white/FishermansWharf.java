package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.white;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class FishermansWharf extends GroupColoredProperty {

  private static final long serialVersionUID = -4754287775290694493L;

  public FishermansWharf(int x, int y) {
    super("Fisherman's Wharf", 250, x, y, 105, ColorGroup.White,
            100, 50, new ArrayList<Integer>() {
              private static final long serialVersionUID = 775967303693432011L;

              {
                add(17);
                add(85);
                add(240);
                add(475);
                add(670);
              }
            },
            500, 250, 1025,
            600, 300, 1525);
  }
}