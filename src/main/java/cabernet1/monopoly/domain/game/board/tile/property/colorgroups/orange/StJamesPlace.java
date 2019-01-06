package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.orange;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class StJamesPlace extends GroupColoredProperty {

  private static final long serialVersionUID = -2010631863380577224L;

  public StJamesPlace(int x, int y) {
    super("St. James Place", 180, x, y, 90, ColorGroup.Orange,
            100, 50, new ArrayList<Integer>() {
              private static final long serialVersionUID = 3986565594667524727L;

              {
                add(14);
                add(70);
                add(200);
                add(550);
                add(750);
              }
            },
            500, 250, 950,
            600, 300, 1450);
  }
}