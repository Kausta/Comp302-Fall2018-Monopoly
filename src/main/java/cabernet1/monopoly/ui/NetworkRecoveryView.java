package cabernet1.monopoly.ui;

import cabernet1.monopoly.domain.InitializationController;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

import javax.swing.*;

public class NetworkRecoveryView extends BaseView {
    private static volatile NetworkRecoveryView _instance = null;
    private final Logger logger = LoggerFactory.getInstance().getLogger(getClass());
    private JPanel root;

    private NetworkRecoveryView() {
        initialize();
    }

    public static synchronized NetworkRecoveryView getInstance() {
        if (_instance == null) {
            _instance = new NetworkRecoveryView();
        }
        return _instance;
    }

    private void initialize() {
        root = new JPanel();
        addToCenter(new JLabel("Waiting for the network recovery algorithm to complete..."));
    }

    private void addToCenter(JComponent component) {
        root.removeAll();

        SpringLayout layout = new SpringLayout();
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, component, 0, SpringLayout.HORIZONTAL_CENTER, root);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, component, 0, SpringLayout.VERTICAL_CENTER, root);
        root.setLayout(layout);
        root.add(component);
        root.validate();
        root.repaint();

    }

    @Override
    public JPanel getRoot() {
        return root;
    }
}
