import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;


public class Board {
	private int N;
	private int[] grid;
	private HashMap<Integer, Integer> conflictedQueens;

	public Board(int n, boolean greedy) {
		this.N = n;
		this.grid = new int[n];
		this.conflictedQueens = new HashMap<Integer, Integer> ();
		this.initial_assignment(greedy);
	}

	private void initial_assignment(Boolean greedy) {
		// the initial assignment of the queens on the board
		int  random;
		Random rand = new Random();
		Set<Integer> alreadyGeneratedNums = new HashSet<Integer>();

		for (int i = 0; i < grid.length; i++) {
			random = rand.nextInt(N);
			grid[i] = i;
			if(greedy) {
				//greedy and random initial assignment
				while(!alreadyGeneratedNums.add(random)) {
					random = rand.nextInt(N);	
				}
			}

			grid[i] = random;
		}
		if(!greedy) {	
			//random initial assignment
			for (int i = 0, n = grid.length; i < n; i++) {
				int j = rand.nextInt(n);
				int rowToSwap = grid[i];
				grid[i] = grid[j];
				grid[j] = rowToSwap;
			}
		}
	}

	// find conflicts in grid, only store column number in conflictedQueens
	public void findConflicts(int[] assignment) {

		conflictedQueens.clear(); // wipe out old conflicts
		//no horizontal conflicts
		//check vertical and diagonal conflicts
		for(int i =0; i<assignment.length; i++) {
			int count = 0;
			for (int j = 0; j < assignment.length; j++) {
				if(i != j) {
					// diagonal
					if((Math.abs(i-j)) == (Math.abs(assignment[i]-assignment[j]))) 
						count++;
					// vertical
					if(assignment[i] == assignment[j]) 
						count++;		
				}
			}
			if(count != 0) {
				conflictedQueens.put(i,count);
			}
		}
	}

	public void updateQueen(int randomCoflictedQueen, int newRowPosition) {
		grid[randomCoflictedQueen] = newRowPosition;
	}

	public boolean isSolution(int[] current) {
		// assignment is a solution if conflictedVariables is empty
		findConflicts(current);
		return (conflictedQueens.isEmpty());
	}

	/**
	 * Print the board in a nice format
	 */
	public void printSolution() {
		Variable queen = new Variable(true);
		Variable nonQueen = new Variable(false);

		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid.length; col++) {
				System.out.print(grid[row] == col ? queen : nonQueen);
			}
			System.out.println();
		}
	}

	public void printAssignment(int[] assignment) {
		Variable queen = new Variable(true);
		Variable nonQueen = new Variable(false);

		for (int row = 0; row < assignment.length; row++) {
			for (int col = 0; col < assignment.length; col++) {				
				System.out.print(assignment[row] == col ? queen : nonQueen);
			}
			System.out.println();
		}
	}

	public void printConflicts() {
		System.out.println("Printing Conflicting Queens");

		Iterator<Entry<Integer, Integer>> it = conflictedQueens.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Integer> pair = (Map.Entry<Integer, Integer>)it.next();
			System.out.println("Queen: " + pair.getKey() + " = " + pair.getValue() + " Conflicts");
			it.remove(); // avoids a ConcurrentModificationException
		}
	}

	public void printQueensPositioins() {
		System.out.println("Printing Queens Positions");
		for (int i = 0; i < grid.length; i++) {
			System.out.print(grid[i] + " ");
		}
		System.out.println();
	}

	public int[] getGrid() {
		return grid;
	}

	public int getN() {
		return N;
	}

	public HashMap<Integer,Integer> getConflictedQueens() {
		return conflictedQueens;
	}

	public void setGrid(int[] solution) {
		this.grid = solution;
	}
} // end of Board
