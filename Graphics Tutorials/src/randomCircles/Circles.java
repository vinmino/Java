package randomCircles;

import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Circles extends JFrame {


    public static void main(String[] args) {
        Circles circle = new Circles();


    }

    public Circles() {
        this.setSize(500,500);
        this.setTitle("Drawed Stuff");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new DrawStuff(), BorderLayout.CENTER);
        this.setVisible(true);

    }

    private class DrawStuff extends JComponent {

        public void paint(Graphics g) {

            Graphics2D g2 = (Graphics2D)g;

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Shape drawLine = new Line2D.Float(20, 90, 55, 250);

            Shape drawArc1 = new Arc2D.Double(5, 150, 100, 100, 45, 180, Arc2D.OPEN);
            Shape drawArc2 = new Arc2D.Double(5, 200, 100, 100, 45, 45, Arc2D.CHORD);
            Shape drawArc3 = new Arc2D.Double(5, 250, 100, 100, 45, 45, Arc2D.PIE);

            g2.setPaint(Color.RED);
            g2.setColor(Color.GREEN);

            g2.draw(drawLine);
            g2.fill(drawArc1);
            g2.draw(drawArc2);
            g2.draw(drawArc3);

        }

    }

}
