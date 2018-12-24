package cabernet1.monopoly.utils.animation;

import java.awt.*;

/**
 * Purpose:    The Path class defines a starting position, stopping
 * position, and number of steps.  It then calculates the
 * next position that would occur along the path.  It is
 * first used in Program 7.1, but is used in all the
 * animation examples, and is an essential part to the
 * final animator.
 * <p>
 * Note that in this case it is a simple straight line,
 * but the path could be a spline curve, or Bessel
 * function, etc.
 */

public class StraightLinePath implements Path {
    private int startX, startY, endX, endY, steps;
    private int currentStep = -1;      // This makes the first step 0
    private double deltaX, deltaY;

    /**
     * Constructor Stores the points, and builds the information
     * needed to construct the next point.  Note that for a path we
     * need the initial point for the path (X1, Y1), the final point
     * for the path (X2, Y2), and the number of steps in the path (
     * numSteps).
     */
    public StraightLinePath(int X1, int Y1, int X2, int Y2, int numSteps) {
        startX = X1;
        startY = Y1;
        endX = X2;
        endY = Y2;
        steps = numSteps;
        deltaX = ((double) (X2 - X1)) / steps;
        deltaY = ((double) (Y2 - Y1)) / steps;
    }

    /**
     * Check to see if the path has MoreSteps
     */
    public boolean hasMoreSteps() {
        return currentStep <= steps;
    }

    /**
     * Get the next position.  If the path has no more steps, return
     * the current position.
     */
    public Point nextPosition() {
        currentStep++;
        return curPosition();
    }

    public Point curPosition() {
        if (currentStep > steps)
            return new Point(endX, endY);
        return new Point((int) (startX + (deltaX * currentStep)),
                (int) (startY + (deltaY * currentStep)));
    }
}

