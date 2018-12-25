package cabernet1.monopoly.domain.network.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.player.Player;
import cabernet1.monopoly.utils.Observable;

import java.util.ArrayList;
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
    if(gc.disabledUIElementList.isEmpty()) {
      gc.disabledUIElementList.clear();
    }
    for(Observable<Boolean> o: gc.interactableUIElementList) {
      if(o.getValue()) {
        o.setValue(false);
        gc.disabledUIElementList.add(o);
      }
    }
  }
}
