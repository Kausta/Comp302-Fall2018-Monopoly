package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.pink;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class StCharlesPlace extends GroupColoredProperty {

  private static final long serialVersionUID = 5468956416623904065L;

  public StCharlesPlace(int x, int y) {
    super("St. Charles Place", 140, x, y, 70, ColorGroup.Pink,
            100, 50, new ArrayList<Integer>() {
              private static final long serialVersionUID = -3540834745820189479L;

              {
                add(10);
                add(50);
                add(150);
                add(450);
                add(625);
              }
            },
            500, 250, 750,
            600, 300, 1250,
            Track.Center);
  }
}