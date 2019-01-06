package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.grey;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class MadisonAvenue extends GroupColoredProperty {

  private static final long serialVersionUID = -2411370364671902463L;

  public MadisonAvenue(int x, int y) {
    super("Madison Avenue", 430, x, y, 215, ColorGroup.Grey,
            300, 150, new ArrayList<Integer>() {
              private static final long serialVersionUID = -9091856425632695145L;

              {
                add(60);
                add(220);
                add(650);
                add(1500);
                add(1800);
              }
            },
            1500, 750, 2100,
            1800, 900, 3600);
  }
}
