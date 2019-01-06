package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.lightgreen;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class MagazineStreet extends GroupColoredProperty {

  private static final long serialVersionUID = 125556777110409L;

  public MagazineStreet(int x, int y) {
    super("Magazine Street", 120, x, y, 50, ColorGroup.LightGreen,
            50, 25, new ArrayList<Integer>() {
              private static final long serialVersionUID = 4933463330834094346L;

              {
                add(5);
                add(25);
                add(80);
                add(225);
                add(360);
              }
            },
            250, 125, 600,
            300, 150, 1000);
  }
}
