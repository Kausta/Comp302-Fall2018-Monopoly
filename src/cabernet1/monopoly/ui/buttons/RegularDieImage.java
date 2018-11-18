package cabernet1.monopoly.ui.buttons;

import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;
import cabernet1.monopoly.utils.Observable;
import cabernet1.monopoly.utils.Observer;
import cabernet1.monopoly.utils.ResourceManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RegularDieImage extends JPanel implements Observer<Integer> {

    public Logger logger = LoggerFactory.getInstance().getLogger(getClass()); // For enabling to usage of logger for all panels
    private BufferedImage dieImage;
    private JLabel dieImageLabel = new JLabel();


    public RegularDieImage() {
        add(dieImageLabel);
        dieImageLabel.setVisible(true);
    }

    public void startObserving(Observable<Integer> observable) {
        observable.addObserver(this);
    }

    private String getDieImage(int dieNum) {
        String image = "";
        switch(dieNum) {
            case 1:
                image = ResourceManager.getInstance().getResourcePath("dice/dice_1.jpeg").getPath();
                break;
            case 2:
                image = ResourceManager.getInstance().getResourcePath("dice/dice_2.jpeg").getPath();
                break;
            case 3:
                image = ResourceManager.getInstance().getResourcePath("dice/dice_3.jpeg").getPath();
                break;
            case 4:
                image = ResourceManager.getInstance().getResourcePath("dice/dice_4.jpeg").getPath();
                break;
            case 5:
                image = ResourceManager.getInstance().getResourcePath("dice/dice_5.jpeg").getPath();
                break;
            case 6:
                image = ResourceManager.getInstance().getResourcePath("dice/dice_6.jpeg").getPath();
                break;
            default:
                logger.d("Invalid die type is entered!");
                break;
        }
        return image;
    }

    public void drawDie(int dieNum) {
        try {
            String path = getDieImage(dieNum);
            remove(dieImageLabel);
            dieImage = ImageIO.read(new File(path));
            Image scaledDie = dieImage.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            dieImageLabel = new JLabel(new ImageIcon(scaledDie));
            add(dieImageLabel);
        } catch (IOException ex) {
        }
    }

    @Override
    public void onValueChanged(Integer value) {
        logger.d("" + value);
        drawDie(value);
    }
}
