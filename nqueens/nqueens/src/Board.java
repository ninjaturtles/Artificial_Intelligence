import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Board {
	private int N;
	private int[] grid;
	private ArrayList<Integer> conflictedQueens;

	public Board(int n) {
		this.N = n;
		this.grid = new int[n];
		this.conflictedQueens = new ArrayList<Integer>();
		this.initial_assignment();
	}

	private void initial_assignment() {
		// the initial assignment of the queens on the board
		int  random;
		Random rand = new Random();
		Set<Integer> alreadyGeneratedNums = new HashSet<Integer>();

		for (int i = 0; i < grid.length; i++) {
			random = rand.nextInt(N);
			grid[i] = i;
			while(!alreadyGeneratedNums.add(random)) {
				random = rand.nextInt(N);	
			}

			grid[i] = random;
		}
		//		for (int i = 0, n = grid.length; i < n; i++) {
		//            int j = rand.nextInt(n);
		//            int rowToSwap = grid[i];
		//            grid[i] = grid[j];
		//            grid[j] = rowToSwap;
		//        }
	}

	// find conflicts in grid, only store column number in conflictedQueens
	public void findConflicts(int[] assignment) {
		int q= 0;
		// wipe out old conflicts
		conflictedQueens.clear();
		//no vertical conflicts
		// check horizontal conflicts
//		for (int i = 0; i < assignment.length; i++) {
//			for (int j = 1; j < assignment.length; j++) {
//				if (assignment[i] == assignment[j]) {
//					conflictedQueens.add(i);
//				}
//			}
//		}


		for(int i =0; i<assignment.length; i++) {
			int count = 0;
			for (int j = 0; j < assignment.length; j++) {
				if(i!=j) {
					if((Math.abs(i-j)) == (Math.abs(assignment[i]-assignment[j]))) {
						count++;
//						conflictedQueens.add(i);
//						System.out.println(assignment[i] +" has conflict with " + assignment[j]);
					}
				}
			}
			System.out.println("count: "+q +" "+ count);
			q++;
		}


	}

	public void updateQueen(Variable var, int value) {

	}

	public boolean isSolution(int[] current) {
		// assignment is a solution if conflictedVariables is empty
		findConflicts(current);
		return conflictedQueens.isEmpty(); 
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
		for (int i = 0; i < conflictedQueens.size(); i++) {
			System.out.print(conflictedQueens.get(i) + " ");
		}
		System.out.println();
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

	public ArrayList<Integer> getConflictedQueens() {
		return conflictedQueens;
	}

	public void setGrid(int[] solution) {
		this.grid = solution;
	}
} // end of Board
