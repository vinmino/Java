package Circle2D;

public class Circle2D {
    /*Data*/
    double x;
    double y;
    double radius;
    /*Constructor*/
    public Circle2D() {
        this.x = 0;
        this.y = 0;
        this.radius = 1;
    }
    public Circle2D(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    /*Methods*/
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public double getRadius() {
        return this.radius;
    }
    public double getArea() {
        return (Math.PI * Math.pow(this.radius, 2));
    }
    public double getParaimeter() {
        return (Math.PI * 2 * this.radius);
    }



}
