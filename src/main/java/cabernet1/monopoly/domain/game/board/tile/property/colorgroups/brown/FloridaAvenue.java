package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.brown;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class FloridaAvenue extends GroupColoredProperty

  {

  private static final long serialVersionUID = 2472566799579009547L;

    public FloridaAvenue(int x, int y) {
    super("Florida Avenue", 130, x, y, 75, ColorGroup.Brown,
            50, 25, new ArrayList<Integer>() {
              private static final long serialVersionUID = 1159776250334815492L;

              {
                add(11);
                add(55);
                add(160);
                add(475);
                add(650);
              }
            },
            250, 125, 800,
            300, 150, 1300);
  }
}
