package expandingCircles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class window extends JFrame {

    public window() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(800,800);
        setContentPane(new da());
        setLocationRelativeTo(null);
    }

}

class da extends JPanel implements ActionListener {

    public da() {

    }

    @Override
    protected void paintComponent(Graphics g) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}