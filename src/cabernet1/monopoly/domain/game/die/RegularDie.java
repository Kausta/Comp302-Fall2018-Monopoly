package cabernet1.monopoly.domain.game.die;

import cabernet1.monopoly.domain.game.die.enumerators.RegularDieFaces;

public class RegularDie extends IDie {
    @Override
    public void rollDice() {
        int value = diceGen.nextInt(6) + 1;
        setValue(value);
        switch (value) {
            case 1:
                faceValue = RegularDieFaces.One;
                break;
            case 2:
                faceValue = RegularDieFaces.Two;
                break;
            case 3:
                faceValue = RegularDieFaces.Three;
                break;
            case 4:
                faceValue = RegularDieFaces.Four;
                break;
            case 5:
                faceValue = RegularDieFaces.Five;
                break;
            case 6:
                faceValue = RegularDieFaces.Six;
                break;
        }
    }

}
