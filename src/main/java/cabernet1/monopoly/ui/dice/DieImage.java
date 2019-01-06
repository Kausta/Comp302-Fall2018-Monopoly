package cabernet1.monopoly.ui.dice;

import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;
import cabernet1.monopoly.utils.Observable;
import cabernet1.monopoly.utils.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class DieImage extends JPanel implements Observer<Integer> {
    public final Logger logger = LoggerFactory.getInstance().getLogger(getClass()); // For enabling to usage of logger for all panels
    protected JLabel dieImageLabel = new JLabel();
    private BufferedImage dieImage;

    public void startObserving(Observable<Integer> observable) {
        observable.addObserver(this);
    }

    protected abstract String getDieImage(int dieNum);

    public void drawDie(int dieNum) {

        try {
            String path = getDieImage(dieNum);
            dieImage = ImageIO.read(new File(path));
            Image scaledDie = dieImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            dieImageLabel.setIcon(new ImageIcon(scaledDie));
            dieImageLabel.setVisible(true);

        } catch (IOException ex) {
            logger.e(ex.getMessage());
        }
    }

    @Override
    public void onValueChanged(Integer value) {
        logger.d("" + value);
        drawDie(value);
    }
}
