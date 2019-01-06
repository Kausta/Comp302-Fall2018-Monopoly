package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.palegreen;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class DekalbAvenue extends GroupColoredProperty {

  private static final long serialVersionUID = 4750367056575734939L;

  public DekalbAvenue(int x, int y) {
    super("Dekalb Avenue", 210, x, y, 90, ColorGroup.PaleGreen,
            100, 50, new ArrayList<Integer>() {
              private static final long serialVersionUID = 4426880654494994170L;

              {
                add(17);
                add(85);
                add(240);
                add(670);
                add(840);
              }
            },
            500, 250, 1025,
            600, 300, 1525,
            Track.Outer);
  }
}
