package cabernet1.monopoly.domain.network.command;

public interface ICommand {
    /**
     * Execute the commands functionality, which will be done via calling the required methods in the game controller
     */
    void execute();
}
