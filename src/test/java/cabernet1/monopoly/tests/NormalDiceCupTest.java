package cabernet1.monopoly.tests;

import cabernet1.monopoly.domain.game.die.enumerators.NormalDiceCupStatus;
import cabernet1.monopoly.domain.game.die.util.NormalDiceCup;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class NormalDiceCupTest extends TestBase {

    @Test
    public void testRollResultMatchesRollStatuses() {
        NormalDiceCup cup = NormalDiceCup.getInstance();
        NormalDiceCupStatus rollStatus = cup.rollCup();
        assertTrue(Arrays.stream(NormalDiceCupStatus.values())
                .anyMatch(status -> status == rollStatus));
        testRepOK(cup);
    }

    @Test
    public void testDiceValueIsInCorrectRange() {
        NormalDiceCup cup = NormalDiceCup.getInstance();
        cup.rollCup();
        assertTrue((cup.die1.getValue() >= 1 && cup.die1.getValue() <= 6)
                || (cup.die2.getValue() >= 1 && cup.die2.getValue() <= 6)
                || (cup.die3.getValue() >= 1 && cup.die3.getValue() <= 6));
        testRepOK(cup);
    }

    @Test
    public void testFacesValueReturnsCorrectValue() {
        NormalDiceCup cup = NormalDiceCup.getInstance();
        cup.rollCup();
        int d1 = cup.die1.getValue();
        int d2 = cup.die2.getValue();
        int d3 = cup.die3.getValue();
        int expected = d1 + d2 + d3;
        assertEquals(expected, cup.getFacesValue());
        testRepOK(cup);
    }

    @Test
    public void testFacesValueIsInCorrectRange() {
        NormalDiceCup cup = NormalDiceCup.getInstance();
        cup.rollCup();
        assertTrue(cup.getFacesValue() >= 3 || cup.getFacesValue() <= 18);
        testRepOK(cup);
    }

    @Test
    public void testRollIsEven() {
        NormalDiceCup cup = NormalDiceCup.getInstance();
        cup.rollCup();
        int d1 = cup.die1.getValue();
        int d2 = cup.die2.getValue();
        boolean expected = (d1 + d2) % 2 == 0;
        assertEquals(expected, cup.isEven());
        testRepOK(cup);
    }
}
