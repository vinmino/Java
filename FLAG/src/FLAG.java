import javafx.animation.Animation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class FLAG extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        double WIDTH = 500;
        double HEIGHT = 500;

        Pane pane = new Pane();
        Image flag = new Image("http://www.all-flags-world.com/country-flag/Italy/flag-italy-XL.jpg");
        ImageView imageView = new ImageView(flag);
        imageView.setFitHeight(flag.getHeight() / 2);
        imageView.setFitWidth(flag.getWidth() / 2);

        Media song = new Media("https://youtu.be/04ckV9QueXc");
        MediaPlayer player = new MediaPlayer(song);
        player.setCycleCount(Animation.INDEFINITE);
        player.play();

        pane.getChildren().add(imageView);

        Scene scene = new Scene(pane, imageView.getFitWidth(), imageView.getFitHeight());

        stage.setScene(scene);
        stage.setTitle("Italy");
        stage.show();
    }

    static void main(String[] args) {
        launch(args);
    }

}
