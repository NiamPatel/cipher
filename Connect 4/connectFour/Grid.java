package connectFour;

/**
 * Represents the grid of a Connect Four game.
 * Note: The first position in a column has row index 0, which means that when you display the
 * Grid you'll show the rows in order of decreasing index, which is the opposite of TextExcel.
 * For example, consider a game that starts with these four moves:
 * Player 1 moves in column 2, which drops to row 0
 * Player 2 moves in column 3, which drops to row 0
 * Player 1 moves in column 3, which drops to row 1
 * Player 2 moves in column 3, which drops to row 2
 * The grid would look like this:
 * 5 |   |   |   |   |   |   |   |
 * 4 |   |   |   |   |   |   |   |
 * 3 |   |   |   |   |   |   |   |
 * 2 |   |   |   | 2 |   |   |   |
 * 1 |   |   |   | 1 |   |   |   |
 * 0 |   |   | 1 | 2 |   |   |   |
 *    --- --- --- --- --- --- ---
 *     0   1   2   3   4   5   6 
 *   
 */
public interface Grid {
	// Note: interfaces can define constants, although it's not in the AP subset
	// For example, you can use Grid.ROWS rather than 6 in your code, just like class constants
	public static final int ROWS = 6;        // number of rows in a grid
	public static final int COLS = 7;        // number of columns in a grid
	public static final int PLAYEREMPTY = 0; // player number for empty cell
	public static final int PLAYER1 = 1;     // player number for first player
	public static final int PLAYER2 = 2;     // player number for second player
	
	/**
	 * Gets the player number (1 or 2) at the given row and column, or 0 if that cell is empty.
	 * 
	 * @param  row  the row index (0 through 5) 
	 * @param  col  the column index (0 through 6)
	 * @return      the player number (1 or 2) at the given row and column, or 0 if empty
	 */
	public int getPlayerAt(int row, int col);
	
	/**
	 * Checks whether the given column is full.
	 * @param  col  the column index (0 through 6)
	 * @return      true if the column is full, otherwise false
	 */
	public boolean isColumnFull(int col);

}
