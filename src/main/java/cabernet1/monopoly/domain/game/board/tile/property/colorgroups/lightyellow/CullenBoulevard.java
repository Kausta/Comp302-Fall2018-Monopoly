package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.lightyellow;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class CullenBoulevard extends GroupColoredProperty {

  private static final long serialVersionUID = 2017582322325456321L;

  public CullenBoulevard(int x, int y) {
    super("Cullen Boulevard", 180, x, y, 70, ColorGroup.LightYellow,
            100, 50, new ArrayList<Integer>() {
              private static final long serialVersionUID = -2974128708911528376L;

              {
                add(11);
                add(55);
                add(160);
                add(475);
                add(650);
              }
            },
            500, 250, 800,
            600, 300, 1300);
  }
}