package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.green;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class NorthCarolinaAvenue extends GroupColoredProperty {

  private static final long serialVersionUID = 3181038311829799821L;

  public NorthCarolinaAvenue(int x, int y) {
    super("North Carolina Avenue", 300, x, y, 150, ColorGroup.Green,
            200, 100, new ArrayList<Integer>() {
              private static final long serialVersionUID = 5781744593237682589L;

              {
                add(26);
                add(130);
                add(390);
                add(900);
                add(1100);
              }
            },
            1000, 500, 1275,
            1200, 600, 2275);
  }
}
