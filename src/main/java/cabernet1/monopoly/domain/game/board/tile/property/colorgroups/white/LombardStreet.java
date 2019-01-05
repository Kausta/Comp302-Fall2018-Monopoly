package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.white;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class LombardStreet extends GroupColoredProperty {

  private static final long serialVersionUID = -3534021112337829155L;

  public LombardStreet(int x, int y) {
    super("Lombard Street", 210, x, y, 105, ColorGroup.White,
            100, 50, new ArrayList<Integer>() {
              private static final long serialVersionUID = -6385818902181858519L;

              {
                add(17);
                add(85);
                add(240);
                add(475);
                add(670);
              }
            },
            500, 250, 1025,
            600, 300, 1525);
  }
}