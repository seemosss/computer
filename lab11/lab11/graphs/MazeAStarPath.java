package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int x1 = maze.toX(v);
        int y1 = maze.toY(v);
        int x2 = maze.toX(t);
        int y2 = maze.toY(t);
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        marked[s] = true;
        announce();
        if (s == t) return;
        int minw = s, mind = Integer.MAX_VALUE;
        for (int w : maze.adj(s)) {
            if (!marked[w] && h(w) < mind) {
                minw = w;
                mind = h(w);
            }
        }
        edgeTo[minw] = s;
        distTo[minw] = mind;
        astar(minw);
    }

    @Override
    public void solve() {
        astar(s);
    }

}

