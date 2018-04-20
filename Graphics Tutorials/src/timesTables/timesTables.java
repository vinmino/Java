package timesTables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

public class timesTables extends JComponent implements MouseMotionListener, ActionListener {

    static final double WIDTH = 800;
    static final double HEIGHT = 800;

    double mouseX = 0;
    double mouseY = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Times Tables");
        timesTables table = new timesTables();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize((int)WIDTH, (int)HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.add(table);
        frame.setVisible(true);
        frame.addMouseMotionListener(table);

        //Timer t = new Timer(10, table);
        //t.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.translate(WIDTH/2, HEIGHT/2);
        Shape circle = new Ellipse2D.Double(0, 100, 2, 2);
        Color col = Color.getHSBColor(0, 100, 50);
        for (int i = 0; i < 100; i++) {
            col = new Color(Color.HSBtoRGB(100, 100, 0));
            g2.setColor(col);
            g2.fill(circle);
            g2.rotate( 2 * Math.PI / 100);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
//Graphics2D g2 = (Graphics2D) g;
//g.translate(WIDTH / 2, HEIGHT / 2);
//g2.setColor(Color.BLACK);
//g.drawOval((WIDTH / 2) - r, (HEIGHT / 2) - r, r, r);
//drawCircle(g, mouseX, mouseY, 25);
//Shape circle = new Ellipse2D.Double((mouseX) - 25, (mouseY) - 25, 50,50);
//g.drawOval(mouseX, mouseY, 50, 50);
//g2.draw(circle);