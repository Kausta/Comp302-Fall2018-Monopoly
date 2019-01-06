package cabernet1.monopoly.ui.panels;

import cabernet1.monopoly.ui.dice.DieImage;
import cabernet1.monopoly.ui.dice.RegularDieImage;
import cabernet1.monopoly.ui.dice.SpeedDieImage;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class DicePanel extends BasePanel {
    // Height and width coefficient constants
    private static final double HEIGHT_COEFFICIENT = 1;
    private static final double WIDTH_COEFFICIENT = 7.5;
    private static volatile DicePanel _instance = null;

    private DicePanel() {
        setBackground(new Color(237, 240, 244));
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        // Adjusting the size of panel with the coefficients
        adjustSize(HEIGHT_COEFFICIENT, WIDTH_COEFFICIENT);
        initializeDice();
    }
    private void initializeDice(){
        DieImage die1=new RegularDieImage();
        DieImage die2=new RegularDieImage();
        DieImage die3=new SpeedDieImage();
        die1.startObserving(controller.die1Observable);
        die2.startObserving(controller.die2Observable);
        die3.startObserving(controller.speedDieObservable);

        add(die1,BorderLayout.WEST);
        add(die2,BorderLayout.CENTER);
        add(die3,BorderLayout.EAST);
    }
    public static synchronized DicePanel getInstance() {
        if (_instance == null) {
            _instance = new DicePanel();
        }
        return _instance;
    }
}
