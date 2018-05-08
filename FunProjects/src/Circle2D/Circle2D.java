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
    public double getPerimeter() {
        return (Math.PI * 2 * this.radius);
    }
    public boolean contains(double x, double y) { //checks that the distance between the point and the center are less than or equal to the radius
        return (Math.sqrt(Math.pow(x - this.getX(), 2) + Math.pow(y - this.getY(), 2)) <= this.getRadius());
    }
    public boolean contains(Circle2D otherCircle) { //checks that the distance between the centers of the circles are less than or equal to the radius of this circle
        return (Math.sqrt(Math.pow(otherCircle.getX() - this.getX(), 2) + Math.pow(otherCircle.getY() - this.getY(), 2)) + otherCircle.getRadius() <= this.getRadius());
    }
    public boolean overlaps(Circle2D otherCircle) { //checks that the distance between the centers of the circles are less than or equal to the sum of their radii
        return (Math.sqrt(Math.pow(otherCircle.getX() - this.getX(), 2) + Math.pow(otherCircle.getY() - this.getY(), 2)) <= this.getRadius() + otherCircle.getRadius());
    }

    /*Main Method*/
    public static void main(String[] args) {
        Circle2D c1 = new Circle2D(2, 2, 5.5);
        System.out.println("A circle c1 was created with its center at (" + c1.getX() + ", " + c1.getY() + ") and a radius of " + c1.getRadius());
        System.out.println("The area of c1 is " + c1.getArea());
        System.out.println("The perimeter of c1 is " + c1.getPerimeter());
        System.out.println("The test point is contained in c1: " + ((c1.contains(3, 3)) ? "true":"false"));
        System.out.println("The test circle is contained in c1: " + ((c1.contains(new Circle2D(4, 5, 10.5))) ? "true":"false"));
        System.out.println("The test circle overlaps with c1: " + ((c1.overlaps(new Circle2D(3, 5, 2.3))) ? "true":"false"));
    }
}
