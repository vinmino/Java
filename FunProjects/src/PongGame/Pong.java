package PongGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pong extends JComponent implements ActionListener, MouseMotionListener {

    int ballX = 400;
    int ballXV = 1;
    int ballY = 150;
    int ballYV = -1;
    int paddleX = 0;

    public static void main(String[] args) {
        JFrame window = new JFrame("Times Tables");
        Pong game = new Pong();
        window.add(game);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.addMouseMotionListener(game);

        Timer t = new Timer(10, game);
        t.start();



    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800,600);
    }

    @Override
    public void paintComponent(Graphics g) {
        //draw sky
        g.setColor(new Color(178, 223, 224));
        g.fillRect(0,0, 800, 600);

        //draws paddle
        g.setColor(new Color(110,65,13));
        g.fillRect(paddleX,510, 150,15);

        //draws ball
        g.setColor(new Color(155, 93, 169));
        g.fillOval(ballX,ballY,30,30);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (ballX >= 785 || ballX <= -15) {
            ballXV *= -1;
        }
        if (ballY <= -15 || (ballY >= 510-15  && ballY <= 510+15&& (ballX >= paddleX && ballX <= paddleX + 150))) {
            ballYV *= -1;
        }
        ballX += 5 * ballXV;
        ballY += 5 * ballYV;
        if (ballY >= 600) {
            System.exit(0);
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        paddleX = e.getX() - 75;
        repaint();
    }
}
