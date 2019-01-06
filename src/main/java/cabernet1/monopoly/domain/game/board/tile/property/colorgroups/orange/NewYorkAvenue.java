package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.orange;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

import java.util.ArrayList;

public class NewYorkAvenue extends GroupColoredProperty {

  private static final long serialVersionUID = 6129473317867009745L;

  public NewYorkAvenue(int x, int y) {
    super("New York Avenue", 200, x, y, 90, ColorGroup.Orange,
            100, 50, new ArrayList<Integer>() {
              private static final long serialVersionUID = 7412772397286273404L;

              {
                add(14);
                add(70);
                add(200);
                add(550);
                add(750);
              }
            },
            500, 250, 950,
            600, 300, 1450,
            Track.Center);
  }
}