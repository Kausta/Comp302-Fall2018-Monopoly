package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.lightyellow;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

import java.util.ArrayList;

public class WestheimerRoad extends GroupColoredProperty {

  private static final long serialVersionUID = -2328182547053376645L;

  public WestheimerRoad(int x, int y) {
    super("Westheimer Road", 150, x, y, 70, ColorGroup.LightYellow,
            100, 50, new ArrayList<Integer>() {
              private static final long serialVersionUID = 5510869901120585515L;

              {
                add(11);
                add(55);
                add(160);
                add(475);
                add(650);
              }
            },
            500, 250, 800,
            600, 300, 1300,
            Track.Outer);
  }
}