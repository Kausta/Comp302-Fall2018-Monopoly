package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.pink;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class StatesAvenue extends GroupColoredProperty {

  private static final long serialVersionUID = 5947282013000298604L;

  public StatesAvenue(int x, int y) {
    super("States Avenue", 140, x, y, 70, ColorGroup.Pink,
            100, 50, new ArrayList<Integer>() {
              private static final long serialVersionUID = 9136078404511049296L;

              {
                add(10);
                add(50);
                add(150);
                add(450);
                add(625);
              }
            },
            500, 250, 750,
            600, 300, 1250);
  }
}