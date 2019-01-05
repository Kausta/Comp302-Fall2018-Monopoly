package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.tan;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class WalnutStreet extends GroupColoredProperty {

  private static final long serialVersionUID = -1077816792225882324L;

  public WalnutStreet(int x, int y) {
    super("Walnut Street", 420, x, y, 150, ColorGroup.Tan,
            250, 125, new ArrayList<Integer>() {
              private static final long serialVersionUID = 8255026287723151022L;

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