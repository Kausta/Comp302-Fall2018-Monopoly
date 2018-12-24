package cabernet1.monopoly.ui.buttons;

public class BuyPropertyButton extends BaseButton {

    private static volatile BuyPropertyButton _instance = null;

    private BuyPropertyButton() {
        setText("Buy Property");
        initialize();
    }

    public static synchronized BuyPropertyButton getInstance() {
        if (_instance == null) {
            _instance = new BuyPropertyButton();
        }
        return _instance;
    }

    private void initialize() {
        controller.buyButton.addObserver(this);

        addActionListener(e -> {
            // TODO: Add a rollDice function to GameController
            controller.buyProperty();
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
