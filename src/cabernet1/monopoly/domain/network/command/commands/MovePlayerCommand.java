package cabernet1.monopoly.domain.network.command.commands;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.network.command.ICommand;

public class MovePlayerCommand extends ICommand {
    private IPlayer player;
    private Tile newTile;

    public IPlayer getPlayer() {
        return player;
    }

    public Tile getNewTile() {
        return newTile;
    }

    public MovePlayerCommand(IPlayer player, Tile newTile) {
        this.player = player;
        this.newTile = newTile;
    }

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        game.movePlayer(player, newTile);

    }

}
