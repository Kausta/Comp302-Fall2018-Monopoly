package cabernet1.monopoly.domain.network.command.commands;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.network.command.ICommand;

public class PayRentCommand extends ICommand{

	private int rentAmount;
	private IPlayer player;
	public IPlayer getPlayer() {
		return player;
	}
	public PayRentCommand(IPlayer player, int rentAmount) {
		this.player=player;
		this.rentAmount=rentAmount;
	}

	@Override
	public void execute() {
		GameController game=Game.getInstance().getGameController();
		game.playerPayRent(player, rentAmount);

	}
}
