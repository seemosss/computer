package lab11.graphs;

import edu.princeton.cs.algs4.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    public int[] parent;
    Stack<Integer> s = new Stack<>();

    public MazeCycles(Maze m) {
        super(m);
        parent = new int[m.V() + 1];
    }

    @Override
    public void solve() {
        dfs();
    }

    // Helper methods go here
    public void dfs() {
        marked[1] = true;
        parent[1] = 1;
        distTo[1] = 1;
        announce();
        s.push(1);
        while (!s.isEmpty()) {
            int v = s.peek();
            int flag = 0;
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    parent[w] = v;
                    marked[w] = true;
                    distTo[w] = distTo[v] + 1;
                    announce();
                    s.push(w);
                    flag = 1;
                    break;
                }
                else if (parent[v] != w && parent[w] != v) {
                    showcycle(v, w);
                    return;
                }
            }
            if (flag == 0) {
                s.pop();
            }
        }
    }

    public void showcycle(int v, int w) {
        edgeTo[w] = v;
        announce();
        while (parent[v] != w) {
            edgeTo[v] = parent[v];
            announce();
            v = parent[v];
        }
        edgeTo[v] = parent[v];
        announce();
    }
}

