package Day_3;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

//https://examples.javacodegeeks.com/desktop-java/javafx/javafx-animation-example/

public class PieceOfThePi extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        double WIDTH = 300;
        double HEIGHT = 300;
        double RADIUS = 100;
        int NumOfPieces = 8;
        Pane pane = new Pane();
        Circle testingCircle = new Circle(WIDTH / 2, HEIGHT / 2, RADIUS, Color.TRANSPARENT);
        testingCircle.setStrokeWidth(1);
        testingCircle.setStroke(Color.TRANSPARENT);
        Arc[] piePieces = new Arc[NumOfPieces];
        for (int i = 0; i < piePieces.length; i++) {
            piePieces[i] = new Arc();
            piePieces[i].setCenterX(WIDTH / 2);
            piePieces[i].setCenterY(HEIGHT / 2);
            piePieces[i].setRadiusX(RADIUS);
            piePieces[i].setRadiusY(RADIUS);
            piePieces[i].setType(ArcType.ROUND);
            piePieces[i].setLength(360 / NumOfPieces);
            piePieces[i].setStartAngle(360 * i / NumOfPieces);
            piePieces[i].setStroke(Color.BLACK);
            piePieces[i].setStrokeWidth(1);
            piePieces[i].setFill(Color.hsb(360 * i / NumOfPieces, 1.0, 1.0));
            pane.getChildren().add(piePieces[i]);
        }
        /*Arc piece = new Arc();
        piece.setCenterX((WIDTH / 2) + 20 * Math.cos(2 * Math.PI / (2 * NumOfPieces)));
        piece.setCenterY((HEIGHT / 2) + 20 * Math.sin(2 * Math.PI / (2 * NumOfPieces)));
        piece.setRadiusY(RADIUS);
        piece.setRadiusX(RADIUS);
        piece.setType(ArcType.ROUND);
        piece.setLength(360 / NumOfPieces);
        piece.setStartAngle(360 - (360 / NumOfPieces));
        piece.setStrokeWidth(1);
        piece.setStroke(Color.BLACK);
        piece.setFill(Color.hsb(360 * (piePieces.length - 1) / NumOfPieces, 1.0, 1.0));
        pane.getChildren().add(piece);*/

        pane.getChildren().add(testingCircle);
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        stage.setTitle("Vincent Lomino");
        stage.setScene(scene);
        stage.show();

        TranslateTransition transition = new TranslateTransition(Duration.millis(3000));

        for (int i = 0; i < piePieces.length; i++) {
            transition.setNode(piePieces[i]);
            transition.setFromX(WIDTH / 2);
            transition.setFromY(HEIGHT / 2);
            transition.setToX((WIDTH / 2) + Math.cos(i * 2 * Math.PI / (NumOfPieces * 2) ));
            transition.setToY((HEIGHT / 2) + Math.sin(i * 2 * Math.PI / (NumOfPieces * 2) ));
            //transition.setByX(50);
            //transition.setByY(25);
            transition.setCycleCount(1);

            transition.play();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
