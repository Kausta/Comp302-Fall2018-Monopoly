package cabernet1.monopoly.lib.cheat.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.property.Property;
import cabernet1.monopoly.domain.game.player.IPlayer;

public class BuyPropertyCheatCommand extends CheatCommand {
    private final int propertyId;

    public BuyPropertyCheatCommand(int propertyId) {
        this.propertyId = propertyId;
    }

    @Override
    public void execute() {
        IPlayer currentPlayer = Game.getInstance().getCurrentPlayer();
        Tile tile = Board.getInstance().getTileAtPosition(propertyId);
        if(tile instanceof Property) {
            Board.getInstance().buyProperty(currentPlayer, (Property) tile);
        }
    }

    public int getMoneyToGain() {
        return propertyId;
    }
}
