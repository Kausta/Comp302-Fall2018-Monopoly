package cabernet1.monopoly.ui.panels;

import javafx.scene.Parent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BoardPanel extends BasePanel {

    private static volatile BoardPanel _instance = null;
    // Height and width coefficient constants
    private final double HEIGHT_COEFFICIENT = 8.25;
    private final double WIDTH_COEFFICIENT = 8.25;
    private BufferedImage board;
    public JPanel insidePanel;
    private BoardPanel(String path) {
        setLayout(new BorderLayout());
        setBackground(new Color(212, 216, 221));
       // setBackground(new Color(0, 216, 221));
        // Adjusting the size of panel with the coefficients
        adjustSize(HEIGHT_COEFFICIENT, WIDTH_COEFFICIENT);
        insidePanel=new JPanel();
        insidePanel.setBackground(new Color(0, 216, 221));
        insidePanel.setLayout(null);
        insidePanel.setOpaque(false);
        insidePanel.setBounds(0,0,this.getWidth(),this.getHeight());

        this.add(insidePanel,BorderLayout.CENTER);

        // Drawing board to screen

        drawBoard(path);
       // drawBoard("D:/gitLab/302_2018_project_cabarnet1/build/resources/main/player/player_1.png",0);
        logger.i("path isv "+path);

    }

    /**
     * Almost trivial getInstance method to make BoardPanel singleton.
     * In addition to trivial one, it also gets board image as parameter
     * and uses it to add the board image to panel itself.
     *
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
     *
     * @param path Path of the board image in local
     */
    private void drawBoard(String path) {
        try {
            this.board = ImageIO.read(new File(path));
            Image scaledBoard = board.getScaledInstance(insidePanel.getWidth(), insidePanel.getHeight(), Image.SCALE_DEFAULT);
            JLabel boardImageLabel = new JLabel(new ImageIcon(scaledBoard));
            insidePanel.add(boardImageLabel);
            boardImageLabel.setBounds(insidePanel.getBounds());
            boardImageLabel.setVisible(true);
            logger.d("dimension of board are x="+insidePanel.getWidth()+" y="+insidePanel.getHeight());
        } catch (IOException ex) {
        }
    }
}
