package cabernet1.monopoly.ui;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.InitializationController;
import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.domain.network.initial.InitialPlayerInfo;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;
import cabernet1.monopoly.ui.components.Form;
import cabernet1.monopoly.ui.components.PlayerInfoDetailHolder;
import cabernet1.monopoly.ui.util.JsonFileType;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InitializationView extends BaseView {
    private static volatile InitializationView _instance = null;
    private final Logger logger = LoggerFactory.getInstance().getLogger(getClass());
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
                if (controller.isLoadedGame()) {
                    if (controller.isServer()) {
                        this.initializeLoadPickOrigin();
                    } else {
                        this.initializeWaitForClientToLoad();
                    }
                } else {
                    initializePlayerNames();
                }
            }
        });
        controller.getWasFileLoadSuccessful().addObserver(successful -> {
            if (!successful) {
                JOptionPane.showMessageDialog(this.getRoot(), "Cannot load that file :(");
                return;
            }
            JOptionPane.showMessageDialog(this.getRoot(), "Loaded the file, now you need to choose which player is on which pc :)");
            this.initializeLoadPickOrigin();
        });
    }


    private void initializeModeSelect() {
        this.root = new JPanel();

        Form form = new Form.Builder()
                .addButton("Start Server Mode", () -> {
                    controller.setServer(true);
                    this.initializeServerParameters(false);
                })
                .addButton("Start Client Mode", () -> {
                    controller.setServer(false);
                    this.initializeClientParameters(false);
                })
                .addButton("Load Game (in Server Mode)", () -> {
                    controller.setServer(true);
                    this.initializeServerParameters(true);
                })
                .addButton("Join Loaded Game", () -> {
                    controller.setServer(false);
                    this.initializeClientParameters(true);
                })
                .build();

        addToCenter(form.getContent());
    }

    private void initializeServerParameters(boolean isLoaded) {
        JTextField portField = new JTextField();
        Form form = new Form.Builder()
                .addLabeledComponent("Port: ", portField)
                .addButton(isLoaded ? "Load" : "Start", () -> {
                    try {
                        int port = Integer.parseInt(portField.getText());
                        if (!isLoaded) {
                            controller.startServer(port);
                            return;
                        }
                        JFileChooser chooser = new JFileChooser();
                        chooser.setFileFilter(new JsonFileType());
                        if (chooser.showOpenDialog(this.getRoot()) != JFileChooser.APPROVE_OPTION) {
                            return;
                        }
                        File file = chooser.getSelectedFile();
                        controller.startServerLoaded(port, file);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(getRoot(), "Cannot parse input");
                    }
                })
                .build();

        addToCenter(form.getContent());
    }

    private void initializeClientParameters(boolean isLoadMode) {
        JTextField clientNameField = new JTextField();
        JTextField ipField = new JTextField();
        JTextField portField = new JTextField();
        Form form = new Form.Builder()
                .addLabeledComponent("Your Client Name: ", clientNameField)
                .addVerticalSpace(5)
                .addLabeledComponent("Server IP: ", ipField)
                .addVerticalSpace(5)
                .addLabeledComponent("Server Port: ", portField)
                .addButton(isLoadMode ? "Load" : "Start", () -> {
                    try {
                        String clientName = clientNameField.getText();
                        if (clientName == null || clientName.trim().isEmpty() || clientName.trim().equalsIgnoreCase("Server")) {
                            JOptionPane.showMessageDialog(getRoot(), "Your client name cannot be empty or be Server!");
                            return;
                        }
                        clientName = clientName.trim();
                        String ip = ipField.getText();
                        int port = Integer.parseInt(portField.getText());
                        controller.startClient(clientName, ip, port);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(getRoot(), "Cannot parse input");
                    }
                })
                .build();

        addToCenter(form.getContent());
    }

    private void initializePlayerNames() {
        List<PlayerInfoDetailHolder> playerNames = IntStream.range(0, 4)
                .mapToObj(x -> new PlayerInfoDetailHolder())
                .collect(Collectors.toList());

        Form.Builder formBuilder = new Form.Builder();
        for (int i = 0; i < playerNames.size(); i++) {
            formBuilder.addLabeledComponent("Player " + i + ": ", playerNames.get(i).getPlayerName())
                    .addVerticalSpace(5)
                    .addDoubleComponent(playerNames.get(i).getIsBot(), playerNames.get(i).getSelectedStrategy())
                    .addVerticalSpace(5);
        }
        formBuilder.addButton("Add Players", () -> {
            List<InitialPlayerInfo> nonEmptyFields = playerNames.stream()
                    .map(playerInfoDetailHolder -> new InitialPlayerInfo(playerInfoDetailHolder.getPlayerNameField(),
                            playerInfoDetailHolder.getIsBotField(),
                            playerInfoDetailHolder.getSelectedStrategyField()))
                    .filter(info -> !info.getPlayerName().trim().isEmpty() || info.isBot())
                    .collect(Collectors.toList());

            if (nonEmptyFields.isEmpty()) {
                JOptionPane.showMessageDialog(getRoot(), "You need to enter at least 1 player name or select 1 bot!");
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
                .addButton("Start The Game", () -> controller.startGame())
                .build();

        addToCenter(form.getContent());
    }

    private void initializeClientGameStart() {
        Form form = new Form.Builder()
                .addLabel("Waiting for the server to start the game...")
                .build();

        addToCenter(form.getContent());
    }

    private void initializeLoadPickOrigin() {
        Form form = new Form.Builder()
                .addLabel("Waiting for clients to connect")
                .addVerticalSpace(5)
                .addLabel("You can select which player plays at which pc and start the game when you want")
                .addButton("Select Players", this::initializeSelectPlayers)
                .build();

        addToCenter(form.getContent());
    }

    private void initializeSelectPlayers() {
        List<IPlayer> players = Game.getInstance().getPlayers();
        Map<String, String> clientIdentifiers = Network.getInstance().getAllClientNames();
        Form.Builder builder = new Form.Builder()
                .addLabel("Choose who belongs to where: ");
        List<JComboBox<String>> playerOrigins = new ArrayList<>();
        String[] clientNames = clientIdentifiers.keySet().toArray(new String[0]);
        for (int i = 0; i < players.size(); i++) {
            playerOrigins.add(new JComboBox<>(clientNames));
            builder.addLabeledComponent("Player " + players.get(i).getName(), playerOrigins.get(playerOrigins.size() - 1))
                    .addVerticalSpace(5);
        }

        builder.addButton("Start Game", () -> {
            List<String> identifiers = playerOrigins.stream()
                    .map(comboBox -> (String) comboBox.getSelectedItem())
                    .map(clientIdentifiers::get)
                    .collect(Collectors.toList());
            controller.startLoadedGame(players, identifiers);
        });

        Form form = builder.build();
        addToCenter(form.getContent());
    }

    private void initializeWaitForClientToLoad() {
        Form form = new Form.Builder()
                .addLabel("Waiting for the server to load and start the game...")
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
