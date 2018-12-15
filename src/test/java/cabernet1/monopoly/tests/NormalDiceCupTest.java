package cabernet1.monopoly.tests;

import cabernet1.monopoly.domain.game.die.enumerators.NormalDiceCupStatus;
import cabernet1.monopoly.domain.game.die.util.NormalDiceCup;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class NormalDiceCupTest {

    @Test
    public void testRollResultMatchesRollStatuses() {
        NormalDiceCup n = NormalDiceCup.getInstance();
        NormalDiceCupStatus rollStatus = n.rollCup();
        assertTrue(Arrays.stream(NormalDiceCupStatus.values())
                .anyMatch(status -> status == rollStatus));
    }

    @Test
    public void testDiceValueIsInCorrectRange() {
        NormalDiceCup n = NormalDiceCup.getInstance();
        n.rollCup();
        assertTrue((n.die1.getDiceValue().getValue() >= 1 && n.die1.getDiceValue().getValue() <= 6)
                || (n.die2.getDiceValue().getValue() >= 1 && n.die2.getDiceValue().getValue() <= 6)
                || (n.die3.getDiceValue().getValue() >= 1 && n.die3.getDiceValue().getValue() <= 6));
    }

    @Test
    public void testFacesValueReturnsCorrectValue() {
        NormalDiceCup n = NormalDiceCup.getInstance();
        n.rollCup();
        int d1 = n.die1.getDiceValue().getValue();
        int d2 = n.die2.getDiceValue().getValue();
        int d3 = n.die3.getDiceValue().getValue();
        int expected = d1 + d2 + d3;
        assertEquals(expected, n.getFacesValue());
    }

    @Test
    public void testFacesValueIsInCorrectRange() {
        NormalDiceCup n = NormalDiceCup.getInstance();
        n.rollCup();
        assertTrue(n.getFacesValue() >= 3 || n.getFacesValue() <= 18);
    }

    @Test
    public void testRollIsEven() {
        NormalDiceCup n = NormalDiceCup.getInstance();
        n.rollCup();
        int d1 = n.die1.getDiceValue().getValue();
        int d2 = n.die2.getDiceValue().getValue();
        boolean expected = (d1 + d2) % 2 == 0;
        assertEquals(expected, n.isEven());
    }
}
