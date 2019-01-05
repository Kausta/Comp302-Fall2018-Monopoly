package cabernet1.monopoly.utils.animation;


import java.awt.*;

/**
 * Purpose:    The Path interface allows a user to define a path that
 * object should travel through, and then simply call the
 * hasMoreSteps and nextPosition methods to walk through
 * the path.
 * <p>
 * Note that in the Path could be a simple straight line,
 * a spline curve, a Bessel function, a Path with multiple
 * segments, etc.
 */

public interface Path {

    /**
     * Check to see if the path has MoreSteps
     */
    boolean hasMoreSteps();

    /**
     * Get the next position.  If the path has no more steps, return
     * the current position.
     */
    Point nextPosition();

    Point curPosition();
}


