package cabernet1.monopoly.domain.game.die.util;

import cabernet1.monopoly.domain.game.die.RegularDie;
import cabernet1.monopoly.domain.game.die.SpeedDie;
import cabernet1.monopoly.domain.game.die.enumerators.NormalDiceCupStatus;
import cabernet1.monopoly.domain.game.die.enumerators.SpeedDieFaces;

/**
 * A dice cup class for normal dice
 */
public class NormalDiceCup implements DiceCup {
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
     * @return true if die1 and die2 are equal, false otherwise.
     */
    private boolean isDoubles() {
        // when determining doubles, only the first two dice are consider
        return die1.getDiceValue() == die2.getDiceValue();
    }

    /**
     * Checks whether die1, die2 and die3 are equal or not.
     * @return true if die1, die2 and die3 are equal, false otherwise.
     */
    private boolean isTriples() {
        return die1.getDiceValue().getValue() == die2.getDiceValue().getValue() && die1.getDiceValue().getValue() == die3.getDiceValue().getValue();
    }

    /**
     * Checks whether die3 is MrMonopoly or not.
     * @return true if die3 is MrMonopoly, false otherwise.
     */
    private boolean isMrMonopolyMove() {
        return die3.getDiceValue() == SpeedDieFaces.MrMonopoly;
    }

    /**
     * Checks whether die3 is BusIcon or not.
     * @return true if die3 is BusIcon, false otherwise.
     */
    private boolean isBusMove() {
        return die3.getDiceValue() == SpeedDieFaces.BusIcon;
    }

    /**
     * Roll the dice and returns the move type according the new dice values.
     * To determine the move type, it checks every possible move types in order.
     * @return the type of move.
     */
    public NormalDiceCupStatus rollCup() {
        // REQUIRES: die1, die2 and die3 are initialized
        // MODIFIES: die1, die2, die3
        // EFFECTS: roll dice die1, die2 and die3.
        //      If dice are triples, returns TRIPLE_MOVE,
        //      If dice are doubles, returns DOUBLE_MOVE,
        //      If third die's face is Mr. Monopoly, returns MR_MONOPOLY_MOVE,
        //      If third die's face is bus icon, returns BUS_MOVE.
        //      Otherwise, returns NORMAL_MOVE

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
     * @return the sum of the face values of dice.
     */
    public int getFacesValue() {
        // REQUIRES: die1, die2 and die3 has values
        // EFFECTS: returns the sum of the dice values
        return die1.getDiceValue().getValue() + die2.getDiceValue().getValue() + die3.getDiceValue().getValue();
    }

    /**
     * Checks whether the sum of die1 and die2 is even or not.
     * @return true if sum of die1 and die2 is even, false otherwise.
     */
    public boolean isEven() {
        return (die1.getDiceValue().getValue() + die2.getDiceValue().getValue()) % 2 == 0;
    }
}
