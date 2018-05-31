package Day_2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Playground extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Vincent Lomino");
        Label food = new Label();
        food.setText("Taco");
        Circle circle = new Circle();
        circle.setCenterY(150);
        circle.setCenterX(150);
        circle.setRadius(100);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);
        food.setCenterShape(true);
        food.translateXProperty().setValue(150);
        food.translateYProperty().setValue(150);
        Pane pane = new Pane();
        pane.getChildren().add(food);
        pane.getChildren().add(circle);
        Scene scene = new Scene(pane, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
