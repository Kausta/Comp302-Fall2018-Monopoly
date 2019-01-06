package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.darkred;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class RodeoDrive extends GroupColoredProperty {

  private static final long serialVersionUID = 411728843484926859L;

  public RodeoDrive(int x, int y) {
    super("Rodeo Drive", 510, x, y, 175, ColorGroup.DarkRed,
            300, 150, new ArrayList<Integer>() {
              private static final long serialVersionUID = -8158674045859754126L;

              {
                add(70);
                add(350);
                add(750);
                add(1600);
                add(1850);
              }
            },
            1500, 750, 2100,
            1800, 900, 3600);
  }
}
