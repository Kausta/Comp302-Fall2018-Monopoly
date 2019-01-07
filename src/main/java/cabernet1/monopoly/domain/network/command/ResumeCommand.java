package cabernet1.monopoly.domain.network.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.game.player.Player;
import cabernet1.monopoly.utils.Observable;
import cabernet1.monopoly.utils.animation.Animator;

import java.util.List;

public class ResumeCommand extends ICommand {

  private static final long serialVersionUID = -963326140316347466L;

  public ResumeCommand() {

  }

  @Override
  public void execute() {
    GameController gc = Game.getInstance().getGameController();
    gc.resumeButton.setValue(false);
    Animator.getInstance().setVisible(true);
    IPlayer currentPlayer = Game.getInstance().getCurrentPlayer();
    List<String> playersOnDevice = Game.getInstance().getPlayersOnDevice();
    if(currentPlayer.isOnThisDevice()) {
      gc.pauseButton.setValue(true);
    }
    else {
      gc.pauseButton.setValue(false);
    }
    gc.saveButton.setValue(false);
    for(Observable<Boolean> o: gc.disabledObservableList) {
      o.setValue(true);
    }
  }
}
