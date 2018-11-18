package cabernet1.monopoly.domain.network.command.commands;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.player.Player;
import cabernet1.monopoly.domain.network.command.ICommand;

public class MovePlayerCommand implements ICommand{
	private Player player;
	private Tile newTile;
	public Player getPlayer() {
		return player;
	}
	public Tile getNewTile() {
		return newTile;
	}
	public MovePlayerCommand(Player player, Tile newTile) {
		this.player=player;
		this.newTile=newTile;
	}
	@Override
	public void execute() {
		GameController game=Game.getInstance().getGameController();
		game.movePlayer(player,newTile);
		
	}
	
}
