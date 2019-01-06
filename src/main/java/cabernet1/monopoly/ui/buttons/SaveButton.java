package cabernet1.monopoly.ui.buttons;

public class SaveButton extends BaseButton {

  private static volatile SaveButton _instance = null;

  private SaveButton() {
    setText("Save");
    initialize();
  }

  public static synchronized SaveButton getInstance() {
    if (_instance == null) {
      _instance = new SaveButton();
    }
    return _instance;
  }

  private void initialize() {
    controller.saveButton.addObserver(this);

    addActionListener(e -> {
      // controller.saveGame();
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
