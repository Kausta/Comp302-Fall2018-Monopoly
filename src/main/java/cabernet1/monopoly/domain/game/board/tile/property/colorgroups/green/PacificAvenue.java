package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.green;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class PacificAvenue extends GroupColoredProperty {

  private static final long serialVersionUID = -9205310390183411698L;

  public PacificAvenue(int x, int y) {
    super("Pacific Avenue", 300, x, y, 150, ColorGroup.Green,
            200, 100, new ArrayList<Integer>() {
              private static final long serialVersionUID = 7015742927868095934L;

              {
                add(26);
                add(130);
                add(390);
                add(900);
                add(1100);
              }
            },
            1000, 500, 1275,
            1200, 600, 2275,
            Track.Center);
  }
}
