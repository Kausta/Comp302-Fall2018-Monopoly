package cabernet1.monopoly.ui.players;

import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.player.IPlayer;
import cabernet1.monopoly.utils.animation.Animatable;
import cabernet1.monopoly.utils.animation.ComplexPath;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends JPanel implements Animatable {
    ComplexPath path;
    IPlayer player;
    int shiftAmount=20;
    boolean firstTime;
    private final int playerSideLength = 50;
    private volatile BufferedImage playerImage;
    public Player(IPlayer player){
        super();
        this.player=player;
        this.firstTime=true;
        int ID=player.getID()+1;
         synchronized (this) {
            try {
                String playerImageName = ID <= 8 ? "Player_" + ID : "Player_8";
                playerImage = ImageIO.read(this.getClass().getClassLoader().getResource("player/" + playerImageName + ".png"));
                 //playerImage = ImageIO.read(this.getClass().getClassLoader().getResource("player/player_1.png"));
                playerImage = resize(playerImage, playerSideLength, playerSideLength);
            } catch (IOException e1) {
                System.out.println("Player image loading failed");
            }
        }
        updatePath(player.getCurrentTile(),true);
        setOpaque(false);

    }

    void updatePath(Tile newTile, boolean takeRailRoads){
        ArrayList<ArrayList<Integer>> res=Board.getInstance().getPath(player.getCurrentTile(),newTile,player.getDirection(),takeRailRoads);
        path=new ComplexPath(res.get(0),res.get(1));

    }
    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
    private int adjustX(int x){
        x+=player.xShift()*shiftAmount;
        x=(int)(x*990.0/1927);
        x-=playerSideLength/2;
        return x;
    }
    private int adjustY(int y){
        y+=player.yShift()*shiftAmount;
        y=(int)(y*990.0/1927);
        y-=playerSideLength/2;
        return y;
    }
    public Rectangle boundingBox() {
        // effect: Returns the smallest rectangle that completely covers the
        // current position of the ball.

        // a Rectangle is the x,y for the upper left corner and then the
        // width and height
        return new Rectangle(adjustX(path.curPosition().x), adjustY(path.curPosition().y)
                , playerSideLength + 1, playerSideLength + 1);
    }

    public void paint(Graphics g) {
        // modifies: <g>
        // effects: Repaints the Graphics area <g>. Swing will then send the
        // newly painted g to the screen.

        // first repaint the proper background color (controlled by
        // the windowing system)
        super.paint(g);

        Rectangle clipRect = g.getClipBounds();
        g.drawImage(playerImage, adjustX(path.curPosition().x), adjustY(path.curPosition().y),null);
        if (clipRect.intersects(this.boundingBox())) {
        }

    }
    public void animate() {
        // modifies: <g>
        // effects: Repaints the Graphics area <g>. Swing will then send the
        // newly painted g to the screen.

        // first repaint the proper background color (controlled by
        // the windowing system)
        Rectangle oldPos = boundingBox();
        if (firstTime){
            Rectangle repaintArea = oldPos.union(boundingBox());
            repaint(repaintArea.x, repaintArea.y, repaintArea.width, repaintArea.height);
            firstTime=false;
            return;
        }
        if (path==null || !path.hasMoreSteps()){

            return;
        }

        Point nextPoint=path.nextPosition();
        Rectangle repaintArea = oldPos.union(boundingBox());
        repaint(repaintArea.x, repaintArea.y, repaintArea.width, repaintArea.height);
    }
}
