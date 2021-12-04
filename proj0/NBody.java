public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] p = new Planet[num];
        for(int i = 0; i < num; i++) {
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double m = in.readDouble();
            String s = in.readString();
            Planet pi = new Planet(xp, yp, xv, yv, m, s);
            p[i] = pi;
        }
        return p;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double r = readRadius(filename);
        Planet[] p = readPlanets(filename);

        //We are now begin to draw!
        StdDraw.setScale(-r, r);
        StdDraw.clear();
        String imageToDraw = "./images/starfield.jpg";
        StdDraw.picture(0, 0, imageToDraw);

        for(Planet pi:p){
            pi.draw();
        }
        StdDraw.enableDoubleBuffering();
        double t = 0;
        while(t < T){
            double[] xforces = new double[p.length];
            double[] yforces = new double[p.length];
            for(int i = 0; i < p.length; i++){
                xforces[i] = p[i].calcNetForceExertedByX(p);
                yforces[i] = p[i].calcNetForceExertedByY(p);
            }
            StdDraw.picture(0, 0, imageToDraw);
            for(int i = 0; i < p.length; i++){
                p[i].update(dt, xforces[i], yforces[i]);
                p[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }

        StdOut.printf("%d\n", p.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < p.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p[i].xxPos, p[i].yyPos, p[i].xxVel,
                    p[i].yyVel, p[i].mass, p[i].imgFileName);
        }
    }
}
