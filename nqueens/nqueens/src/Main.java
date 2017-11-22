
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = Integer.parseInt(args[0]);
		int max_steps = Integer.parseInt(args[1]);
		
		Board board = new Board(n);
        long start = System.currentTimeMillis();
        MinConflicts.minConflics(board, max_steps);
        board.printSolution();
        long stop = System.currentTimeMillis();
        System.out.println("Found in " + ((double)(stop-start))/1000 + "s.\n");
        board.printConflicts();
        board.printQueensPositioins();
	}

} // end of Main