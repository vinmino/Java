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
        ButtonPanel panel = new ButtonPanel();

        setContentPane(panel);
        setSize(300, 300);
        setVisible(true);

    }

    public static void main(String[] args) {
        new graphicalGame();
    }
}

class ButtonPanel extends JPanel {

    TicTacToe game;

    JButton TL = new JButton("   ");
    JButton TM = new JButton("   ");
    JButton TR = new JButton("   ");
    JButton ML = new JButton("   ");
    JButton MM = new JButton("   ");
    JButton MR = new JButton("   ");
    JButton BL = new JButton("   ");
    JButton BM = new JButton("   ");
    JButton BR = new JButton("   ");



    public ButtonPanel() {
        this.game = new TicTacToe(this);

        setLayout(new GridLayout(3,3));


        this.TL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TL.setText(" X ");
                int[] pos = {0,0};
                play(pos);

            }
        });

        this.TM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TM.setText(" X ");
                int[] pos = {0,1};
                play(pos);

            }
        });

        this.TR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TR.setText(" X ");
                int[] pos = {0,2};
                play(pos);

            }
        });

        this.ML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ML.setText(" X ");
                int[] pos = {1,0};
                play(pos);

            }
        });

        this.MM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MM.setText(" X ");
                int[] pos = {1,1};
                play(pos);

            }
        });

        this.MR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MR.setText(" X ");
                int[] pos = {1,2};
                play(pos);

            }
        });

        this.BL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BL.setText(" X ");
                int[] pos = {2,0};
                play(pos);

            }
        });

        this.BM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BM.setText(" X ");
                int[] pos = {2,1};
                play(pos);

            }
        });

        this.BR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BR.setText(" X ");
                int[] pos = {2,2};
                play(pos);

            }
        });


        add(TL);
        add(TM);
        add(TR);
        add(ML);
        add(MM);
        add(MR);
        add(BL);
        add(BM);
        add(BR);
    }

    private void play(int[] pos) {
        game.gPlay(pos,this);
    }


}
