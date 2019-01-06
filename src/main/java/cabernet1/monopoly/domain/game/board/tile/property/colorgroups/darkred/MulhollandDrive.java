package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.darkred;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class MulhollandDrive extends GroupColoredProperty {

  private static final long serialVersionUID = -6150963086669006080L;

  public MulhollandDrive(int x, int y) {
    super("Mulholland Drive", 450, x, y, 175, ColorGroup.DarkRed,
            300, 150, new ArrayList<Integer>() {
              private static final long serialVersionUID = -5424348700770623140L;

              {
                add(70);
                add(350);
                add(750);
                add(1600);
                add(1850);
              }
            },
            1500, 750, 2100,
            1800, 900, 3600);
  }
}
