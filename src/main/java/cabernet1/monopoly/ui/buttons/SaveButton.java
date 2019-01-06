package cabernet1.monopoly.ui.buttons;

import cabernet1.monopoly.ui.util.JsonFileType;

import javax.swing.*;
import java.io.File;

public class SaveButton extends BaseButton {

    private static volatile SaveButton _instance = null;

    private SaveButton() {
        setText("Save");
        initialize();
    }

    public static synchronized SaveButton getInstance() {
        if (_instance == null) {
            _instance = new SaveButton();
        }
        return _instance;
    }

    private void initialize() {
        controller.saveButton.addObserver(this);

        addActionListener(e -> {
            File file = chooseSaveFile();
            if (file == null) {
                JOptionPane.showMessageDialog(this, "Cannot save, you haven't choose a file!");
            }
            controller.saveGame(file);
        });
    }

    @Override
    public void onValueChanged(Boolean value) {
        // Change visibility
        if (value) {
            setVisible(true);
        } else {
            setVisible(false);
        }
    }

    private File chooseSaveFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new JsonFileType());
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        return chooser.getSelectedFile();
    }
}
