package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double mean;
    private double stddev;
    private double confidenceLow;
    private double confidenceHigh;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        double[] a = new double[T];
        if (N <= 0 || T <= 0) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            for (int j = 0; j <= N*N; j++) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                p.open(row, col);
                if (p.percolates()) {
                    a[i] = 1.0 * p.numberOfOpenSites() / (N*N);
                    break;
                }
            }
        }
        mean = StdStats.mean(a);
        stddev = StdStats.stddev(a);
        confidenceLow = mean - 1.96*stddev/Math.sqrt(T);
        confidenceHigh = mean + 1.96*stddev/Math.sqrt(T);
    }   // perform T independent experiments on an N-by-N grid
    public double mean() {
        return mean;
    }                                           // sample mean of percolation threshold
    public double stddev() {
        return stddev;
    }                                       // sample standard deviation of percolation threshold
    public double confidenceLow() {
        return confidenceLow;
    }                                 // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        return confidenceHigh;
    }                                 // high endpoint of 95% confidence interval
}
