package cabernet1.monopoly.lib.cheat.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.property.Property;
import cabernet1.monopoly.domain.game.player.IPlayer;

public class RollDiceCheatCommand extends CheatCommand {

    public RollDiceCheatCommand() {
    }

    @Override
    public void execute() {
        Game.getInstance().getGameController().rollDice();
    }

}
