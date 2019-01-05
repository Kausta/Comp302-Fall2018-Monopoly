package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.palegreen;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class PeachtreeStreet extends GroupColoredProperty {

  private static final long serialVersionUID = 1598209419478760434L;

  public PeachtreeStreet(int x, int y) {
    super("Peachtree Street", 240, x, y, 90, ColorGroup.PaleGreen,
            100, 50, new ArrayList<Integer>() {
              private static final long serialVersionUID = -4332238811132631005L;

              {
                add(17);
                add(85);
                add(240);
                add(670);
                add(840);
              }
            },
            500, 250, 1025,
            600, 300, 1525);
  }
}
