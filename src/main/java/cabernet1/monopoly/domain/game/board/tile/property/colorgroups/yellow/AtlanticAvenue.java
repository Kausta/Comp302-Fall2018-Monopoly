package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.yellow;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class AtlanticAvenue extends GroupColoredProperty {

  private static final long serialVersionUID = 3141127378737190696L;

  public AtlanticAvenue(int x, int y) {
    super("Atlantic Avenue", 260, x, y, 130, ColorGroup.Yellow,
            150, 75, new ArrayList<Integer>() {
              private static final long serialVersionUID = 2249503491460654481L;

              {
                add(22);
                add(110);
                add(330);
                add(800);
                add(975);
              }
            },
            750, 375, 1150,
            900, 450, 2150);
  }
}