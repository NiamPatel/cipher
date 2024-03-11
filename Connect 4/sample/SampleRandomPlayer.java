/* CS3 Project 2: Connect Four sample random player
 * Shows how to put a type into a package, implement the Player interface, and use the Grid interface
 */
package sample;       // put your own java files into a package/folder with your lower-case name
import connectFour.*; // this imports the connectFour types (Grid and Player)
import java.util.*;   // this imports the java.util types

public class SampleRandomPlayer implements Player {
	
	public int getMoveColumn(Grid g) {
		// makes a random valid move, by finding all non-full columns and then picking one at random.
		
		// Step 1: build a list of available (non-full) column indexes
		ArrayList<Integer> availableColumns = new ArrayList<Integer>();
		for (int column = 0; column < Grid.COLS; column++) {
			if (!g.isColumnFull(column)) {
				availableColumns.add(column);
			}
		}
		
		// Step 2: choose an index into the availableColumns list, at random
		int listIndex = (int)(Math.random() * availableColumns.size());
		
		// Step 3: Get the chosen element (column index) from the availableColumns list, and return it 
		int columnIndex = availableColumns.get(listIndex);
		return columnIndex;
	}

	public String getPlayerName() {
		return "Sample's Random Player"; // return something descriptive that includes your name
	}
}
