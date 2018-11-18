package cabernet1.monopoly.ui;

import cabernet1.monopoly.domain.InitializationController;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;
import cabernet1.monopoly.ui.components.Form;

import javax.swing.*;

public class InitializationView extends BaseView {
    private static final String ACTION_COMMAND_SERVER = "SERVER";
    private static final String ACTION_COMMAND_CLIENT = "CLIENT";

    private static volatile InitializationView _instance = null;
    private Logger logger = LoggerFactory.getInstance().getLogger(getClass());
    private InitializationController controller;
    private JPanel root;

    private InitializationView() {
    }

    public static synchronized InitializationView getInstance() {
        if (_instance == null) {
            _instance = new InitializationView();
        }
        return _instance;
    }

    public void initialize(InitializationController controller) {
        this.controller = controller;

        this.initializeModeSelect();
        logger.i("Initialized Initial UI");
    }

    private void initializeModeSelect() {
        this.root = new JPanel();

        Form form = new Form.Builder()
                .addButton("Start Server Mode", () -> {
                    controller.setServer(true);
                    this.initializeServerParameters();
                })
                .addButton("Start Client Mode", () -> {
                    controller.setServer(false);
                    this.initializeClientParameters();
                })
                .build();

        addToCenter(form.getContent());
    }

    private void initializeServerParameters() {
        JTextField portField = new JTextField();
        Form form = new Form.Builder()
                .addLabeledComponent("Port: ", portField)
                .addButton("Start", () -> {
                    try {
                        int port = Integer.parseInt(portField.getText());
                        controller.startServer(port);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .build();

        addToCenter(form.getContent());
    }

    private void initializeClientParameters() {
        JTextField ipField = new JTextField();
        JTextField portField = new JTextField();
        Form form = new Form.Builder()
                .addLabeledComponent("Server IP: ", ipField)
                .addLabeledComponent("Server Port: ", portField)
                .addButton("Start", () -> {
                    try {
                        String ip = ipField.getText();
                        int port = Integer.parseInt(portField.getText());
                        controller.startClient(ip, port);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .build();

        addToCenter(form.getContent());
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
