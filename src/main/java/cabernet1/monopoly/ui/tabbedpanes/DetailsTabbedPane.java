package cabernet1.monopoly.ui.tabbedpanes;

import cabernet1.monopoly.ui.tabbedpanes.tabs.PlayersTab;
import cabernet1.monopoly.ui.tabbedpanes.tabs.PropertiesTab;

public class DetailsTabbedPane extends BaseTabbedPane {

    // Height and width coefficient constants
    private static final double HEIGHT_COEFFICIENT = 3;
    private static final double WIDTH_COEFFICIENT = 7.5;
    private static volatile DetailsTabbedPane _instance = null;

    private DetailsTabbedPane() {

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
        addTab("Players", null, PlayersTab.getInstance(), null);

        // Tab2
        addTab("Properties", null, PropertiesTab.getInstance(), null);
    }
}
