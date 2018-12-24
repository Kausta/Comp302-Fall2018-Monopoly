package cabernet1.monopoly.domain.network.command;

import cabernet1.monopoly.Application;
import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.game.player.InitialPlayerData;

import java.util.List;

public class StartGameCommand extends ICommand {
    private static final long serialVersionUID = -4525676647561793235L;
    private final List<InitialPlayerData> players;

    public StartGameCommand(List<InitialPlayerData> players) {
        this.players = players;
    }

    @Override
    public void execute() {
        Game.getInstance().initialize(players);
        Application.getInstance().startGame();
    }
}
