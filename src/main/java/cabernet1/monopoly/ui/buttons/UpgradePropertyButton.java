package cabernet1.monopoly.ui.buttons;

public class UpgradePropertyButton extends BaseButton {

    private static volatile UpgradePropertyButton _instance = null;

    private UpgradePropertyButton() {
        setText("Upgrade Property");
        initialize();
        setVisible(false);
    }

    public static synchronized UpgradePropertyButton getInstance() {
        if (_instance == null) {
            _instance = new UpgradePropertyButton();
        }
        return _instance;
    }

    private void initialize() {
        controller.upgradeButton.addObserver(this);

        addActionListener(e -> {
            // TODO: Add a rollDice function to GameController
            controller.upgradeBuilding();
        });
    }

    public void onValueChanged(Boolean value) {
        // Change visibility
        if (value) {
            setVisible(true);
        } else {
            setVisible(false);
        }
    }
}
