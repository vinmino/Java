package Example;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class DisplayClock extends Application {
    public Label updateLable(ClockPane clock) {
        String timeString = new DecimalFormat("00").format(clock.getHour()) + ":" + new DecimalFormat("00").format(clock.getMinute())
                + ":" + new DecimalFormat("00").format(clock.getSecond());
        return new Label(timeString);
    }

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a clock and a label
        ClockPane clock = new ClockPane();
        Label lblCurrentTime = updateLable(clock);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                clock.setCurrentTime();
                lblCurrentTime.setText(updateLable(clock).getText());
            }
        };
        timer.start();

        // Place clock and label in border pane
        BorderPane pane = new BorderPane();
        pane.setCenter(clock);
        pane.setBottom(lblCurrentTime);
        BorderPane.setAlignment(lblCurrentTime, Pos.TOP_CENTER);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 250, 250);
        primaryStage.setTitle("DisplayClock"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        primaryStage.setAlwaysOnTop(true);

    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}