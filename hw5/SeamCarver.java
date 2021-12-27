import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private Picture picture;
    private int width;
    private int height;
    private double[][] energy;
    private double[][] invenergy;

    private int caldelta(int rgb1, int rgb2) {
        int r1 = (rgb1 >> 16) & 0xFF;
        int g1 = (rgb1 >> 8) & 0xFF;
        int b1 = (rgb1) & 0xFF;
        int r2 = (rgb2 >> 16) & 0xFF;
        int g2 = (rgb2 >> 8) & 0xFF;
        int b2 = (rgb2) & 0xFF;
        return (r1 - r2) * (r1 - r2) + (g1 - g2) * (g1 - g2) + (b1 - b2) * (b1 - b2);
    }

    private int calenergy(int i, int j) {
        int rgbx1 = 0, rgbx2 = 0, rgby1 = 0, rgby2 = 0;
        if (i == 0) rgbx1 = picture.getRGB(width - 1, j);
        else rgbx1 = picture.getRGB(i - 1, j);
        if (i == width - 1) rgbx2 = picture.getRGB(0, j);
        else rgbx2 = picture.getRGB(i + 1, j);
        if (j == 0) rgby1 = picture.getRGB(i, height - 1);
        else rgby1 = picture.getRGB(i, j - 1);
        if (j == height - 1) rgby2 = picture.getRGB(i, 0);
        else rgby2 = picture.getRGB(i, j + 1);
        return caldelta(rgbx1, rgbx2) + caldelta(rgby1, rgby2);
    }

    public SeamCarver(Picture picture) {
        this.picture = picture;
        setPicture();
    }

    public void setPicture() {
        this.width = picture.width();
        this.height = picture.height();
        energy = new double[width][height];
        invenergy = new double[height][width];
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                energy[i][j] = calenergy(i, j);
                invenergy[j][i] = energy[i][j];
            }
        }
    }

    private int[] helpmethod(double[][] e) {
        int m = e.length, n = e[0].length;
        double[][] mincost = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) mincost[i][j] = e[i][j];
                else {
                    double min = mincost[i - 1][j];
                    if (j != 0) min = Math.min(min, mincost[i - 1][j - 1]);
                    if (j != n - 1) min = Math.min(min, mincost[i - 1][j + 1]);
                    mincost[i][j] = e[i][j] + min;
                }
            }
        }
        int[] result = new int[m];
        int minj = 0;
        double mincostj = mincost[m - 1][0];
        for (int j = 1; j < n; j++) {
            if (mincost[m - 1][j] < mincostj) {
                minj = j;
                mincostj = mincost[m - 1][j];
            }
        }
        result[m - 1] = minj;
        for (int i = m - 2; i >= 0; i--) {
            int lastj = minj;
            if (lastj != 0 && mincost[i][lastj - 1] < mincost[i][lastj]) minj = lastj - 1;
            if (lastj != n - 1 && mincost[i][lastj + 1] < mincost[i][minj]) minj = lastj + 1;
            result[i] = minj;
        }
        return result;
    }

    public Picture picture(){
        return this.picture;
    }                       // current picture
    public int width(){
        return this.width;
    }                         // width of current picture
    public int height() {
        return this.height;
    }                        // height of current picture
    public double energy(int x, int y) {
        return this.energy[x][y];
    }           // energy of pixel at column x and row y
    public int[] findHorizontalSeam() {
        return helpmethod(this.energy);
    }           // sequence of indices for horizontal seam
    public int[] findVerticalSeam() {
        return helpmethod(this.invenergy);
    }             // sequence of indices for vertical seam
    public void removeHorizontalSeam(int[] seam) {
        picture = SeamRemover.removeHorizontalSeam(picture, findHorizontalSeam());
        setPicture();
    }  // remove horizontal seam from picture
    public void removeVerticalSeam(int[] seam) {
        picture = SeamRemover.removeVerticalSeam(picture, findVerticalSeam());
        setPicture();
    }    // remove vertical seam from picture
}
