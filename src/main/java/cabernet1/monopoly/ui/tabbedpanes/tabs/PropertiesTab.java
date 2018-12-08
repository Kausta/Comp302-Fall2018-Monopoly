package cabernet1.monopoly.ui.tabbedpanes.tabs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PropertiesTab extends JScrollPane {
    private static volatile PropertiesTab _instance = null;

    private PropertiesTab() {
        // Initialize Table
        initialize();
    }

    public static synchronized PropertiesTab getInstance() {
        if (_instance == null) {
            _instance = new PropertiesTab();
        }
        return _instance;
    }

    private void initialize() {
        JTable propertiesTable = new JTable();
        setViewportView(propertiesTable);
        propertiesTable.setModel(
                new DefaultTableModel(new Object[][]{}, new String[]{"Property Name", "Owner", "Group", "Rent"}) {
                    Class[] columnTypes = new Class[]{String.class, String.class, Integer.class};

                    public Class getColumnClass(int columnIndex) {
                        return columnTypes[columnIndex];
                    }
                }
        );
        propertiesTable.getColumnModel().getColumn(0).setResizable(false);
        propertiesTable.getColumnModel().getColumn(0).setPreferredWidth(300);
        propertiesTable.getColumnModel().getColumn(1).setResizable(false);
        propertiesTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        propertiesTable.getColumnModel().getColumn(2).setResizable(false);
        propertiesTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        propertiesTable.getColumnModel().getColumn(3).setResizable(false);
        propertiesTable.getColumnModel().getColumn(3).setPreferredWidth(200);
    }
}
