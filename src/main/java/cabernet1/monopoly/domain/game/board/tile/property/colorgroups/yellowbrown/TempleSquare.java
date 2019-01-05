package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.yellowbrown;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class TempleSquare extends GroupColoredProperty {

  private static final long serialVersionUID = -5577883067707500766L;

  public TempleSquare(int x, int y) {
    super("Temple Square", 360, x, y, 130, ColorGroup.YellowBrown,
            200, 100, new ArrayList<Integer>() {
              private static final long serialVersionUID = -5488063206990756148L;

              {
                add(32);
                add(160);
                add(470);
                add(1050);
                add(1250);
              }
            },
            1000, 500, 1500,
            1200, 600, 2500);
  }
}