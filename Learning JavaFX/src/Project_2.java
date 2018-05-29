import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Project_2 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Circle circle = new Circle();
        circle.setCenterX(150);
        circle.setCenterY(150);
        circle.setRadius(50);
        circle.setStroke(Color.YELLOW);
        circle.setFill(null);
        Circle circle1 = new Circle();
        circle1.setCenterX(200);
        circle1.setCenterY(150);
        circle1.setRadius(50);
        circle1.setStroke(Color.GREEN);
        circle1.setFill(null);
        Pane pane = new Pane();
        pane.getChildren().add(circle);
        pane.getChildren().add(circle1);
        Scene scene = new Scene(pane, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Vincent Lomino");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
