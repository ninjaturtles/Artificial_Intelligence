
public class Variable {
	public int row;
	public int col;
	public boolean queen;
	
//	public Variable(int myRow, int myCol, boolean isQueen) {
//		this.row = myRow;
//		this.col = myCol;
//		this.queen = isQueen;
//	}
	
	public Variable(boolean isQueen) {
		this.queen = isQueen;
	}
	
	@Override
	public String toString() {
		if (queen) {
			return " Q ";
		} else {
			return " - ";
		}
	}
	
} // end of Queen
