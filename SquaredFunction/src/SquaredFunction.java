import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

public class SquaredFunction extends Application {

    public static double f(double x) {
        return -0.0125 * Math.pow(x, 2);
        //return 100 * Math.sin(x/25);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        double WIDTH = 500;
        double HEIGHT = 500;
        double xCenter = WIDTH / 2;
        double yCenter = HEIGHT / 2;
        Pane root = new Pane();
        Line xAxis = new Line(xCenter, 0, xCenter, HEIGHT);
        Label xLabel = new Label("X");
        xLabel.translateYProperty().setValue(yCenter + 2);
        xLabel.translateXProperty().setValue(2);
        Line yAxis = new Line(0, yCenter, WIDTH, yCenter);
        Label yLabel = new Label("Y");
        yLabel.translateXProperty().setValue(xCenter + 2);
        double[] points = new double[(int)WIDTH];
        for (double d = 0; d < WIDTH; d += 2) {
            points[(int)d] = d;
            points[(int)d + 1] = f(d - xCenter) + yCenter;
        }

        Polyline graph = new Polyline(points);


        root.getChildren().addAll(xAxis, yAxis, graph, xLabel, yLabel);


        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Vincent Lomino");
        primaryStage.show();
        primaryStage.setAlwaysOnTop(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
