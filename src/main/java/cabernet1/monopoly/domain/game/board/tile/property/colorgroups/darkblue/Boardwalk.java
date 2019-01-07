package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.darkblue;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

import java.util.ArrayList;

public class Boardwalk extends GroupColoredProperty {

  private static final long serialVersionUID = 4665050546748030414L;

  public Boardwalk(int x, int y) {
    super("Boardwalk", 400, x, y, 200, ColorGroup.DarkBlue,
            200, 100, new ArrayList<Integer>() {
              private static final long serialVersionUID = 7815010893031438521L;

              {
                add(35);
                add(175);
                add(500);
                add(1100);
                add(1300);
              }
            },
            1000, 500, 1500,
            1200, 600, 2500,
            Track.Center);
  }
}
