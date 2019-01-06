package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.darkred;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

import java.util.ArrayList;

public class VenturaBoulevard extends GroupColoredProperty {

  private static final long serialVersionUID = -969187093245201791L;

  public VenturaBoulevard(int x, int y) {
    super("Ventura Boulevard", 480, x, y, 175, ColorGroup.DarkRed,
            300, 150, new ArrayList<Integer>() {
              private static final long serialVersionUID = -3681904590032820021L;

              {
                add(70);
                add(350);
                add(750);
                add(1600);
                add(1850);
              }
            },
            1500, 750, 2100,
            1800, 900, 3600,
            Track.Outer);
  }
}
