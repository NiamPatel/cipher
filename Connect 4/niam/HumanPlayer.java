package niam;

import java.util.Scanner;

public class HumanPlayer {
    private char playerSymbol; 

    public HumanPlayer(char symbol) {
        this.playerSymbol = symbol;
    }

    public int chooseColumn() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Player " + playerSymbol + ", choose a column (0-6): ");
        int column = scanner.nextInt();
        return column;
    }

    public void makeMove(Board gameBoard) {
        int column;
        do {
            column = chooseColumn();
        } while (!isValidColumn(column, gameBoard)); 

        gameBoard.updateBoard(column, playerSymbol);
    }

    private boolean isValidColumn(int column, Board gameBoard) {
        char[][] board = gameBoard.getArray();
        return board[0][column] == ' '; 
    }
}
