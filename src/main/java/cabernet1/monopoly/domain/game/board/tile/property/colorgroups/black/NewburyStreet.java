package cabernet1.monopoly.domain.game.board.tile.property.colorgroups.black;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.enumerators.Track;

import java.util.ArrayList;

public class NewburyStreet extends GroupColoredProperty {

  private static final long serialVersionUID = 6837642356782778240L;

  public NewburyStreet(int x, int y) {
    super("Newbury Street", 380, x, y, 165, ColorGroup.Black,
            200, 100, new ArrayList<Integer>() {
              private static final long serialVersionUID = -1188696650548792808L;

              {
                add(30);
                add(160);
                add(470);
                add(1050);
                add(1250);
              }
            },
            1000, 500, 1500,
            1200, 600, 2500,
            Track.Inner);
  }
}