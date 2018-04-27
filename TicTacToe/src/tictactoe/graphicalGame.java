package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class graphicalGame extends JFrame {

    public graphicalGame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tic-Tac-Toe");
        setLocationRelativeTo(null);
        TicTacToe game = new TicTacToe();

    }

    public static void main(String[] args) {
        new graphicalGame();
    }
}

class ButtonPanel extends JPanel {

    JButton TL = new JButton("");
    JButton TM = new JButton("");
    JButton TR = new JButton("");
    JButton ML = new JButton("");
    JButton MM = new JButton("");
    JButton MR = new JButton("");
    JButton BL = new JButton("");
    JButton BM = new JButton("");
    JButton BR = new JButton("");



    public ButtonPanel() {
        //TicTacToe game = new TicTacToe();
        setLayout(new GridLayout(3,3));
        this.TL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TL.setText("X");
            }
        });

        this.TM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TM.setText("X");
            }
        });

        this.TR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TR.setText("X");
            }
        });

        this.ML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ML.setText("X");
            }
        });

        this.MM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MM.setText("X");
            }
        });

        this.MR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MR.setText("X");
            }
        });

        this.BL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BL.setText("X");
            }
        });

        this.BM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BM.setText("X");
            }
        });

        this.BR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BR.setText("X");
            }
        });

    }


}
