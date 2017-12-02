/**
 * Main Class
 * 
 * @author Akanksha Malik - Johnny Khalil 
 * 			Marko Mihailovic - Jose Romero
 * @version 1.0
 * @since December 1, 2017
 */
public class Main {

	/**
	 * Main function
	 * 
	 * @param args :
	 * Command line arguments as follows: N max_steps initial_assignment_flag
	 * Where: 
	 * 		N: the size of the NxN puzzle (int greater than 3)
	 * 		max_steps: number of steps before the algorithm gives up (int depends on N) 
	 * 		initial_assignment_flag:  0 for random initial assignment, 
	 * 								  1 for greedy and random initial assignment.
	 */
	public static void main(String[] args) {
		// parse command line arguments		
		int n = Integer.parseInt(args[0]);
		int max_steps = Integer.parseInt(args[1]);
		boolean flag = Integer.parseInt(args[2]) == 1 ? true : false;
		
		// a solution only exists for all natural number except n=2 and n=3
		if (n >= 4) {
			// initialize the board
			Board board = new Board(n,flag);
			
			// start timing
			long start = System.currentTimeMillis();
			
			// run Minimum Conflict Algorithm
			MinConflicts.minConflics(board, max_steps);
			
			// print solution, left commented out intentionally, 
			// printing large N would be a nightmare
			//board.printSolution();
			
			// stop timing
			long stop = System.currentTimeMillis();
			
			System.out.println("Found in " + ((double)(stop-start))/1000 + "s.");
			System.out.println(board.getN() + " Queens");
		}else {
			System.out.println("No Solution can be found");
		}
	}

} // end of Main