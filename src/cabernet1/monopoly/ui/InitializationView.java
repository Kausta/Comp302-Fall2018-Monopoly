package cabernet1.monopoly.ui;

import cabernet1.monopoly.domain.InitializationController;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;
import cabernet1.monopoly.ui.components.Form;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InitializationView extends BaseView {
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
        this.initializeObservers();
        this.initializeModeSelect();
        logger.i("Initialized Initial UI");
    }

    private void initializeObservers() {
        controller.getConnectionObservable().addObserver(connected -> {
            if (!connected) {
                JOptionPane.showMessageDialog(getRoot(), "Cannot connect or start server!");
            } else {
                initializePlayerNames();
            }
        });
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
                        JOptionPane.showMessageDialog(getRoot(), "Cannot parse input");
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
                .addVerticalSpace(5)
                .addLabeledComponent("Server Port: ", portField)
                .addButton("Start", () -> {
                    try {
                        String ip = ipField.getText();
                        int port = Integer.parseInt(portField.getText());
                        controller.startClient(ip, port);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(getRoot(), "Cannot parse input");
                    }
                })
                .build();

        addToCenter(form.getContent());
    }

    private void initializePlayerNames() {
        List<JTextField> playerNames = IntStream.range(0, 4)
                .mapToObj(x -> new JTextField())
                .collect(Collectors.toList());

        Form.Builder formBuilder = new Form.Builder();
        for (int i = 0; i < playerNames.size(); i++) {
            formBuilder.addLabeledComponent("Player " + i + ": ", playerNames.get(i))
                    .addVerticalSpace(5);
        }
        formBuilder.addButton("Add Players", () -> {
            List<String> nonEmptyFields = playerNames.stream()
                    .map(JTextComponent::getText)
                    .filter(str -> !str.isEmpty())
                    .collect(Collectors.toList());

            if (nonEmptyFields.isEmpty()) {
                JOptionPane.showMessageDialog(getRoot(), "You need to enter at least 1 player name!");
                return;
            }
            controller.initializePlayerNames(nonEmptyFields);
            if (controller.isServer()) {
                initializeServerGameStart();
            } else {
                initializeClientGameStart();
            }
        });
        Form form = formBuilder.build();

        addToCenter(form.getContent());
    }

    private void initializeServerGameStart() {
        Form form = new Form.Builder()
                .addLabel("Waiting for clients to connect")
                .addVerticalSpace(5)
                .addLabel("You can start the game at any point as the server")
                .addButton("Start The Game", () -> {
                    controller.startGame();
                })
                .build();

        addToCenter(form.getContent());
    }

    private void initializeClientGameStart() {
        Form form = new Form.Builder()
                .addLabel("Waiting for the server to start the game...")
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
