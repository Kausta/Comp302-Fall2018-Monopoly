package cabernet1.monopoly.domain.game.die;

import cabernet1.monopoly.domain.game.die.enumerators.SpeedDieFaces;

public class SpeedDie extends IDie {

    private int value = diceGen.nextInt(6) + 1;

    @Override
    public void rollDice() {
        switch (value) {
            case 1:
                faceValue = SpeedDieFaces.One;
                break;
            case 2:
                faceValue = SpeedDieFaces.Two;
                break;
            case 3:
                faceValue = SpeedDieFaces.Three;
                break;
            case 4:
            case 5:
                faceValue = SpeedDieFaces.MrMonopoly;
                break;
            case 6:
                faceValue = SpeedDieFaces.BusIcon;
                break;
        }
        value = diceGen.nextInt(6) + 1;
    }

    public int speedDieValue() {
        return this.value;
    }

}
