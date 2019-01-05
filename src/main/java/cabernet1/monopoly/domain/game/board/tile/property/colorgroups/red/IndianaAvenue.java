package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.red;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class IndianaAvenue extends GroupColoredProperty {

  private static final long serialVersionUID = -1643486028661179859L;

  public IndianaAvenue(int x, int y) {
    super("Indiana Avenue", 220, x, y, 100, ColorGroup.Red,
            150, 75, new ArrayList<Integer>() {
              private static final long serialVersionUID = 1953504231657967902L;

              {
                add(18);
                add(90);
                add(250);
                add(700);
                add(875);
              }
            },
            750, 375, 1050,
            900, 450, 2050);
  }
}