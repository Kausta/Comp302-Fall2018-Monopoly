package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.darkpurple;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class WackerDrive extends GroupColoredProperty {

  private static final long serialVersionUID = -6392740831428101656L;

  public WackerDrive(int x, int y) {
    super("Wacker Drive", 300, x, y, 110, ColorGroup.DarkPurple,
            150, 75, new ArrayList<Integer>() {
              private static final long serialVersionUID = -187347990365986660L;

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