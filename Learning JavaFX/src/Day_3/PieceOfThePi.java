package Day_3;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PieceOfThePi extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        double WIDTH = 300;
        double HEIGHT = 300;
        double RADIUS = 100;
        int NumOfPieces = 8;
        Pane pane = new Pane();
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
            piePieces[i].setFill(Color.hsb(360 * i / NumOfPieces, 1.0, 1.0));
            piePieces[i].setStroke(piePieces[i].getFill());
            piePieces[i].setStrokeWidth(1);
            pane.getChildren().add(piePieces[i]);
        }

        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        stage.setTitle("Vincent Lomino");
        stage.setScene(scene);
        stage.show();

        ParallelTransition[] transitions = new ParallelTransition[piePieces.length];
        TranslateTransition transition;
        FillTransition fillTransition;
        ParallelTransition parallelTransition;
        for (int i = 0; i < transitions.length; i++) {
            transition = new TranslateTransition(Duration.millis(500));
            transition.setNode(piePieces[i]);
            fillTransition = new FillTransition(Duration.millis(500));
            fillTransition.setShape(piePieces[i]);
            fillTransition.setToValue(Color.hsb(360 * i / NumOfPieces, 1.0, 1.0, 0.1));
            fillTransition.setFromValue(Color.hsb(360 * i / NumOfPieces, 1.0, 1.0));
            fillTransition.setAutoReverse(true);
            fillTransition.setCycleCount(2);
            transition.setFromX(0);
            transition.setFromY(0);
            transition.setByX(50 * Math.sin((1 - Math.pow(NumOfPieces, -1)) * Math.PI + (2 * Math.PI * (i - 1) / NumOfPieces)));
            transition.setByY(50 * Math.cos((1 - Math.pow(NumOfPieces, -1)) * Math.PI + (2 * Math.PI * (i - 1) / NumOfPieces)));
            transition.setCycleCount(2);
            transition.setAutoReverse(true);
            parallelTransition = new ParallelTransition();
            parallelTransition.getChildren().addAll(transition, fillTransition);
            transitions[i] = parallelTransition;
        }

        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(transitions);
        sequentialTransition.setCycleCount(Animation.INDEFINITE);
        sequentialTransition.setAutoReverse(false);
        sequentialTransition.play();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
