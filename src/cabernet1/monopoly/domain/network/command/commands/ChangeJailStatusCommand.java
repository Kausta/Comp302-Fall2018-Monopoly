package cabernet1.monopoly.domain.network.command.commands;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.player.Player;
import cabernet1.monopoly.domain.network.command.ICommand;

public class ChangeJailStatusCommand implements ICommand{
	private boolean inJail;
	private Player player;
	public Player getPlayer() {
		return player;
	}
	public boolean getJailStatus() {
		return inJail;
	}
	public ChangeJailStatusCommand(Player player, boolean inJail) {
		this.player=player;
		this.inJail=inJail;
	}
	@Override
	public void execute() {
		GameController game=Game.getInstance().getGameController();
		game.changeJailStatus(player, inJail);
		
	}
	
}
