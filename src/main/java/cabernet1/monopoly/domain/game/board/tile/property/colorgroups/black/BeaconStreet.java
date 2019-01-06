package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.black;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class BeaconStreet extends GroupColoredProperty {

  private static final long serialVersionUID = 8975059844982564991L;

  public BeaconStreet(int x, int y) {
    super("Beacon Street", 330, x, y, 165, ColorGroup.Black,
            200, 100, new ArrayList<Integer>() {
              private static final long serialVersionUID = 7194992164883529450L;

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