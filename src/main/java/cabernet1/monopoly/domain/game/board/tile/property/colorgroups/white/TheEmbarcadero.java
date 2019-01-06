package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.white;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class TheEmbarcadero extends GroupColoredProperty {

  private static final long serialVersionUID = -8663032945461158241L;

  public TheEmbarcadero(int x, int y) {
    super("The Embarcadero", 210, x, y, 105, ColorGroup.White,
            100, 50, new ArrayList<Integer>() {
              private static final long serialVersionUID = 403577237359574887L;

              {
                add(17);
                add(85);
                add(240);
                add(475);
                add(670);
              }
            },
            500, 250, 1025,
            600, 300, 1525);
  }
}