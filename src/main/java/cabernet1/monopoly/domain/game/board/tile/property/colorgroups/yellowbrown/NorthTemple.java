package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.yellowbrown;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

import java.util.ArrayList;

public class NorthTemple extends GroupColoredProperty {

  private static final long serialVersionUID = 4810063214427779098L;

  public NorthTemple(int x, int y) {
    super("North Temple", 360, x, y, 130, ColorGroup.YellowBrown,
            200, 100, new ArrayList<Integer>() {
              private static final long serialVersionUID = -6996116203848508521L;

              {
                add(32);
                add(160);
                add(470);
                add(1050);
                add(1250);
              }
            },
            1000, 500, 1500,
            1200, 600, 2500,
            Track.Outer);
  }
}