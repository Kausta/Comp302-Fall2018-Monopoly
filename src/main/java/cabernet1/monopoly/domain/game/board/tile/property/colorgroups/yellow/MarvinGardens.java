package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.yellow;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class MarvinGardens extends GroupColoredProperty {

  private static final long serialVersionUID = 7705443247696261143L;

  public MarvinGardens(int x, int y) {
    super("Marvin Gardens", 280, x, y, 130, ColorGroup.Yellow,
            150, 75, new ArrayList<Integer>() {
              private static final long serialVersionUID = 8244491181189282732L;

              {
                add(22);
                add(110);
                add(330);
                add(800);
                add(975);
              }
            },
            750, 375, 1150,
            900, 450, 2150);
  }
}