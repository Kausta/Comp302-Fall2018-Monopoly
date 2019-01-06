package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.black;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

import java.util.ArrayList;

public class BoylstonStreet extends GroupColoredProperty {

  private static final long serialVersionUID = -2327713378163081267L;

  public BoylstonStreet(int x, int y) {
    super("Boylston Street", 330, x, y, 165, ColorGroup.Black,
            200, 100, new ArrayList<Integer>() {
              private static final long serialVersionUID = -1939853933781496480L;

              {
                add(30);
                add(160);
                add(470);
                add(1050);
                add(1250);
              }
            },
            1000, 500, 1500,
            1200, 600, 2500,
            Track.Inner);
  }
}