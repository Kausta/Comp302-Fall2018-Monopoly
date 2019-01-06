package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.red;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

import java.util.ArrayList;

public class IllinoisAvenue extends GroupColoredProperty {

  private static final long serialVersionUID = 7935706472302835368L;

  public IllinoisAvenue(int x, int y) {
    super("Illinois Avenue", 240, x, y, 100, ColorGroup.Red,
            150, 75, new ArrayList<Integer>() {
              private static final long serialVersionUID = -9212601237803913800L;

              {
                add(18);
                add(90);
                add(250);
                add(700);
                add(875);
              }
            },
            750, 375, 1050,
            900, 450, 2050,
            Track.Center);
  }
}