package cabernet1.monopoly.utils.animation;

import java.awt.*;
import java.util.ArrayList;

public class ComplexPath implements Path {
    ArrayList<StraightLinePath> path;
    private int numSteps = 10;
    private int curPathIdx;

    public ComplexPath(ArrayList<Integer> xArr, ArrayList<Integer> yArr) {
        assert (yArr.size() == xArr.size());
        assert (!yArr.isEmpty());
        path = new ArrayList<>();
        if (yArr.size() == 1) {
            path.add(new StraightLinePath(xArr.get(0), yArr.get(0), xArr.get(0), yArr.get(0), numSteps));
        } else {
            for (int i = 0; i < xArr.size() - 1; ++i) {
                path.add(new StraightLinePath(xArr.get(i), yArr.get(i)
                        , xArr.get(i + 1), yArr.get(i + 1), numSteps));
            }
        }
        curPathIdx = 0;
    }

    /**
     * Check to see if the path has MoreSteps
     */
    public boolean hasMoreSteps() {
        return curPathIdx != path.size() - 1 || path.get(curPathIdx).hasMoreSteps();
    }

    /**
     * Get the next position.  If the path has no more steps, return
     * the current position.
     */
    public Point nextPosition() {
        StraightLinePath curPath = path.get(curPathIdx);
        if (!path.get(curPathIdx).hasMoreSteps() && curPathIdx + 1 < path.size())
            ++curPathIdx;
        return path.get(curPathIdx).nextPosition();
    }

    public Point curPosition() {
        return path.get(curPathIdx).curPosition();
    }
}
