package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.lightgreen;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

import java.util.ArrayList;

public class CanalStreet extends GroupColoredProperty {

  private static final long serialVersionUID = 1384771399059753236L;

  public CanalStreet(int x, int y) {
    super("Canal Street", 90, x, y, 50, ColorGroup.LightGreen,
            50, 25, new ArrayList<Integer>() {
              private static final long serialVersionUID = 1881475289598244355L;

              {
                add(5);
                add(25);
                add(80);
                add(225);
                add(360);
              }
            },
            250, 125, 600,
            300, 150, 1000,
            Track.Outer);
  }
}
