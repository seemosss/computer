package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    public int[][] grid;
    private int count = 0;
    private int first = -1, last = -2;
    private static int thisN;
    WeightedQuickUnionUF w;
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        grid = new int[N][N];
        thisN = N;
        w = new WeightedQuickUnionUF(N*N);
    } // create N-by-N grid, with all sites initially blocked
    public void open(int row, int col) {
        if (row < 0 || row >= thisN || col < 0 || col >= thisN) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (!isOpen(row, col)) {
            grid[row][col] = 1;
            int thisnum = row * thisN + col;
            if (first == -1 && row == 0) {
                first = thisnum;
            }
            if (last == -2 && row == thisN - 1) {
                last = thisnum;
            }
            if (row == 0) {
                w.union(thisnum, first);
            }
            if (row == thisN - 1)
                w.union(thisnum, last);
            if (row - 1 >= 0 && isOpen(row - 1, col))
                w.union(thisnum, thisnum - thisN);
            if (row + 1 < thisN && isOpen(row + 1, col))
                w.union(thisnum, thisnum + thisN);
            if (col - 1 >= 0 && isOpen(row, col - 1))
                w.union(thisnum, thisnum - 1);
            if (col + 1 < thisN && isOpen(row, col + 1))
                w.union(thisnum, thisnum + 1);
            count++;
        }
    } // open the site (row, col) if it is not open already
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= thisN || col < 0 || col >= thisN) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return grid[row][col] == 1;
    } // is the site (row, col) open?
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= thisN || col < 0 || col >= thisN) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (first == -1)
            return false;
        else
            return w.connected(first, row * thisN + col);
    } // is the site (row, col) full?
    public int numberOfOpenSites() {
        return count;
    }         // number of open sites
    public boolean percolates() {
        if (first == -1 || last == -2)
            return false;
        return w.connected(first, last);
    }             // does the system percolate?
    public static void main(String[] args) {

    }  // use for unit testing (not required)
}
