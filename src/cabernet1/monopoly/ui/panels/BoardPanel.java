package cabernet1.monopoly.ui.panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class BoardPanel extends BasePanel {

    // Height and width coefficient constants
    private final double HEIGHT_COEFFICIENT = 8.25;
    private final double WIDTH_COEFFICIENT = 8.25;

    private static volatile BoardPanel _instance = null;
    private BufferedImage board;

    private BoardPanel (String path) {

        // Adjusting the size of panel with the coefficients
        adjustSize(HEIGHT_COEFFICIENT, WIDTH_COEFFICIENT);

        // Drawing board to screen
        drawBoard(path);
    }

    /**
     * Almost trivial getInstance method to make BoardPanel singleton.
     * In addition to trivial one, it also gets board image as parameter
     * and uses it to add the board image to panel itself.
     * @param path Path of the board image in local.
     * @return Instance of Singleton object.
     */
    public static synchronized BoardPanel getInstance(String path) {
        if (_instance == null) {
            _instance = new BoardPanel(path);
        }
        return _instance;
    }

    /**
     * Draws the board image to screen.
     * First it reads the image file from the given path.
     * Then it scale the image to fit the panel.
     * After that it creates a new JLabel with initializing a new ImageIcon with the scaled image.
     * Finally, it adds the created JLabel to panel itself.
     * @param path Path of the board image in local
     */
    private void drawBoard(String path) {
        try {
            this.board = ImageIO.read(new File(path));
            Image scaledBoard = board.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
            JLabel boardImageLabel = new JLabel(new ImageIcon(scaledBoard));
            add(boardImageLabel);
            boardImageLabel.setVisible(true);
        }
        catch (IOException ex) {
        }
    }
}
