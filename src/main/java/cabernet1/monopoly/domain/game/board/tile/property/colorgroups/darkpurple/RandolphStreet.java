package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.darkpurple;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class RandolphStreet extends GroupColoredProperty {

  private static final long serialVersionUID = 4349599105788137627L;

  public RandolphStreet(int x, int y) {
    super("Randolph Street", 270, x, y, 110, ColorGroup.DarkPurple,
            150, 75, new ArrayList<Integer>() {
              private static final long serialVersionUID = 2946875861237461702L;

              {
                add(23);
                add(115);
                add(345);
                add(825);
                add(1010);
              }
            },
            750, 375, 1180,
            900, 450, 2180,
            Track.Outer);
  }
}