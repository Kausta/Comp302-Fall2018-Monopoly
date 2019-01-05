package cabernet1.monopoly.domain.network.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.player.Player;
import cabernet1.monopoly.utils.Observable;

import java.util.List;

public class ResumeCommand extends ICommand {

  public ResumeCommand() {

  }

  @Override
  public void execute() {
    GameController gc = Game.getInstance().getGameController();
    gc.resumeButton.setValue(false);
    Player currentPlayer = Game.getInstance().getCurrentPlayer();
    List<String> playersOnDevice = Game.getInstance().getPlayersOnDevice();
    if(playersOnDevice.contains(currentPlayer.getName())) {
      gc.pauseButton.setValue(true);
    }
    else {
      gc.pauseButton.setValue(false);
    }
    for(Observable<Boolean> o: gc.disabledObservableList) {
      o.setValue(true);
    }
  }
}
