package cabernet1.monopoly.domain.network.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.game.player.Player;
import cabernet1.monopoly.utils.Observable;

import java.util.ArrayList;
import java.util.List;

public class PauseCommand extends ICommand {

  private static final long serialVersionUID = -2942268372587032414L;

  public PauseCommand() {

  }

  @Override
  public void execute() {
    GameController gc = Game.getInstance().getGameController();
    gc.pauseButton.setValue(false);
    IPlayer currentPlayer = Game.getInstance().getCurrentPlayer();
    List<String> playersOnDevice = Game.getInstance().getPlayersOnDevice();
    if(playersOnDevice.contains(currentPlayer.getName())) {
      gc.resumeButton.setValue(true);
    }
    else {
      gc.resumeButton.setValue(false);
    }
    if(gc.disabledObservableList.isEmpty()) {
      gc.disabledObservableList.clear();
    }
    for(Observable<Boolean> o: gc.interactableObservableList) {
      if(o.getValue()) {
        o.setValue(false);
        gc.disabledObservableList.add(o);
      }
    }
  }
}
