package cabernet1.monopoly.domain.game.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.network.command.ICommand;

public class DowngradePropertyCommand extends ICommand {

    private final int propertyId;

    public DowngradePropertyCommand(int propertyId){ this.propertyId = propertyId;}

    @Override
    public void execute(){
        GameController gc = Game.getInstance().getGameController();
        gc.downgradeBuilding(propertyId);
    }
}
