package cabernet1.monopoly.ui.tabbedpanes;

import cabernet1.monopoly.ui.tabbedpanes.tabs.PlayersTab;
import cabernet1.monopoly.ui.tabbedpanes.tabs.PropertiesTab;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class DetailsTabbedPane extends BaseTabbedPane {

    // Height and width coefficient constants
    private final double HEIGHT_COEFFICIENT = 3;
    private final double WIDTH_COEFFICIENT = 7.5;

    private static volatile DetailsTabbedPane _instance = null;

    public DetailsTabbedPane () {

        // Adjusting the size of panel with the coefficients
        adjustSize(HEIGHT_COEFFICIENT, WIDTH_COEFFICIENT);

        // Initialize Panel
        initialize();
    }

    public static synchronized DetailsTabbedPane getInstance() {
        if (_instance == null) {
            _instance = new DetailsTabbedPane();
        }
        return _instance;
    }

    private void initialize() {
        // Tab 1
        addTab("Players Statistics", null, PlayersTab.getInstance(), null);

        // Tab2
        addTab("Deeds Statistics", null, PropertiesTab.getInstance(), null);
    }
}
