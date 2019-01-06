package cabernet1.monopoly.ui.tabbedpanes.tabs;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;
import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.property.Property;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;
import cabernet1.monopoly.utils.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class PropertiesTab extends JScrollPane implements Observer<ArrayList<Tile>> {
    private static volatile PropertiesTab _instance = null;
    public final Logger logger = LoggerFactory.getInstance().getLogger(getClass()); // For enabling to usage of logger for all panels
    public final GameController controller = Game.getInstance().getGameController();
    private JTable propertiesTable;
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
        controller.tileListObservable.addObserver(this);

        propertiesTable = new JTable(new DefaultTableModel(new Object[][]{}, new String[]{"Property Name", "Owner", "Group", "Rent"}));
        setViewportView(propertiesTable);

        propertiesTable.getColumnModel().getColumn(0).setResizable(false);
        propertiesTable.getColumnModel().getColumn(0).setPreferredWidth(300);
        propertiesTable.getColumnModel().getColumn(1).setResizable(false);
        propertiesTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        propertiesTable.getColumnModel().getColumn(2).setResizable(false);
        propertiesTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        propertiesTable.getColumnModel().getColumn(3).setResizable(false);
        propertiesTable.getColumnModel().getColumn(3).setPreferredWidth(200);
    }
    @Override
    public void onValueChanged(ArrayList<Tile> tileList) {
        logger.d("Value changed");
        DefaultTableModel model = (DefaultTableModel) propertiesTable.getModel();
        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        logger.d("tile list size is "+tileList.size());
        int counter=0;
        for (Tile p : tileList) {
            if (p instanceof GroupColoredProperty) {
                ++counter;
                GroupColoredProperty prop=(GroupColoredProperty)p;
                String owner=prop.getOwner()!=null?prop.getOwner().getName():"No Owner";
                model.addRow(new Object[]{p.getName(), owner,prop.getColorGroup(), ""+prop.getRent()});
            }
        }
        logger.d("Got "+counter+" group properties");
    }
}
