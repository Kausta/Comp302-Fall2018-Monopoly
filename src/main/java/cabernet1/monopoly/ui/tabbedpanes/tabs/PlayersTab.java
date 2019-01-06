package cabernet1.monopoly.ui.tabbedpanes.tabs;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;
import cabernet1.monopoly.utils.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class PlayersTab extends JScrollPane implements Observer<ArrayList<IPlayer>> {
    private static volatile PlayersTab _instance = null;
    public final Logger logger = LoggerFactory.getInstance().getLogger(getClass()); // For enabling to usage of logger for all panels
    public final GameController controller = Game.getInstance().getGameController();
    private JTable playerTable;

    private PlayersTab() {
        // Initialize Table
        initialize();
    }

    public static synchronized PlayersTab getInstance() {
        if (_instance == null) {
            _instance = new PlayersTab();
        }
        return _instance;
    }

    private void initialize() {
        controller.playerListObservable.addObserver(this);

        playerTable = new JTable(new DefaultTableModel(new Object[][]{}, new String[]{"Player Name", "Current Position", "Current Money"}));
        setViewportView(playerTable);

        /*playerTable.setModel(
                new DefaultTableModel(new Object[][]{}, new String[]{"Player Name", "Current Position", "Color", "Current Money"}) {
                    Class[] columnTypes = new Class[]{String.class, String.class, Integer.class};

                    public Class getColumnClass(int columnIndex) {
                        return columnTypes[columnIndex];
                    }
                }
        );*/

        DefaultTableModel model = (DefaultTableModel) playerTable.getModel();
        for (IPlayer p : controller.playerList()) {
            String money = "" + p.getMoney();
            model.addRow(new Object[]{p.getName(), p.getCurrentTile().getName(), money});
        }

        playerTable.getColumnModel().getColumn(0).setResizable(false);
        playerTable.getColumnModel().getColumn(0).setPreferredWidth(500);
        playerTable.getColumnModel().getColumn(1).setResizable(false);
        playerTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        playerTable.getColumnModel().getColumn(2).setResizable(false);
        playerTable.getColumnModel().getColumn(2).setPreferredWidth(200);
    }

    @Override
    public void onValueChanged(ArrayList<IPlayer> playerList) {
        logger.d("Value changed");
        DefaultTableModel model = (DefaultTableModel) playerTable.getModel();
        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        for (IPlayer p : playerList) {
            String money = "" + p.getMoney();
            model.addRow(new Object[]{p.getName(), p.getCurrentTile().getName(), money});
        }
    }
}
