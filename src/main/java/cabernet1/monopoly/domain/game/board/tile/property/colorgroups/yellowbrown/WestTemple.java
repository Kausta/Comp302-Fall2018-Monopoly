package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.yellowbrown;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class WestTemple extends GroupColoredProperty {

  private static final long serialVersionUID = -2511796609299502429L;

  public WestTemple(int x, int y) {
    super("West Temple", 330, x, y, 130, ColorGroup.YellowBrown,
            200, 100, new ArrayList<Integer>() {
              private static final long serialVersionUID = -7788685275502462243L;

              {
                add(32);
                add(160);
                add(470);
                add(1050);
                add(1250);
              }
            },
            1000, 500, 1500,
            1200, 600, 2500);
  }
}