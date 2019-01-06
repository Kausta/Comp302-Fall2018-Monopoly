package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.lightblue;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class ConnecticutAvenue extends GroupColoredProperty {

  private static final long serialVersionUID = -5466410118589061666L;

  public ConnecticutAvenue(int x, int y) {
    super("Connecticut Avenue", 120, x, y, 50, ColorGroup.LightBlue,
            50, 25, new ArrayList<Integer>() {
              private static final long serialVersionUID = -7111744535630093336L;

              {
                add(6);
                add(30);
                add(90);
                add(270);
                add(400);
              }
            },
            250, 125, 550,
            300, 150, 1050);
  }
}