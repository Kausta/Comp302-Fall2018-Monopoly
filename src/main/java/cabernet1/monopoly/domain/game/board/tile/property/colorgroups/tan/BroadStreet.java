package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.tan;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class BroadStreet extends GroupColoredProperty {

  private static final long serialVersionUID = -4211843912201436164L;

  public BroadStreet(int x, int y) {
    super("Broad Street", 390, x, y, 150, ColorGroup.Tan,
            250, 125, new ArrayList<Integer>() {
              private static final long serialVersionUID = -4151859180611117462L;

              {
                add(45);
                add(210);
                add(575);
                add(1300);
                add(1600);
              }
            },
            1250, 675, 1800,
            1500, 750, 3300);
  }
}