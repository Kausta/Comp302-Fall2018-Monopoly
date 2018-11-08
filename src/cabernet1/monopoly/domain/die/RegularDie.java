package cabernet1.monopoly.domain.die;

public class RegularDie extends IDie {
    @Override
    public void rollDice() {
        faceValue = String.valueOf(diceGen.nextInt(6) + 1);
    }
}
