package tictactoe;

import java.util.Scanner;

public class TicTacToe {

    int board[][];
    Scanner key;

    public TicTacToe() {
        this.board = new int[3][3];
    }

    private int[] compGen() {
        int row = -1;
        int col = -1;
        int pos[] = new int[2];
        do {
            row = (int) (Math.random() * 4);
            col = (int) (Math.random() * 4);
        } while(board[row][col] == 0);
        pos[0] = row;
        pos[1] = col;
        return pos;
    }

    private int[] userGen() {
        System.out.println("Input a space for your guess: ");
        int row = key.nextInt();
        int col = key.nextInt();
        int pos[] = {row, col};
        return pos;
    }

    public void play() {

    }

    public void showBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
        game.showBoard();
    }

}
