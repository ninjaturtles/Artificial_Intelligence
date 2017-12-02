import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * NxN Chess Board used to represent N-queens puzzle
 * 
 * @author Akanksha Malik - Johnny Khalil 
 * 			Marko Mihailovic - Jose Romero
 * @version 1.0
 * @since December 1, 2017
 */
public class Board {
	public static int N;
	private int[] grid;

	/**
	 * Class constructor - initializes am NxN board for N-queen puzzle 
	 * 
	 * @param n - number of queens on the board, also size of the board
	 * @param greedy - flag; true for random and greedy initial assignment
	 * 						false for random initial assignment
	 */
	public Board(int n, boolean greedy) {
		Board.N = n;
		this.grid = new int[n];
		this.initial_assignment(greedy);
	}

	/**
	 * Creates the initial state, might be a random or greedy random 
	 * configuration of n queens in n columns
	 * 
	 * @param greedy - flag; true for random and greedy initial assignment
	 * 				   false for random initial assignment
	 */
	private void initial_assignment(Boolean greedy) {
		int  random;
		Random rand = new Random();
		// to be used for greedy assignment, no two queens can be on the same row
		Set<Integer> alreadyGeneratedNums = new HashSet<Integer>();

		for (int i = 0; i < grid.length; i++) {
			random = rand.nextInt(N);
			grid[i] = i;
			
			//greedy and random initial assignment
			if(greedy) {
				while(!alreadyGeneratedNums.add(random)) {
					random = rand.nextInt(N);	
				}
			}

			grid[i] = random;
		}
		
		//random initial assignment
		if(!greedy) {	
			for (int i = 0, n = grid.length; i < n; i++) {
				int j = rand.nextInt(n);
				int rowToSwap = grid[i];
				grid[i] = grid[j];
				grid[j] = rowToSwap;
			}
		}
	}

	/**
	 * Prints the solution in grid format
	 */
	public void printSolution() {
		String queen = " Q ";
		String nonQueen = " - ";

		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid.length; col++) {
				System.out.print(grid[row] == col ? queen : nonQueen);
			}
			System.out.println();
		}
	}

	/**
	 * Prints the row column positions of all queens 
	 */
	public void printQueensPositions() {
		System.out.println("Printing Queens Positions");
		for (int i = 0; i < grid.length; i++) {
			System.out.print(grid[i] + " ");
		}
		System.out.println();
	}

	/**
	 * Getter: grid
	 * @return grid, the grid of the puzzle
	 */
	public int[] getGrid() {
		return grid;
	}

	/**
	 * Getter: N
	 * @return N, the size of the puzzle
	 */
	public int getN() {
		return N;
	}

	/**
	 * Sets the solution of the puzzle  
	 * @param solution , solution found by Min-Conflict Algorithm
	 */
	public void setGrid(int[] solution) {
		this.grid = solution;
	}
	
} // end of Board
