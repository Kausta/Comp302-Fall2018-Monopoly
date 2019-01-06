package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.network.command.ICommand;

public class showDiceFacesCommand extends ICommand {
    private static final long serialVersionUID = -4760859312103405343L;
    final int die1;
    final int die2;
    final int die3;

    public showDiceFacesCommand(int die1Result,int die2Result,int die3Result) {
        this.die1 = die1Result;
        this.die2 = die2Result;
        this.die3 = die3Result;
    }

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        game.showDiceFaces(die1,die2,die3);
    }
}
