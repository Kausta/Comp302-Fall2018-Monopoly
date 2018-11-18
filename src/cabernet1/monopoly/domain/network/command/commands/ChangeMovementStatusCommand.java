package cabernet1.monopoly.domain.network.command.commands;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.player.Player;
import cabernet1.monopoly.domain.game.player.enumerators.PlayerMovementStatus;
import cabernet1.monopoly.domain.network.command.ICommand;

public class ChangeMovementStatusCommand extends ICommand{
	private PlayerMovementStatus movementStatus;
	private Player player;
	public Player getPlayer() {
		return player;
	}
	public PlayerMovementStatus getmovementStatus() {
		return movementStatus;
	}
	public ChangeMovementStatusCommand(Player player, PlayerMovementStatus status) {
		this.player=player;
		this.movementStatus=status;
	}
	@Override
	public void execute() {
		GameController game=Game.getInstance().getGameController();
		game.changeMovementStatus(player, movementStatus);

	}
}
