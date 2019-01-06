package cabernet1.monopoly.ui.buttons;

public class ResumeButton extends BaseButton {

  private static volatile ResumeButton _instance = null;

  private ResumeButton() {
    setText("Resume");
    initialize();
  }

  public static synchronized ResumeButton getInstance() {
    if (_instance == null) {
      _instance = new ResumeButton();
    }
    return _instance;
  }

  private void initialize() {
    controller.resumeButton.addObserver(this);
    addActionListener(e -> {
      controller.resumeGame();
    });
    setVisible(false);
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
