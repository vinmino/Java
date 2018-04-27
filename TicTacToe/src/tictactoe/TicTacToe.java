package tictactoe;

import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    int board[][];
    Scanner key;
    Random rand = new Random();

    public TicTacToe() {
        this.board = new int[3][3];
        this.key = new Scanner(System.in);
    }

    private int[] compGen() {
        int row = -1;
        int col = -1;
        int pos[] = new int[2];
        do {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        } while(board[row][col] != 0 || board[row][col] == 1 || board[row][col] == 2);
        pos[0] = row;
        pos[1] = col;
        return pos;
    }

    private boolean winner() {
        boolean won = false;
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != 0) {
                won = true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != 0) {
                won = true;
            }
        }
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[2][2] != 0) || (board[0][2] == board[2][0] && board[1][1] == board[0][2] && board[1][1] != 0)) {
            won = true;
        }
        return won;
    }

    private int[] userGen() {
        System.out.println("Input a space for your guess: ");
        int row = key.nextInt();
        int col = key.nextInt();
        int pos[] = {row, col};
        return pos;
    }

    private void update(int[] pos, int player) {
        if (player == 1) {
            board[pos[0]][pos[1]] = 1;
        } else if (player == 2) {
            board[pos[0]][pos[1]] = 2;
        } else {
            System.out.println("You fucked up vinnie");
        }
    }

    public void play() {
        int counter = 0;
        while (!this.winner()) {
            if (counter % 2 == 0) {
                this.showBoard();
                this.update(this.userGen(), 1);
                counter++;
            } else {
                this.update(this.compGen(), 2);
                counter++;
            }
        }
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
