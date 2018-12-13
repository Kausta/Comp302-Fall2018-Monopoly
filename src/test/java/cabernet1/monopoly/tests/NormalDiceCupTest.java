package cabernet1.monopoly.tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import cabernet1.monopoly.domain.game.die.util.NormalDiceCup;
import cabernet1.monopoly.domain.game.die.RegularDie;
import cabernet1.monopoly.domain.game.die.enumerators.NormalDiceCupStatus;

import java.util.Arrays;


public class NormalDiceCupTest {

  @Test
  public void testRollCup() {
    NormalDiceCup n = NormalDiceCup.getInstance();
    NormalDiceCupStatus rollStatus = n.rollCup();
    assertTrue(Arrays.stream(NormalDiceCupStatus.values())
            .anyMatch(status -> status == rollStatus));
  }
}
