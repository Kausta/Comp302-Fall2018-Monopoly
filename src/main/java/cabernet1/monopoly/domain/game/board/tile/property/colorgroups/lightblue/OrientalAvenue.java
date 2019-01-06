package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.lightblue;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;

import java.util.ArrayList;

public class OrientalAvenue extends GroupColoredProperty {

  private static final long serialVersionUID = -8457989658066214765L;

  public OrientalAvenue(int x, int y) {
    super("Oriental Avenue", 100, x, y, 50, ColorGroup.LightBlue,
            50, 25, new ArrayList<Integer>() {
              private static final long serialVersionUID = -6139709070159517773L;

              {
                add(6);
                add(30);
                add(90);
                add(270);
                add(400);
              }
            },
            250, 125, 550,
            300, 150, 1050,
            Track.Center);
  }
}