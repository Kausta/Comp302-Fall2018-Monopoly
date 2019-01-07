package cabernet1.monopoly.ui.dice;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.game.die.SpeedDie;
import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;
import cabernet1.monopoly.utils.Observable;
import cabernet1.monopoly.utils.Observer;
import cabernet1.monopoly.utils.animation.Animatable;
import cabernet1.monopoly.utils.animation.Animator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public abstract class DieImage extends JPanel implements Observer<Integer>, Animatable {
    public final Logger logger = LoggerFactory.getInstance().getLogger(getClass()); // For enabling to usage of logger for all panels
    protected JLabel dieImageLabel = new JLabel();
    private BufferedImage dieImage;
    private int animatorCounter=0;
    private final int totalAnimationTime=10;
    private int valueOfDie;

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
        valueOfDie=value;
        animatorCounter=0;
        Animator.getInstance().addDrawable(this);
    }
    public void animate(){
        if (animatorCounter==totalAnimationTime){
            Animator.getInstance().removeDrawable(this);
            drawDie(valueOfDie);
            if (this instanceof SpeedDieImage) {
                Game.getInstance().getGameController().finishedRollingDice();
            }
        }else {
            ++animatorCounter;
            drawDie(new Random().nextInt(6) + 1);
        }
    }
}
