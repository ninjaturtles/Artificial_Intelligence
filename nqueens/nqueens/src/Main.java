
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = Integer.parseInt(args[0]);
		int max_steps = Integer.parseInt(args[1]);
		boolean flag = Integer.parseInt(args[2]) == 1 ? true : false;
		
		if (n >= 4) {
			Board board = new Board(n,flag);
			long start = System.currentTimeMillis();
			
//			board.findConflicts(board.getGrid());
//			board.printConflicts();
//			board.printQueensPositioins();
//			board.printAssignment(board.getGrid());
			MinConflicts.minConflics(board, max_steps);
//			board.printSolution();
			long stop = System.currentTimeMillis();
			System.out.println("Found in " + ((double)(stop-start))/1000 + "s.\n");
//			board.printConflicts();
//			board.printQueensPositioins();
		}else {
			System.out.println("No Solution can be found");
		}
	}

} // end of Main