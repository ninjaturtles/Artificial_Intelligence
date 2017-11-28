import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class MinConflicts {

	public static boolean minConflics(Board board, int max_steps) {
		// starting arrangement
		int[] current = board.getGrid(); 
		board.printAssignment(current);
		System.out.println();
		for (int i = 0; i < max_steps; i++) {

			// check if solved; if there are't any conflicts, problem is solved
			if (board.isSolution(current)) {
				board.setGrid(current);
				System.out.println("Number of steps: "+ i);
				System.out.println("Solution found");
				
				return true;
			}
			//choose one of the conflicting queens at random
			int randomCoflictedQueen = chooseRandomConflictedQueen(board);
//			board.printAssignment(current);
//			System.out.println("randomCoflictedQueen: " + randomCoflictedQueen);
			// find the queen that the conflicting queen can swap with
			// that will give minimum number of overall conflicts
			int newRowPosition = minNumberOfConflictsHeuristic(randomCoflictedQueen, current);
			// update queen position
//			System.out.println("newRowPosition: " + newRowPosition);

			board.updateQueen(randomCoflictedQueen, newRowPosition);
		}
		System.out.println("No solution found");
		return false;
	}

	/**
	 * Returns the position of the min conflict queen
	 */
	private static int minNumberOfConflictsHeuristic(int queen, int[] assignment) {
		HashMap<Integer, Integer> queenConflict = new HashMap<Integer, Integer> ();
		
		for (int i = 0; i<assignment.length; i++) {
			int count = 0;
			for (int j = 0; j < assignment.length; j++) {
				if(i != assignment[queen]) {
					// diagonal
					if((Math.abs(assignment[j]-i)) == (Math.abs(j-queen))) { 
						count++;
					}
					// vertical
					if(assignment[j] == i); {
						count++;	
					}
				}
			}
			if(count != 0) {
				queenConflict.put(i, count);
			}
	
		}

		Entry<Integer,Integer> min = null;
		for(Entry<Integer,Integer> entry : queenConflict.entrySet()) {
			if (assignment[queen] == entry.getKey()) {
				System.out.println("same position");
			} else {
				if(min == null || min.getValue() > entry.getValue()) {
					min=entry;
				}
			}
		}
		return min.getKey();
	}

	private static int chooseRandomConflictedQueen(Board board) {
		Random rand = new Random();
		HashMap<Integer,Integer> conflictedQueens = board.getConflictedQueens();
		int random = rand.nextInt(conflictedQueens.size()) + 0;
		int count = 0;
		int randomQueen = 0;

		Iterator<Entry<Integer, Integer>> it = conflictedQueens.entrySet().iterator();
		while (it.hasNext() && count <= random ) {
			Map.Entry<Integer, Integer> pair = (Map.Entry<Integer, Integer>) it.next();
			count++;
			randomQueen = (int) pair.getKey(); 
		}
		return randomQueen;
	}



} // end of MinConflics
