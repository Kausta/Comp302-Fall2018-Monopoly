package cabernet1.monopoly.lib.cheat.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.game.player.IPlayer;

public class GainMoneyCheatCommand extends CheatCommand {
    private final int moneyToGain;

    public GainMoneyCheatCommand(int moneyToGain) {
        this.moneyToGain = moneyToGain;
    }

    @Override
    public void execute() {
        IPlayer currentPlayer = Game.getInstance().getCurrentPlayer();
        currentPlayer.gainMoney(getMoneyToGain());
    }

    public int getMoneyToGain() {
        return moneyToGain;
    }
}
