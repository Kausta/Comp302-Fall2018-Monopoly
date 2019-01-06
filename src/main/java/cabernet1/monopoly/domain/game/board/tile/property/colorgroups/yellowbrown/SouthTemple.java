package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.yellowbrown;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class SouthTemple extends GroupColoredProperty {

  private static final long serialVersionUID = 6350979807568321174L;

  public SouthTemple(int x, int y) {
    super("South Temple", 330, x, y, 130, ColorGroup.YellowBrown,
            200, 100, new ArrayList<Integer>() {
              private static final long serialVersionUID = -1491657204214116326L;

              {
                add(32);
                add(160);
                add(470);
                add(1050);
                add(1250);
              }
            },
            1000, 500, 1500,
            1200, 600, 2500,
            Track.Outer);
  }
}