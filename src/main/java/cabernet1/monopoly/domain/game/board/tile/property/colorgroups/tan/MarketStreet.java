package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.tan;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

import java.util.ArrayList;

public class MarketStreet extends GroupColoredProperty {

  private static final long serialVersionUID = -3717841981631232748L;

  public MarketStreet(int x, int y) {
    super("Market Street", 420, x, y, 150, ColorGroup.Tan,
            250, 125, new ArrayList<Integer>() {
              private static final long serialVersionUID = -1768130250952898339L;

              {
                add(45);
                add(210);
                add(575);
                add(1300);
                add(1600);
              }
            },
            1250, 675, 1800,
            1500, 750, 3300,
            Track.Outer);
  }
}