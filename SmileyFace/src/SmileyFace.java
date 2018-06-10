import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

public class SmileyFace extends Pane {

    public SmileyFace() {
        paint();
    }

    public void paint() {
        double faceRadius =
                Math.min(getWidth(), getHeight()) * 0.8 * 0.5;
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;

        Circle circle = new Circle(centerX, centerY, faceRadius);
        circle.setFill(Color.YELLOW);
        circle.setStroke(Color.BLACK);

        Ellipse eyeLeft = new Ellipse(centerX - (faceRadius * 0.3), centerY - (faceRadius * 0.45), faceRadius * 0.05, faceRadius * 0.1);
        eyeLeft.setFill(Color.BLACK);
        Ellipse eyeRight = new Ellipse(centerX + (faceRadius * 0.3), centerY - (faceRadius * 0.45), faceRadius * 0.05, faceRadius * 0.1);
        eyeLeft.setFill(Color.BLACK);

        Arc smile = new Arc();
        smile.setCenterX(centerX);
        smile.setCenterY(centerY);
        smile.setStartAngle(190);
        smile.setLength(160);
        smile.setType(ArcType.OPEN);
        smile.setFill(Color.TRANSPARENT);
        smile.setStrokeWidth(2);
        smile.setStroke(Color.BLACK);
        smile.setRadiusX(faceRadius * 0.6);
        smile.setRadiusY(faceRadius * 0.4);

        Transition radiusTranstion = new Transition()
        {
            {
                setCycleDuration(Duration.millis(1000));
                setAutoReverse(true);
                setInterpolator(Interpolator.LINEAR);
                setCycleCount(Animation.INDEFINITE);
            }

            @Override
            protected void interpolate(double frac)
            {
                smile.setRadiusY((faceRadius * 0.4) - (faceRadius * 0.4 * frac));
                smile.translateYProperty().setValue(faceRadius * 0.4 * frac);
            }
        };

        radiusTranstion.play();

        getChildren().clear();
        getChildren().addAll(circle, eyeLeft, eyeRight, smile);

    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paint();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paint();
    }
}
