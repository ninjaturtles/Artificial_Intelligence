import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

/**
 * Minimum Conflict Algorithm For N-queens puzzle
 * 
 * @author Akanksha Malik - Johnny Khalil 
 * 			Marko Mihailovic - Jose Romero
 * @version 1.0
 * @since December 1, 2017
 */
public class MinConflicts {
	private static ArrayList<Integer> conflictedQueens;
	private static int[] conflictedQueens2 = new int[Board.N];
	public static int[] current;

	/**
	 * Minimum Conflict Algorithm
	 * At each stage, a number of queens are chosen for reassignment in their columns.
	 * The algorithm moves the queens to the min-conflicts squares, breaking ties randomly
	 * 
	 * @param board - an initial complete assignment for csp
	 * @param max_steps - the number of steps allowed before giving up
	 * @return true if a solution is found, false otherwise
	 */
	public static boolean minConflics(Board board, int max_steps) {
		conflictedQueens = new ArrayList<Integer> ();

		// starting arrangement
		current = board.getGrid(); 

		for (int i = 0; i < max_steps; i++) {
//			System.out.println("step: " + i);

			// check if solved; if there are't any conflicts, problem is solved
			if (isSolution()) {
				board.setGrid(current);
				System.out.println("Number of steps: "+ i);
				System.out.println("Solution found");
				return true;
			}

			// move three conflicted queens at the same time

			// randomly chosen conflicted queens from 
			int randomCoflictedQueenCol = chooseRandomConflictedQueen();
			int randomCoflictedQueenCol2 = chooseRandomConflictedQueen();
			int randomCoflictedQueenCol3 = chooseRandomConflictedQueen();

			// the value row for queen that minimizes CONFLICTS
			int newRow = minNumberOfConflictsHeuristic(randomCoflictedQueenCol);
			int newRow2 = minNumberOfConflictsHeuristic(randomCoflictedQueenCol2);
			int newRow3 = minNumberOfConflictsHeuristic(randomCoflictedQueenCol3);

			// move queen to new row
			updateAssignment(randomCoflictedQueenCol, newRow);
			updateAssignment(randomCoflictedQueenCol2, newRow2);
			updateAssignment(randomCoflictedQueenCol3, newRow3);
		}
		
		System.out.println("No solution found");
		return false;
	}

	/**
	 * Moves a queen to its new row that minimizes CONFLICTS 
	 * 
	 * @param randomCoflictedQueenCol - the column of the queen to be moved
	 * @param newRow - the new row the queen is to be moved to
	 */
	private static void updateAssignment(int randomCoflictedQueenCol, int newRow) {
		// in the 1D integer array, columns are indices, rows are values
		current[randomCoflictedQueenCol] = newRow;
	}

	/**
	 * Checks if the current assignment is a solution,
	 * Updates the arraylist of conflicted queens
	 * 
	 * @return true if a solution, false otherwise. 
	 * 
	 */
	public static boolean isSolution() {
		// assignment is a solution if conflictedQueens is empty
		findConflicts();
		return (conflictedQueens.isEmpty());
	}

	
	/**
	 * Finds conflicts in grid, only store column number in conflictedQueens
	 */
	public static void findConflicts() {

		// wipe out old conflicts
		conflictedQueens.clear();
		
		/* Marko explain this ?? */
		conflictedQueens2 = new int[Board.N];
		
		//no horizontal conflicts
		//check vertical and diagonal conflicts
		for(int i = 0; i<current.length; i++) {
			int count = 0;
			
			/* Marko explain this ?? */
			if (conflictedQueens2[i]!=1) {
				for (int j = 0; j < current.length; j++) {
					
					// skip current row
					if(i != j) {
						// check diagonal conflicts
						if((Math.abs(i-j)) == (Math.abs(current[i]-current[j]))) {
							count++;
							conflictedQueens2[i] = 1;
							conflictedQueens2[j] = 1;
							// stop searching as soon as the first conflict is found
							break;
						}
						
						// check horizontal conflicts
						if(current[i] == current[j]) { 
							count++;
							conflictedQueens2[i] = 1;
							conflictedQueens2[j] = 1;
							// stop searching as soon as the first conflict is found
							break;
						}
					}
				}
			}
			else {
				count++;
			}
			
			// only append conflicted queens
			if(count != 0) {
				conflictedQueens.add(i);
			}
		}
	}

	/**
	 * Returns the position of the minimum conflicted queen
	 * @param queenCol - column of the randomly chosen queen
	 */
	private static int minNumberOfConflictsHeuristic(int queenCol) {
		// store each row and its number of conflicts in hashmap
		// key is row, value is number of conflicts
		HashMap<Integer, Integer> queenConflicts = new HashMap<Integer, Integer> ();

		// count all conflicts for each row
		for (int i = 0; i<current.length; i++) {
			int count = 0;
			
			for (int j = 0; j < current.length; j++) {
				
				// check diagonal conflicts
				if ((Math.abs(current[j]-i)) == (Math.abs(j-queenCol))) { 
					count++;
				}
				
				// check horizontal conflicts
				if (current[j] == i) {
					count++;	
				}
			}
			queenConflicts.put(i, count);
		}

		// find minimum number of conflict in queenConflicts
		Entry<Integer,Integer> min = null;
		for(Entry<Integer,Integer> entry : queenConflicts.entrySet()) {
			if (current[queenCol] == entry.getKey()) {
				// current row, dont add it
			} else {
				if(min == null || min.getValue() > entry.getValue()) {
					min=entry;
				}
			}
		}

		// find all entries that share the same min number of conflicts
		ArrayList<Integer> minConflictingQueens = new ArrayList<Integer>();
		for(Entry<Integer,Integer> entry : queenConflicts.entrySet()) {
			if (entry.getValue() == min.getValue()) {
				minConflictingQueens.add(entry.getKey());
			}
		}

		// pick new row at random
		Random rand = new Random();
		int random = rand.nextInt(minConflictingQueens.size()) + 0;

		return minConflictingQueens.get(random);
	}

	/**
	 * Chooses a random conflicted queen from conflicted queens arraylist
	 * 
	 * @return column of randomly chosen queen
	 */
	private static int chooseRandomConflictedQueen() {
		Random rand = new Random();
		int random = rand.nextInt(conflictedQueens.size()) + 0;
		return conflictedQueens.get(random);
	}

	/**
	 * Prints columns of conflicted queens 
	 */
	public static void printConflicts() {
		System.out.println("Printing Conflicting Queens");
		for (int i = 0; i < conflictedQueens.size(); i++ ) {
			if (i % 10 == 0) {
				System.out.print("Queen: " + conflictedQueens.get(i) + ", ");
			} else {
				System.out.println("Queen: " + conflictedQueens.get(i) + ", ");
			}
		}
	}

	/**
	 * Prints given assignment in grid format
	 * 
	 * @param assignment - assignment to be printed, 1D integer array
	 */
	public static void printAssignment(int[] assignment) {
		String queen = " Q ";
		String nonQueen = " - ";

		for (int row = 0; row < assignment.length; row++) {
			for (int col = 0; col < assignment.length; col++) {				
				System.out.print(assignment[row] == col ? queen : nonQueen);
			}
			System.out.println();
		}
	}

} // end of MinConflics
