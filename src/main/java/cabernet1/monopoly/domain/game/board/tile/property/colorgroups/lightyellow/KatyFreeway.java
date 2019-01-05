package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.lightyellow;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class KatyFreeway extends GroupColoredProperty {

  private static final long serialVersionUID = -7962776274056912360L;

  public KatyFreeway(int x, int y) {
    super("Katy Freeway", 150, x, y, 70, ColorGroup.LightYellow,
            100, 50, new ArrayList<Integer>() {
              private static final long serialVersionUID = -5061456381087461473L;

              {
                add(11);
                add(55);
                add(160);
                add(475);
                add(650);
              }
            },
            500, 250, 800,
            600, 300, 1300);
  }
}