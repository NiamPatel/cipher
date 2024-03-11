package niam;

import connectFour.Grid;

public class Board{

    char[][] boardarray = new char[6][7]; 
    char player = 'x';
    public Board() {
        for (int row = 0; row < boardarray.length; row++) {
            for (int col = 0; col < boardarray[row].length; col++) {
                boardarray[row][col] = ' '; 
            }
        }
    }
    public char[][] getArray(){
        return boardarray;
    }
    public void printBoard() {
        for (int row = 0; row < boardarray.length; row++) {
            for (int col = 0; col < boardarray[row].length; col++) {
                if (col != 0)
                    System.out.print("|"); // Changed to print a vertical bar for better visual separation
                System.out.print(boardarray[row][col]);
            }
            System.out.print("|"); // Changed to print a vertical bar for better visual separation

            System.out.println();
        }
    }
    public void updateBoard(int column, char currentPlayer){
        for (int row = boardarray.length - 1; row >= 0; row--) {
            if (boardarray[row][column] == ' ') {
                boardarray[row][column] = currentPlayer;
                currentPlayer = (currentPlayer == 'x') ? 'o' : 'x';
                return; 
            }
        }
    }

    public static boolean checkC(char[][] arr, char curPlayer) {
        for (int col = 0; col < arr[0].length; col++) {
            int count = 0;
            for (int row = 0; row < arr.length; row++) {
                if (arr[row][col] == curPlayer) {
                    count++;
                    if (count == 5) return true;
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }
    
    public boolean checkR(char[] arr, char curPlayer) {
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == curPlayer) {
                temp++;
                if (temp == 5) {
                    return true;
                }
            } else {
                temp = 0;
            }
        }
        return false;
    }
    
    public boolean checkD(char[][] arr, char curPlayer) {
    for (int i = 0; i <= arr.length - 5; i++) {
        for (int j = 0; j <= arr[0].length - 5; j++) {
            int count = 0;
            for (int k = 0; k < 5; k++) {
                if (arr[i + k][j + k] == curPlayer) {
                    count++;
                    if (count == 5) return true;
                } else {
                    break; 
                }
            }
        }
    }
    for (int i = arr.length - 1; i >= 4; i--) {
        for (int j = 0; j <= arr[0].length - 5; j++) {
            int count = 0;
            for (int k = 0; k < 5; k++) {
                if (arr[i - k][j + k] == curPlayer) {
                    count++;
                    if (count == 5) return true;
                } else {
                    break; 
                }
            }
        }
    }
    return false;
}


    
    public String checkForWin(Board gameBoard, char cur){
        char[][] arr = gameBoard.getArray();
        for(int i = 0; i<arr.length; i++){
            if (checkR(arr[i], cur)){
                return "w";
            }
        }
        
     return "no win";
    }}
   
    

