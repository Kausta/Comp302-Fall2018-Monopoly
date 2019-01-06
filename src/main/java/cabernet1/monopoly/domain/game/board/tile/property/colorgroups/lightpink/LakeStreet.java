package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.lightpink;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class LakeStreet extends GroupColoredProperty {

  private static final long serialVersionUID = -3200517916581742047L;

  public LakeStreet(int x, int y) {
    super("Lake Street", 30, x, y, 15, ColorGroup.LightPink,
            50, 25, new ArrayList<Integer>() {
              private static final long serialVersionUID = 811479213868153777L;

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
