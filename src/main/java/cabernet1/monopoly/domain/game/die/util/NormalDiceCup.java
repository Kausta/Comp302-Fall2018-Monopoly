package cabernet1.monopoly.domain.game.die.util;

import cabernet1.monopoly.domain.game.die.RegularDie;
import cabernet1.monopoly.domain.game.die.SpeedDie;
import cabernet1.monopoly.domain.game.die.enumerators.NormalDiceCupStatus;
import cabernet1.monopoly.domain.game.die.enumerators.SpeedDieFaces;
import cabernet1.monopoly.utils.RepresentationInvariant;

/**
 * A dice cup class for normal dice
 */
public class NormalDiceCup implements DiceCup, RepresentationInvariant {
    private static volatile NormalDiceCup _instance = null;

    public RegularDie die1 = new RegularDie();
    public RegularDie die2 = new RegularDie();
    public SpeedDie die3 = new SpeedDie();

    /**
     * NormalDiceCup class constructor
     */
    private NormalDiceCup() {

    }

    /**
     * Method for getting the singleton NormalDiceCup object
     *
     * @return the instance of NormalDiceCup
     */
    public static synchronized NormalDiceCup getInstance() {
        if (_instance == null) {
            _instance = new NormalDiceCup();
        }
        return _instance;
    }

    /**
     * Checks whether die1 and die2 are equal or not.
     *
     * @return true if die1 and die2 are equal, false otherwise.
     */
    private boolean isDoubles() {
        // when determining doubles, only the first two dice are consider
        return die1.getDiceValue() == die2.getDiceValue();
    }

    /**
     * Checks whether die1, die2 and die3 are equal or not.
     *
     * @return true if die1, die2 and die3 are equal, false otherwise.
     * @requires die1, die2 and die3 are initialized
     * @effects If dice values are all equal, returns true, otherwise false.
     */
    public boolean isTriples() {
        return die1.getDiceValue().getValue() == die2.getDiceValue().getValue() && die1.getDiceValue().getValue() == die3.getDiceValue().getValue();
    }

    /**
     * Checks whether die3 is MrMonopoly or not.
     *
     * @return true if die3 is MrMonopoly, false otherwise.
     */
    private boolean isMrMonopolyMove() {
        return die3.getDiceValue() == SpeedDieFaces.MrMonopoly;
    }

    /**
     * Checks whether die3 is BusIcon or not.
     *
     * @return true if die3 is BusIcon, false otherwise.
     */
    private boolean isBusMove() {
        return die3.getDiceValue() == SpeedDieFaces.BusIcon;
    }

    /**
     * Roll the dice and returns the move type according the new dice values.
     * To determine the move type, it checks every possible move types in order.
     *
     * @return the type of move.
     * @requires die1, die2 and die3 are initialized
     * @modifies die1, die2, die3
     * @effects roll dice die1, die2 and die3
     * If dice are triples, returns TRIPLE_MOVE,
     * If dice are doubles, returns DOUBLE_MOVE,
     * If third die's face is Mr. Monopoly, returns MR_MONOPOLY_MOVE,
     * If third die's face is bus icon, returns BUS_MOVE.
     * Otherwise, returns NORMAL_MOVE
     */
    public NormalDiceCupStatus rollCup() {

        die1.rollDice();
        die2.rollDice();
        die3.rollDice();

        if (isTriples())
            return NormalDiceCupStatus.TRIPLE_MOVE;
        if (isDoubles())
            return NormalDiceCupStatus.DOUBLE_MOVE;
        if (isMrMonopolyMove())
            return NormalDiceCupStatus.MR_MONOPOLY_MOVE;
        if (isBusMove())
            return NormalDiceCupStatus.BUS_MOVE;

        return NormalDiceCupStatus.NORMAL_MOVE;
    }

    /**
     * Method for getting the total of the face values of dice.
     *
     * @return the sum of the face values of dice.
     * @requires die1, die2 and die3 has values
     * @effects returns the sum of the dice values
     */
    public int getFacesValue() {
        return die1.getValue() + die2.getValue() + die3.getValue();
    }

    /**
     * Checks whether the sum of die1 and die2 is even or not.
     *
     * @return true if sum of die1 and die2 is even, false otherwise.
     * @requires die1, die2 and die3 has values
     * @effects returns whether the sum of the dice values is even
     */
    public boolean isEven() {
        return (die1.getValue() + die2.getValue()) % 2 == 0;
    }

    @Override
    public boolean repOK() {
        return die1.repOK() && die2.repOK() && die3.repOK();
    }

    @Override
    public String toString() {
        return "NormalDiceCup{ " +
                "die1: " + die1 +
                ", die2: " + die2 +
                ", die3: " + die3 +
                " }";
    }
}
