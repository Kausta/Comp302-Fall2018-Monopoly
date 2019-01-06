package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.grey;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

import java.util.ArrayList;

public class WallStreet extends GroupColoredProperty {

  private static final long serialVersionUID = -5170239217598080665L;

  public WallStreet(int x, int y) {
    super("Wall Street", 500, x, y, 215, ColorGroup.Grey,
            300, 150, new ArrayList<Integer>() {
              private static final long serialVersionUID = -4904682176208180715L;

              {
                add(60);
                add(220);
                add(650);
                add(1500);
                add(1800);
              }
            },
            1500, 750, 2100,
            1800, 900, 3600,
            Track.Inner);
  }
}
