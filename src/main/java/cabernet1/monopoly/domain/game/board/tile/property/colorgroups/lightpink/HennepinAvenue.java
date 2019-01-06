package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.lightpink;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class HennepinAvenue extends GroupColoredProperty {

  private static final long serialVersionUID = 2048062699289389230L;

  public HennepinAvenue(int x, int y) {
    super("Hennepin Avenue", 60, x, y, 15, ColorGroup.LightPink,
            50, 25, new ArrayList<Integer>() {
              private static final long serialVersionUID = -8298396231200774929L;

              {
                add(1);
                add(5);
                add(15);
                add(45);
                add(80);
              }
            },
            250, 125, 125,
            300, 150, 625);
  }
}
