package Day_1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Project_1 extends Application {
    @Override
    public void start(Stage pF) throws Exception {
        pF.setTitle("Vincent Lomino");
        Button button = new Button();
        button.setText("NOPE");
        button.setPrefSize(200, 200);
        Scene scene = new Scene(button);
        pF.setScene(scene);
        pF.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
