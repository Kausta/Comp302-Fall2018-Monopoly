package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.lightgreen;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class EsplanadeAvenue extends GroupColoredProperty {

  private static final long serialVersionUID = 7771950289763368686L;

  public EsplanadeAvenue(int x, int y) {
    super("Esplanade Avenue", 90, x, y, 50, ColorGroup.LightGreen,
            50, 25, new ArrayList<Integer>() {
              private static final long serialVersionUID = 6485579738925620579L;

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
