package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.game.player.enumerators.PlayerMovementStatus;
import cabernet1.monopoly.domain.network.command.ICommand;

public class ChangeMovementStatusCommand extends ICommand{

	private PlayerMovementStatus movementStatus;
	private IPlayer player;
	public IPlayer getPlayer() {
		return player;
	}
	public PlayerMovementStatus getmovementStatus() {
		return movementStatus;
	}
	public ChangeMovementStatusCommand(IPlayer player, PlayerMovementStatus status) {
		this.player=player;
		this.movementStatus=status;
	}
	@Override
	public void execute() {
		GameController game=Game.getInstance().getGameController();
		game.changeMovementStatus(player, movementStatus);
	}
}
