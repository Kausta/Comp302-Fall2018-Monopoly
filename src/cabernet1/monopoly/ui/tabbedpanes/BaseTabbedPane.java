package cabernet1.monopoly.ui.tabbedpanes;

import cabernet1.monopoly.logging.Logger;
import cabernet1.monopoly.logging.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public abstract class BaseTabbedPane extends JTabbedPane {
    public Logger logger = LoggerFactory.getInstance().getLogger(getClass()); // For enabling to usage of logger for all panels
    private Dimension size = new Dimension();
    private int height;
    private int width;
    private double heightPiece;
    private double widthPiece;

    public BaseTabbedPane() {

        // Getting the screen size and calculating the size of height and width pieces
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        heightPiece = screenSize.getHeight() / 9;
        widthPiece = screenSize.getWidth() / 16;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    /**
     * Adjusts the tabbed pane size.
     * It takes two coefficient parameters for height and width
     * and calculates the screen size with multiplying these
     * coefficients with heightPiece and widthPiece values.
     *
     * @param hC Height coefficient
     * @param wC Width coefficient
     */
    public void adjustSize(double hC, double wC) {
        height = (int) (heightPiece * hC);
        width = (int) (widthPiece * wC);
        size.height = height;
        size.width = width;
        setPreferredSize(size);
    }
}
