import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class MinConflicts {
	private static HashMap<Integer, Integer> conflictedQueens;
	public static int[] current;
	
	public static boolean minConflics(Board board, int max_steps) {
		conflictedQueens = new HashMap<Integer, Integer> ();

		// starting arrangement
		current = board.getGrid(); 
//		board.printAssignment(current);
		
		System.out.println();
		for (int i = 0; i < max_steps; i++) {
			System.out.println("step: " + i);
			// check if solved; if there are't any conflicts, problem is solved
			if (isSolution()) {
				board.setGrid(current);
				System.out.println("Number of steps: "+ i);
				System.out.println("Solution found");
				return true;
			}
			//choose one of the conflicting queens at random
			int randomCoflictedQueenRow = chooseRandomConflictedQueen();
//			board.printAssignment(current);
//			System.out.println();
//			printConflicts();
//			System.out.println("row: " + randomCoflictedQueenRow);
//			System.out.println("col: " + current[randomCoflictedQueenRow]);
			
			// find the queen that the conflicting queen can swap with
			// that will give minimum number of overall conflicts
			int newCol = minNumberOfConflictsHeuristic(randomCoflictedQueenRow);
			// update queen position
//			System.out.println("new col: " + newCol);
			
			updateAssignment(randomCoflictedQueenRow, newCol);
		}
		System.out.println("No solution found");
		return false;
	}
	
	private static void updateAssignment(int randomCoflictedQueenRow, int newCol) {
		current[randomCoflictedQueenRow] = newCol;
	}

	public static boolean isSolution() {
		// assignment is a solution if conflictedVariables is empty
		findConflicts();
		return (conflictedQueens.isEmpty());
	}
	
	// find conflicts in grid, only store column number in conflictedQueens
	public static void findConflicts() {

		conflictedQueens.clear(); // wipe out old conflicts
		//no horizontal conflicts
		//check vertical and diagonal conflicts
		for(int i = 0; i<current.length; i++) {
			int count = 0;
			for (int j = 0; j < current.length; j++) {
				if(i != j) {
					// diagonal
					if((Math.abs(i-j)) == (Math.abs(current[i]-current[j]))) {
						count++;
					}
					// vertical
					if(current[i] == current[j]) { 
						count++;	
					}
				}
			}
			if(count != 0) {
				conflictedQueens.put(i,count);
			}
		}
	}
	
	/**
	 * Returns the position of the min conflict queen
	 */
	private static int minNumberOfConflictsHeuristic(int queenRow) {
		HashMap<Integer, Integer> queenConflicts = new HashMap<Integer, Integer> ();
		
		for (int i = 0; i<current.length; i++) {
			int count = 0;
			for (int j = 0; j < current.length; j++) {
				// diagonal
				if((Math.abs(current[j]-i)) == (Math.abs(j-queenRow))) { 
					count++;
				}
				// vertical
				if(current[j] == i) {
					count++;	
				}
			}
			queenConflicts.put(i, count);
		}


		Entry<Integer,Integer> min = null;
		for(Entry<Integer,Integer> entry : queenConflicts.entrySet()) {
			if (current[queenRow] == entry.getKey()) {
				
//				System.out.println("same position");
			} else {
//				System.out.println(" col " + entry.getKey() + ": " + entry.getValue() + " conflicts");
				if(min == null || min.getValue() > entry.getValue()) {
					min=entry;
//					System.out.println("new min: " + min.getKey());
				}
			}
		}
		
		// find all entries that have same min number of conflicts
		HashMap<Integer, Integer> hashm = new HashMap<Integer, Integer> ();
		for(Entry<Integer,Integer> entry : queenConflicts.entrySet()) {
			if (entry.getValue() == min.getValue()) {
				hashm.put(entry.getKey(), entry.getValue());
			}
		}
		
		
		// pick entry at random
		
		Random rand = new Random();
		int random = rand.nextInt(hashm.size()) + 0;
		int count = 0;
		int randomQueen = 0;

		Iterator<Entry<Integer, Integer>> it = hashm.entrySet().iterator();
		while (it.hasNext() && count <= random ) {
			Map.Entry<Integer, Integer> pair = (Map.Entry<Integer, Integer>) it.next();
			count++;
			randomQueen = (int) pair.getKey(); 
			it.remove();
		}
		return randomQueen;
	}

	private static int chooseRandomConflictedQueen() {
		Random rand = new Random();
		int random = rand.nextInt(conflictedQueens.size()) + 0;
		int count = 0;
		int randomQueen = 0;

		Iterator<Entry<Integer, Integer>> it = conflictedQueens.entrySet().iterator();
		while (it.hasNext() && count <= random ) {
			Map.Entry<Integer, Integer> pair = (Map.Entry<Integer, Integer>) it.next();
			count++;
			randomQueen = (int) pair.getKey(); 
			it.remove();
		}
		return randomQueen;
	}

	public static void printConflicts() {
		System.out.println("Printing Conflicting Queens");

		Iterator<Entry<Integer, Integer>> it = conflictedQueens.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Integer> pair = (Map.Entry<Integer, Integer>)it.next();
			System.out.println("Queen: " + pair.getKey() + " = " + pair.getValue() + " Conflicts");
			it.remove(); // avoids a ConcurrentModificationException
		}
	}
	
	public static void printAssignment(int[] assignment) {
		Variable queen = new Variable(true);
		Variable nonQueen = new Variable(false);

		for (int row = 0; row < assignment.length; row++) {
			for (int col = 0; col < assignment.length; col++) {				
				System.out.print(assignment[row] == col ? queen : nonQueen);
			}
			System.out.println();
		}
	}

} // end of MinConflics
