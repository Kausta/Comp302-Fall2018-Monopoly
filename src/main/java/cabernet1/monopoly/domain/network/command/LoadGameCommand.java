package cabernet1.monopoly.domain.network.command;

import cabernet1.monopoly.Application;
import cabernet1.monopoly.lib.persistence.GameSerializer;

import java.util.Map;

public class LoadGameCommand extends ICommand {
    private static final long serialVersionUID = -4525676647561793235L;

    private final Map<String, String> serializedThing;

    public LoadGameCommand(Map<String, String> serializedThing) {
        this.serializedThing = serializedThing;
    }

    @Override
    public void execute() {
        GameSerializer.getInstance().deserializeGameAndLoad(serializedThing);
        Application.getInstance().startGame();
    }
}
