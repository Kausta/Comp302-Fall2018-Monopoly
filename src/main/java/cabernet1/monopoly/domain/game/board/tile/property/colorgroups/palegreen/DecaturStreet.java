package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.palegreen;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

import java.util.ArrayList;

public class DecaturStreet extends GroupColoredProperty {

  private static final long serialVersionUID = 6714191943685830821L;

  public DecaturStreet(int x, int y) {
    super("Decatur Street", 240, x, y, 90, ColorGroup.PaleGreen,
            100, 50, new ArrayList<Integer>() {
              private static final long serialVersionUID = 3352292369184477849L;

              {
                add(17);
                add(85);
                add(240);
                add(670);
                add(840);
              }
            },
            500, 250, 1025,
            600, 300, 1525,
            Track.Outer);
  }
}
