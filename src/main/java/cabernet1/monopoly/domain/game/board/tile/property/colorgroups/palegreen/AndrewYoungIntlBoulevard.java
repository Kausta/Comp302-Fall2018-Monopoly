package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.palegreen;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

import java.util.ArrayList;

public class AndrewYoungIntlBoulevard extends GroupColoredProperty {

  private static final long serialVersionUID = 2485004315332622356L;

  public AndrewYoungIntlBoulevard(int x, int y) {
    super("Andrew Young Intl Boulevard", 210, x, y, 90, ColorGroup.PaleGreen,
            100, 50, new ArrayList<Integer>() {
              private static final long serialVersionUID = -6480865401842796003L;

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
