import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class FLAG extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Pane pane = new Pane();
        Image flag = new Image("http://www.all-flags-world.com/country-flag/Italy/flag-italy-XL.jpg");
        ImageView imageView = new ImageView(flag);
        imageView.setFitHeight(flag.getHeight() / 2);
        imageView.setFitWidth(flag.getWidth() / 2);

        //AudioClip music = new AudioClip("https://ia800605.us.archive.org/8/items/NeverGonnaGiveYouUp/jocofullinterview41.mp3");
        AudioClip music = new AudioClip("http://wapking.download/files/sfd137/68034/I%20Want%20It%20That%20Way(bossmobi).mp3");
        music.setCycleCount(Animation.INDEFINITE);
        music.play();

        Transition volumeSeizure = new Transition() {
            @Override
            protected void interpolate(double frac) {
                music.volumeProperty().setValue(1.0 * frac);
            }
        };

        volumeSeizure.setAutoReverse(true);
        volumeSeizure.setCycleCount(Animation.INDEFINITE);
        volumeSeizure.play();

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
