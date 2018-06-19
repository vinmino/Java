import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Scanner;

public class House extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Structure building = new Structure();

        BorderPane pane = new BorderPane();
        pane.setCenter(building);

        Scene scene = new Scene(pane, 500, 500);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("UP House");
        stage.setAlwaysOnTop(true);
    }

    static void main(String[] args) {
        Scanner key = new Scanner(System.in);
        System.out.print("Select 4 different colors for the balloons:\n" +
                "\t\tRED = 1\n" +
                "\t\tORANGE = 2\n" +
                "\t\tYELLOW = 3\n" +
                "\t\tGREEN = 4\n" +
                "\t\tBLUE = 5\n" +
                "\t\tPURPLE = 6\n" +
                "Choice 1: ");
        int c1 = key.nextInt();
        System.out.print("Choice 2: ");
        int c2 = key.nextInt();
        System.out.print("Choice 3: ");
        int c3 = key.nextInt();
        System.out.print("Choice 4: ");
        int c4 = key.nextInt();

        launch(args);
    }
}

class Structure extends Pane {

    int c1, c2, c3, c4;

    public Structure() {
        Scanner key = new Scanner(System.in);
        System.out.print("Select 4 different colors for the balloons:\n" +
                "\t\tRED = 1\n" +
                "\t\tORANGE = 2\n" +
                "\t\tYELLOW = 3\n" +
                "\t\tGREEN = 4\n" +
                "\t\tBLUE = 5\n" +
                "\t\tPURPLE = 6\n" +
                "Choice 1: ");
        this.c1 = key.nextInt();
        System.out.print("Choice 2: ");
        this.c2 = key.nextInt();
        System.out.print("Choice 3: ");
        this.c3 = key.nextInt();
        System.out.print("Choice 4: ");
        this.c4 = key.nextInt();

        paintStuff();
    }

    void paintStuff() {
        //Pane data
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;

        //House Data
        double houseWidth = Math.min(getHeight(), getWidth()) * 0.5;
        double houseHeight = houseWidth * 7 / 9;
        double houseX = centerX - houseWidth / 2;
        double houseY = getHeight() - houseHeight;
        Rectangle mainHouse = new Rectangle(houseX, houseY, houseWidth, houseHeight);
        mainHouse.setFill(Color.SADDLEBROWN);

        //Door data
        double doorHeight = houseHeight / 3;
        double doorWidth = houseWidth / 8;
        double doorX = houseX + houseWidth / 2 - doorWidth / 2;
        double doorY = houseY + houseHeight - doorHeight;
        Rectangle door = new Rectangle(doorX, doorY, doorWidth, doorHeight);
        door.setFill(Color.BLACK);

        //Balloon data
        /*int numOfBalloons = 9;
        double circleRadii = 15;
        double stringLengths = 100;
        Circle[] balloons = new Circle[numOfBalloons];
        for (int i = 0; i < 4; i++) {
            Circle workingCircle = new Circle();
            workingCircle.setRadius(circleRadii);
            workingCircle.setCenterX(centerX + Math.cos((i + 1) * Math.PI / 4) + stringLengths );
            workingCircle.setCenterY(centerY + Math.sin((i + 1) * Math.PI / 6) + stringLengths);
            workingCircle.setFill(Color.TRANSPARENT);
            workingCircle.setStroke(Color.BLACK);

            balloons[i] = workingCircle;
        }*/
        Polygon roof = new Polygon();
        double topX = houseX + houseWidth / 2;
        double topY = houseY - houseWidth / 3;
        roof.getPoints().addAll(
                houseX, houseY,
                topX, topY,
                houseX + houseWidth, houseY
        );
        roof.setFill(Color.DARKBLUE);



        double stringLengths = Math.min(getWidth(), getHeight()) * 0.34;

        double circle1X = topX + stringLengths * Math.cos((Math.PI / 5) + (Math.PI * 0 / 5));
        double circle1Y = topY - stringLengths * Math.sin((Math.PI / 5) + (Math.PI * 0 / 5));
        Line line1 = new Line(circle1X, circle1Y, topX, topY);
        Circle circle1 = new Circle(circle1X, circle1Y,Math.min(getHeight(),getWidth()) * 0.06);
        circle1.setStroke(Color.BLACK);
        switch (c4) {
            case 1: circle1.setFill(Color.RED); break;
            case 2: circle1.setFill(Color.ORANGE); break;
            case 3: circle1.setFill(Color.YELLOW); break;
            case 4: circle1.setFill(Color.GREEN); break;
            case 5: circle1.setFill(Color.BLUE); break;
            case 6: circle1.setFill(Color.PURPLE); break;
        }

        double circle2X = topX + stringLengths * Math.cos((Math.PI / 5) + (Math.PI * 1 / 5));
        double circle2Y = topY - stringLengths * Math.sin((Math.PI / 5) + (Math.PI * 1 / 5));
        Line line2 = new Line(circle2X, circle2Y, topX, topY);
        Circle circle2 = new Circle(circle2X,circle2Y,Math.min(getHeight(),getWidth()) * 0.06);
        circle2.setStroke(Color.BLACK);
        switch (c3) {
            case 1: circle2.setFill(Color.RED); break;
            case 2: circle2.setFill(Color.ORANGE); break;
            case 3: circle2.setFill(Color.YELLOW); break;
            case 4: circle2.setFill(Color.GREEN); break;
            case 5: circle2.setFill(Color.BLUE); break;
            case 6: circle2.setFill(Color.PURPLE); break;
        }

        double circle3X = topX + stringLengths * Math.cos((Math.PI / 5) + (Math.PI * 2 / 5));
        double circle3Y = topY - stringLengths * Math.sin((Math.PI / 5) + (Math.PI * 2 / 5));
        Line line3 = new Line(circle3X, circle3Y, topX, topY);
        Circle circle3 = new Circle(circle3X,circle3Y,Math.min(getHeight(),getWidth()) * 0.06);
        circle3.setStroke(Color.BLACK);
        switch (c2) {
            case 1: circle3.setFill(Color.RED); break;
            case 2: circle3.setFill(Color.ORANGE); break;
            case 3: circle3.setFill(Color.YELLOW); break;
            case 4: circle3.setFill(Color.GREEN); break;
            case 5: circle3.setFill(Color.BLUE); break;
            case 6: circle3.setFill(Color.PURPLE); break;
        }

        double circle4X = topX + stringLengths * Math.cos((Math.PI / 5) + (Math.PI * 3 / 5));
        double circle4Y = topY - stringLengths * Math.sin((Math.PI / 5) + (Math.PI * 3 / 5));
        Line line4 = new Line(circle4X, circle4Y, topX, topY);
        Circle circle4 = new Circle(circle4X,circle4Y,Math.min(getHeight(),getWidth()) * 0.06);
        circle4.setStroke(Color.BLACK);
        switch (c1) {
            case 1: circle4.setFill(Color.RED); break;
            case 2: circle4.setFill(Color.ORANGE); break;
            case 3: circle4.setFill(Color.YELLOW); break;
            case 4: circle4.setFill(Color.GREEN); break;
            case 5: circle4.setFill(Color.BLUE); break;
            case 6: circle4.setFill(Color.PURPLE); break;
        }





        getChildren().clear();
        getChildren().addAll(mainHouse, roof, door);
        getChildren().addAll(line1, line2, line3, line4, circle1, circle2, circle3, circle4);

    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paintStuff();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paintStuff();
    }

}
