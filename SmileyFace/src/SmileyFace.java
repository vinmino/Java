import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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



        getChildren().clear();
        getChildren().addAll(circle);

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
