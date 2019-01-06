package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.lightyellow;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

import java.util.ArrayList;

public class KirbyDrive extends GroupColoredProperty {

  private static final long serialVersionUID = 633760654757886880L;

  public KirbyDrive(int x, int y) {
    super("Kirby Drive", 180, x, y, 70, ColorGroup.LightYellow,
            100, 50, new ArrayList<Integer>() {
              private static final long serialVersionUID = 5029300507275957582L;

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