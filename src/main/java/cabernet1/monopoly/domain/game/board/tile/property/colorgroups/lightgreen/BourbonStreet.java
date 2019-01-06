package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.lightgreen;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

import java.util.ArrayList;

public class BourbonStreet extends GroupColoredProperty {

  private static final long serialVersionUID = 3007486686021999762L;

  public BourbonStreet(int x, int y) {
    super("Bourbon Street", 120, x, y, 50, ColorGroup.LightGreen,
            50, 25, new ArrayList<Integer>() {
              private static final long serialVersionUID = 7663223361743147006L;

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
