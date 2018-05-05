package expandingCircles;

public class ExpandingCircles {

    /*Data*/
    double radius;
    double x;
    double y;

    int numOfPoints;
    double[][] points;

    /*Constructor*/
    public ExpandingCircles(double radius) {
        this.radius = radius;
        x = 100 + Math.random() * (500 - 100 + 1);
        y = 100 + Math.random() * (500 - 100 + 1);

        numOfPoints = (int)(Math.PI * 2 * radius);
        points = new double[numOfPoints][2];

        double angle;
        for (int i = 0; i < points.length; i++) {
            angle = 2 * Math.PI * i / numOfPoints;
            x = radius * Math.cos(Math.PI + angle);
            y = radius * Math.sin(Math.PI + angle);
            points[i][0] = x;
            points[i][1] = y;
        }

    }

    static void main(String[] args) {

    }

}
