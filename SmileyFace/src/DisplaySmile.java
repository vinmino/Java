import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DisplaySmile extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        SmileyFace face = new SmileyFace();

        Scene scene = new Scene(face, 500, 500);
        stage.setScene(scene);
        stage.setTitle("Vincent Lomino");
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
