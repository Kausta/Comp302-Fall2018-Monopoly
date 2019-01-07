package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.green;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

import java.util.ArrayList;

public class PennsylvaniaAvenue extends GroupColoredProperty {

  private static final long serialVersionUID = 740239979505684260L;

  public PennsylvaniaAvenue(int x, int y) {
    super("Pennsylvania Avenue", 320, x, y, 150, ColorGroup.Green,
            200, 100, new ArrayList<Integer>() {
              private static final long serialVersionUID = 4236367489365975990L;

              {
                add(26);
                add(130);
                add(390);
                add(900);
                add(1100);
              }
            },
            1000, 500, 1275,
            1200, 600, 2275,
            Track.Center);
  }
}
