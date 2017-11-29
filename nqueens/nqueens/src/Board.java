import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class Board {
	private int N;
	private int[] grid;

	public Board(int n, boolean greedy) {
		this.N = n;
		this.grid = new int[n];
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

	public void setGrid(int[] solution) {
		this.grid = solution;
	}
} // end of Board
