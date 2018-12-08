package cabernet1.monopoly.domain.network.command;

import java.io.Serializable;

public abstract class ICommand implements Serializable {
    /**
     * Execute the commands functionality, which will be done via calling the required methods in the game controller
     */
    public abstract void execute();
}
