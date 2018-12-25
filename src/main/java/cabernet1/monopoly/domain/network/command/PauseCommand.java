package cabernet1.monopoly.domain.network.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.player.Player;

import java.util.List;

public class PauseCommand extends ICommand {

  public PauseCommand() {

  }

  @Override
  public void execute() {
    GameController gc = Game.getInstance().getGameController();
    gc.pauseButton.setValue(false);
    Player currentPlayer = Game.getInstance().getCurrentPlayer();
    List<String> playersOnDevice = Game.getInstance().getPlayersOnDevice();
    if(playersOnDevice.contains(currentPlayer.getName())) {
      gc.resumeButton.setValue(true);
    }
    else {
      gc.resumeButton.setValue(false);
    }
  }
}
