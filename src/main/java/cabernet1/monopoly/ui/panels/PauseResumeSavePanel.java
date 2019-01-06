package cabernet1.monopoly.ui.panels;

import cabernet1.monopoly.ui.buttons.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class PauseResumeSavePanel extends BasePanel {

    // Height and width coefficient constants
    private static final double HEIGHT_COEFFICIENT = 0.35;
    private static final double WIDTH_COEFFICIENT = 7.5;
    private static volatile PauseResumeSavePanel _instance = null;

    private PauseResumeSavePanel() {
        setBackground(new Color(237, 240, 244));
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        // Adjusting the size of panel with the coefficients
        adjustSize(HEIGHT_COEFFICIENT, WIDTH_COEFFICIENT);

        // Initialize the panel
        initialize();
    }

    public static synchronized PauseResumeSavePanel getInstance() {
        if (_instance == null) {
            _instance = new PauseResumeSavePanel();
        }
        return _instance;
    }

    private void initialize() {
        add(PauseButton.getInstance(), BorderLayout.CENTER);
        add(ResumeButton.getInstance(), BorderLayout.CENTER);
        add(SaveButton.getInstance(), BorderLayout.CENTER);
    }
}
