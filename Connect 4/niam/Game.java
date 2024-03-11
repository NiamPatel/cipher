package niam;

public class Game {
    private Board gameBoard;
    private HumanPlayer player1;
    private HumanPlayer player2;
    
    public Game() {
        gameBoard = new Board(); 
        player1 = new HumanPlayer('x'); 
        player2 = new HumanPlayer('o'); 
    }
    
    public void play() {
        boolean isPlayer1Turn = true; 
        boolean gameEnded = false;
        
        while (!gameEnded) {
            System.out.println("Current board layout:");
            gameBoard.printBoard();
            
            if (isPlayer1Turn) {
                System.out.println("Player 1's turn:");
                player1.makeMove(gameBoard);
            } else {
                System.out.println("Player 2's turn:");
                player2.makeMove(gameBoard);
            }
            
            String result = gameBoard.checkForWin(gameBoard, isPlayer1Turn ? 'x' : 'o');
            if (!result.equals("no win")) {
                System.out.println(result + "win for");
                System.out.println(isPlayer1Turn ? 'x' : 'o');
                gameEnded = true;
            } else if (isBoardFull()) { 
                System.out.println("The game is a draw!");
                gameEnded = true;
            }
            
            isPlayer1Turn = !isPlayer1Turn; 
        }
        
        System.out.println("Final board layout:");
        gameBoard.printBoard();
    }
    
    private boolean isBoardFull() {
        char[][] board = gameBoard.getArray();
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == ' ') {
                return false; 
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        Game newGame = new Game(); 
        newGame.play();
    }
}
