package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.darkblue;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class ParkPlace extends GroupColoredProperty {

  private static final long serialVersionUID = -7347486596044188212L;

  public ParkPlace(int x, int y) {
    super("Park Place", 350, x, y, 200, ColorGroup.DarkBlue,
            200, 100, new ArrayList<Integer>() {
              private static final long serialVersionUID = 8208199850471352730L;

              {
                add(35);
                add(175);
                add(500);
                add(1100);
                add(1300);
              }
            },
            1000, 500, 1500,
            1200, 600, 2500);
  }
}
