package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.brown;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class MiamiAvenue extends GroupColoredProperty

  {

  private static final long serialVersionUID = 3540407163033963716L;

    public MiamiAvenue(int x, int y) {
    super("Miami Avenue", 130, x, y, 75, ColorGroup.Brown,
            50, 25, new ArrayList<Integer>() {
              private static final long serialVersionUID = 5346912913735602946L;

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
