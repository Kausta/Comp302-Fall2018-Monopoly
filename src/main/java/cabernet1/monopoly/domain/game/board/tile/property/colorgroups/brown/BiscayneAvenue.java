package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.brown;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class BiscayneAvenue extends GroupColoredProperty

  {

  private static final long serialVersionUID = -4425314184393184046L;

    public BiscayneAvenue(int x, int y) {
    super("Biscayne Avenue", 150, x, y, 75, ColorGroup.Brown,
            50, 25, new ArrayList<Integer>() {
              private static final long serialVersionUID = -5727661035021481549L;

              {
                add(11);
                add(55);
                add(160);
                add(475);
                add(650);
              }
            },
            250, 125, 800,
            300, 150, 1300);
  }
}
