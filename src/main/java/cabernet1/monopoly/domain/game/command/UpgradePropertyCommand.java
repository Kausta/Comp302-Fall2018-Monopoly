package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.network.command.ICommand;

public class UpgradePropertyCommand extends ICommand {
    private int propertyId;

    public UpgradePropertyCommand(int propertyId) {
        this.propertyId = propertyId;
    }

    @Override
    public void execute() {
        GameController game = Game.getInstance().getGameController();
        game.completeUpgradeBuilding(propertyId);

    }
}
