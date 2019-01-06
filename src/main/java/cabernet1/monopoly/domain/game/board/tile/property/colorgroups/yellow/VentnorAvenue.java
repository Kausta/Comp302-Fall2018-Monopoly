package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.yellow;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

import java.util.ArrayList;

public class VentnorAvenue extends GroupColoredProperty {

  private static final long serialVersionUID = 8419175676344670563L;

  public VentnorAvenue(int x, int y) {
    super("Ventnor Avenue", 260, x, y, 130, ColorGroup.Yellow,
            150, 75, new ArrayList<Integer>() {
              private static final long serialVersionUID = 4876121404627602732L;

              {
                add(22);
                add(110);
                add(330);
                add(800);
                add(975);
              }
            },
            750, 375, 1150,
            900, 450, 2150,
            Track.Center);
  }
}