package cabernet1.monopoly.ui.buttons;

public class PauseButton extends BaseButton {

  private static volatile PauseButton _instance = null;

  private PauseButton() {
    setText("Pause");
    initialize();
  }

  public static synchronized PauseButton getInstance() {
    if (_instance == null) {
      _instance = new PauseButton();
    }
    return _instance;
  }

  private void initialize() {
    controller.rollButton.addObserver(this);

    addActionListener(e -> {
      controller.pauseGame();
    });
  }

  @Override
  public void onValueChanged(Boolean value) {
    // Change visibility
    if (value) {
      setVisible(true);
    } else {
      setVisible(false);
    }
  }
}
