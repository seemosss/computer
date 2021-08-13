public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double x = p.xxPos - xxPos;
        double y = p.yyPos - yyPos;
        return Math.sqrt(x * x + y * y);
    }

    public double calcForceExertedBy(Planet p){
        double r = calcDistance(p);
        return G * mass * p.mass / (r * r);
    }

    public double calcForceExertedByX(Planet p){
        return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p){
        return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allp){
        double f = 0;
        for (Planet p : allp){
            if (this.equals(p))
                continue;
            f += calcForceExertedByX(p);
        }
        return f;
    }

    public double calcNetForceExertedByY(Planet[] allp){
        double f = 0;
        for (Planet p : allp){
            if (this.equals(p))
                continue;
            f += calcForceExertedByY(p);
        }
        return f;
    }

    public void update(double time, double fx, double fy){
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel += ax * time;
        yyVel += ay * time;
        xxPos += xxVel * time;
        yyPos += yyVel * time;
    }

    public void Draw(){
        String filename = "./images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, filename);
    }
}
