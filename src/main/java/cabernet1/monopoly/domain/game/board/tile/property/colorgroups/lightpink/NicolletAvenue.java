package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.lightpink;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

import java.util.ArrayList;

public class NicolletAvenue extends GroupColoredProperty {

  private static final long serialVersionUID = 46577197444773624L;

  public NicolletAvenue(int x, int y) {
    super("Nicollet Avenue", 30, x, y, 15, ColorGroup.LightPink,
            50, 25, new ArrayList<Integer>() {
              private static final long serialVersionUID = -1368577251693035963L;

              {
                add(1);
                add(5);
                add(15);
                add(45);
                add(80);
              }
            },
            250, 125, 125,
            300, 150, 625,
            Track.Outer);
  }
}
