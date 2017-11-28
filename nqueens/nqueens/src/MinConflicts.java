import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class MinConflicts {

	public static boolean minConflics(Board board, int max_steps) {
		// starting arrangement
		int[] current = board.getGrid(); 

		for (int i = 0; i < max_steps; i++) {
			System.out.println(i);
			// check if solved; if there are't any conflicts, problem is solved
			if (board.isSolution(current)) {
				board.setGrid(current);
				return true;
			}
			//choose one of the conflicting queens at random
			int randomCoflictedQueen = chooseRandomConflictedQueen(board);

			// find the queen that the conflicting queen can swap with
			// that will give minimum number of overall conflicts
			int newRowPosition = minNumberOfConflictsHeuristic(randomCoflictedQueen, current);
			// update queen position
			//			board.updateQueen(randomCoflictedQueen, newRowPosition);
		}
		return false;
	}

	private static int chooseRandomConflictedQueen(Board board) {
		Random rand = new Random();
		HashMap<Integer,Integer> conflictedQueens = board.getConflictedQueens();
		int random = rand.nextInt(conflictedQueens.size()) + 0;
		int count = 0;
		int randomQueen = 0;
		
		Iterator<Entry<Integer, Integer>> it = conflictedQueens.entrySet().iterator();
		while (it.hasNext() && count <= random ) {
			Map.Entry<Integer, Integer> pair = (Map.Entry<Integer, Integer>)it.next();
			count++;
			randomQueen = (int) pair.getKey(); 
		}
		
		return randomQueen;
	}

	/**
	 * Returns the number of queens that conflict with passed queen.
	 */
	private static int minNumberOfConflictsHeuristic(int queen, int[] assignment) {
		return 0;
	}

} // end of MinConflics
