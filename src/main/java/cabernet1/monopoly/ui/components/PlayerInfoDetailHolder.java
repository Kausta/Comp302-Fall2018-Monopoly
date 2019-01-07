package cabernet1.monopoly.ui.components;

import cabernet1.monopoly.domain.game.bot.BotLevel;

import javax.swing.*;

public class PlayerInfoDetailHolder {
    private JTextField playerName;
    private JCheckBox isBot;
    private JComboBox<BotLevel> selectedStrategy;

    public PlayerInfoDetailHolder() {
        playerName = new JTextField();
        isBot = new JCheckBox("Is that player a bot ?");
        selectedStrategy = new JComboBox<>(BotLevel.values());
    }

    public JTextField getPlayerName() {
        return playerName;
    }

    public String getPlayerNameField() {
        return playerName.getText();
    }

    public JCheckBox getIsBot() {
        return isBot;
    }

    public boolean getIsBotField() {
        return isBot.isSelected();
    }

    public JComboBox<BotLevel> getSelectedStrategy() {
        return selectedStrategy;
    }

    public BotLevel getSelectedStrategyField() {
        return (BotLevel) selectedStrategy.getSelectedItem();
    }
}
