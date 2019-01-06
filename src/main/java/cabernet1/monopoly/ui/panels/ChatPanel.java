package cabernet1.monopoly.ui.panels;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.ui.scrollpanes.ChatScrollPane;
import cabernet1.monopoly.ui.scrollpanes.LogScrollPane;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChatPanel extends BasePanel {

    // Height and width coefficient constants
    private static final double HEIGHT_COEFFICIENT = 0.3;
    private static final double WIDTH_COEFFICIENT = 7.5;
    private static volatile ChatPanel _instance = null;

    private ChatPanel() {
        setBackground(new Color(212, 216, 221));
        //setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        // Adjusting the size of panel with the coefficients
        adjustSize(HEIGHT_COEFFICIENT, WIDTH_COEFFICIENT);
        JTextField entry = new JTextField();
        entry.setPreferredSize(new Dimension(500, 25));
        entry.setEditable(true);

        add(entry);
        entry.setVisible(true);

        JButton send = new JButton("Send");
        send.addActionListener(e -> {
            Game.getInstance().getGameController().sendChatMessage(entry.getText(), "[" + Game.getInstance().getCurrentPlayer().getName() + "]" + ": " + entry.getText());
            entry.setText("");
        });
        add(send);
        send.setVisible(true);
    }


    public static synchronized ChatPanel getInstance() {
        if (_instance == null) {
            _instance = new ChatPanel();
        }
        return _instance;
    }
}
