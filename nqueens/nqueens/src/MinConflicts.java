import java.util.ArrayList;
import java.util.Random;

public class MinConflicts {

	
	public static boolean minConflics(Board board, int max_steps) {
		int[] current = board.getGrid(); 
		
		for (int i = 0; i < max_steps; i++) {
			if (board.isSolution(current)) {
				board.setGrid(current);
				return true;
			}
			//a randomly chosen conflicted variable from board.conflictedVariables
//			Variable var = chooseRandomConflictedVariable(board);
//			int newRowPosition = minNumberOfConflictsHeuristic(var, current);
//			board.updateQueen(var, newRowPosition);
		}
		return false;
	}

	public static int chooseRandomConflictedVariable(Board board) {
		Random rand = new Random();
		ArrayList<Integer> conflictedQueens = board.getConflictedQueens();
		int random = rand.nextInt(conflictedQueens.size());
		return conflictedQueens.get(random);
	}
	
	 /**
     * Returns the number of queens that conflict with passed queen.
     */
	public static int minNumberOfConflictsHeuristic(Variable queen, Variable[][] assignment) {
		return 0;
	}
	
	
	
} // end of MinConflics
