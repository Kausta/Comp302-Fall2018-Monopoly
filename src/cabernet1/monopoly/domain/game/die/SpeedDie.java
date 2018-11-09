package cabernet1.monopoly.domain.game.die;

public class SpeedDie extends IDie {
    @Override
    public void rollDice() {
        int temp = (diceGen.nextInt(6) + 1);
        if (temp < 4)
            faceValue = String.valueOf(temp);
        else if (temp == 4 || temp == 5)
            faceValue = "Mr.Monopoly";
        else
            faceValue = "BusIcon";
    }

}
