package expandingCircles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class window extends JFrame {

    public window() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 800);
        setVisible(true);
        setTitle("Expanding Circles");
        setLocationRelativeTo(null);
    }

    static void main(String[] args) {

        window window = new window();

        for (int i = 0; i < 15; i++) {

        }

    }



}

/*
class DrawArea extends JPanel implements ActionListener {

    @Override
    */
/*protected void paintComponent(Graphics g) {
        for
    }*//*


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}*/
