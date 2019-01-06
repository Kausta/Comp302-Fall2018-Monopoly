package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.grey;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class FifthAvenue extends GroupColoredProperty {

  private static final long serialVersionUID = 6852575890105177299L;

  public FifthAvenue(int x, int y) {
    super("Fifth Avenue", 430, x, y, 215, ColorGroup.Grey,
            300, 150, new ArrayList<Integer>() {
              private static final long serialVersionUID = -7963563195351815757L;

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
