import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import javax.swing.*;

public class VennDiagram extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        double HEIGHT = 500;
        double WIDTH = 500;
        double xCenter = WIDTH / 2;
        double yCenter = HEIGHT / 2;
        int numOfCircles = 50;
        double tempX;
        double tempY;
        double circleRadii = 100;
        Circle[] circleArray = new Circle[numOfCircles];
        double angle = 2 * Math.PI / circleArray.length;

        Pane pane = new Pane();
        for (int i = 0; i < circleArray.length; i++) {
            tempX = xCenter + (circleRadii / 2) * Math.cos(angle * i);
            tempY = yCenter + (circleRadii / 2) * Math.sin(angle * i);


            circleArray[i] = new Circle(tempX, tempY, circleRadii);
            circleArray[i].setFill(null);
            circleArray[i].setStroke(Color.BLACK);
            circleArray[i].setStrokeWidth(2);
            pane.getChildren().add(circleArray[i]);
        }
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        primaryStage.setTitle("Vincent Lomino");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
