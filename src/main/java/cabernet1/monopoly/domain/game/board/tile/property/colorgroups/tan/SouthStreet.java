package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.tan;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class SouthStreet extends GroupColoredProperty {

  private static final long serialVersionUID = -4935661060623998031L;

  public SouthStreet(int x, int y) {
    super("South Street", 390, x, y, 150, ColorGroup.Tan,
            250, 125, new ArrayList<Integer>() {
              private static final long serialVersionUID = 4537125841544336882L;

              {
                add(45);
                add(210);
                add(575);
                add(1300);
                add(1600);
              }
            },
            1250, 675, 1800,
            1500, 750, 3300,
            Track.Outer);
  }
}