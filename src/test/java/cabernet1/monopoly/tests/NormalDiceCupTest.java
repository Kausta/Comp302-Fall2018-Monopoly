package cabernet1.monopoly.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import cabernet1.monopoly.domain.game.die.util.NormalDiceCup;
import cabernet1.monopoly.domain.game.die.RegularDie;


public class NormalDiceCupTest {

  @Test
  public void testGetFacesValue() {
    NormalDiceCup n = NormalDiceCup.getInstance();
    n.die1 = new RegularDie();
    n.die1.rollDice();
    n.die2 = new RegularDie();
    n.die2.rollDice();

    assertEquals(true, n.isEven());
  }

  public void testRollCup() {
    NormalDiceCup n = NormalDiceCup.getInstance();
    assertTrue(NormalDiceCupStatus.TRIPLE_MOVE.equals(n.testRollCup())
                || NormalDiceCupStatus.DOUBLE_MOVE.equals(n.testRollCup())
                || NormalDiceCupStatus.MR_MONOPOLY_MOVE.equals(n.testRollCup())
                || NormalDiceCupStatus.BUS_MOVE.equals(n.testRollCup())
                || NormalDiceCupStatus.NORMAL_MOVE.equals(n.testRollCup()));
  }
}
