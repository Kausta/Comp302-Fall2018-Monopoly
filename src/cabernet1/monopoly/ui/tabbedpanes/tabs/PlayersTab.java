package cabernet1.monopoly.ui.tabbedpanes.tabs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PlayersTab extends JScrollPane {
    private static volatile PlayersTab _instance = null;

    public PlayersTab() {
        // Initialize Table
        initialize();
    }

    public static synchronized PlayersTab getInstance() {
        if (_instance == null) {
            _instance = new PlayersTab();
        }
        return _instance;
    }

    public void initialize() {
        JTable playerTable = new JTable();
        setViewportView(playerTable);
        playerTable.setModel(
            new DefaultTableModel(new Object[][]{}, new String[]{"Player Name", "Color", "Current Money"}) {
                Class[] columnTypes = new Class[]{String.class, String.class, Integer.class};
                public Class getColumnClass(int columnIndex) {
                    return columnTypes[columnIndex];
                }
            }
        );
        playerTable.getColumnModel().getColumn(0).setResizable(false);
        playerTable.getColumnModel().getColumn(0).setPreferredWidth(600);
        playerTable.getColumnModel().getColumn(1).setResizable(false);
        playerTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        playerTable.getColumnModel().getColumn(2).setResizable(false);
        playerTable.getColumnModel().getColumn(2).setPreferredWidth(200);
    }
}
