package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.darkpurple;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class LakeShoreDrive extends GroupColoredProperty {

  private static final long serialVersionUID = -271224558622913328L;

  public LakeShoreDrive(int x, int y) {
    super("Lake Shore Drive", 270, x, y, 110, ColorGroup.DarkPurple,
            150, 75, new ArrayList<Integer>() {
              private static final long serialVersionUID = 1885317681815963531L;

              {
                add(23);
                add(115);
                add(345);
                add(825);
                add(1010);
              }
            },
            750, 375, 1180,
            900, 450, 2180);
  }
}