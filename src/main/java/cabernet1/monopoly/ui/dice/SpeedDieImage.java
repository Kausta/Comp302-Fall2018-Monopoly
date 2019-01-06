package cabernet1.monopoly.ui.dice;

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

public class SpeedDieImage extends DieImage {

    public SpeedDieImage() {
        add(dieImageLabel);
        dieImageLabel.setVisible(true);
        drawDie(1);
    }

    protected String getDieImage(int dieNum) {
        String image = "";
        switch (dieNum) {
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
                image = ResourceManager.getInstance().getResourcePath("dice/mr-monopoly.png").getPath();
                break;
            case 5:
                image = ResourceManager.getInstance().getResourcePath("dice/mr-monopoly.png").getPath();
                break;
            case 6:
                image = ResourceManager.getInstance().getResourcePath("dice/bus-icon.PNG").getPath();
                break;
            default:
                logger.d("Invalid die type is entered!");
                break;
        }
        return image;
    }

}
