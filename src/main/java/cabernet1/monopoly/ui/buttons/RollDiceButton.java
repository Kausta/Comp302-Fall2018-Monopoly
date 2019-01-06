package cabernet1.monopoly.ui.buttons;

public class RollDiceButton extends BaseButton {

    private static volatile RollDiceButton _instance = null;

    private RollDiceButton() {
        setText("Roll Dice");
        initialize();
    }

    public static synchronized RollDiceButton getInstance() {
        if (_instance == null) {
            _instance = new RollDiceButton();
        }
        return _instance;
    }

    private void initialize() {
        controller.rollButton.addObserver(this);

        addActionListener(e -> {
            // TODO: Add a rollDice function to GameController
            controller.rollDice();
            logger.d("Roll Dice button is clicked");
           // controller.pauseGame();
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
