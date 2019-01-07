package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.network.command.ICommand;

public class PayRentCommand extends ICommand {

    private static final long serialVersionUID = -3201844977371445437L;
    private final int rentAmount;
    private final int playerId;

    public PayRentCommand(int playerId, int rentAmount) {
        this.playerId = playerId;
        this.rentAmount = rentAmount;
    }

    public int getPlayerId() {
        return playerId;
    }

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        game.playerPayRent(playerId, rentAmount);
        game.updateInfoObservables();
    }
}
