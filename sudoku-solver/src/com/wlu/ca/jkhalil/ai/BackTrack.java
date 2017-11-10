package com.wlu.ca.jkhalil.ai;

import java.util.ArrayList;
import java.util.Collections;

/**
 * file name: BackTrack.java
 * Back Track algorithm implementation
 * 
 * @author Akanksha Malik - Johnny Khalil
 * @since Nov 6th, 2017
 */
public class BackTrack {
	public Sudoku sudoku;
	public Variable[][] solution;

	public BackTrack(Sudoku sudoku) {
		this.sudoku = sudoku;
	}

	/**
	 * The BackTrack Algorithm. 
	 * A simple backtracking algorithm for constraint satisfaction problems. 
	 * The algorithm is modeled on the recursive depth-first search
	 * Chooses values for one variable at a time and backtracks when a variable 
	 * has no legal values left to assign.
	 * 
	 * @return True if a solution to the puzzle is found, false on failure
	 */
	public boolean backtrackSearch() {
		// initial assignment copies the last state generated by AC3 algorithm
		Variable[][] assignment = sudoku.grid;

		boolean status = backtrack(assignment);
		return status;
	}

	/**
	 * Auxiliary function used by backtrackSearch()
	 * 
	 * @param assignment - A state would be a partial assignment
	 * @return true on success, false on failure
	 */
	public boolean backtrack(Variable[][] assignment) {

		if (sudoku.isComplete(assignment)) {
			solution = assignment;
			return true;
		}

		Variable var = selectUnassignedVariable(assignment);
		//orderDomainValues(var);

		for (Integer value: var.domain) {
			var.setValue(value);
			if (sudoku.isConsistent(var, assignment)) {
				assignment[var.row][var.column].value = var.value;
				boolean result = backtrack(assignment);
				if (result) {
					return result;
				}
			}
			assignment[var.row][var.column].value = 0;
		}
		return false;
	}

	/**
	 * Choose the next unassigned variable in order.
	 *  Ordered by the variable with the fewest “legal” values.
	 * 
	 * @param assignment - A state would be a partial assignment
	 * @return Variable - most constrained variable
	 */
	public Variable selectUnassignedVariable(Variable[][] assignment) {
		ArrayList<Variable> available = retrieveAvailableVariables(assignment);

		// sort the array by minimum-remaining-values (MRV) heuristic
		mrvHeuristic(available);

		if (available.size() == 0) {
			return null;
		}

		return available.get(0);
	}

	/**
	 * Chooses the variable with the fewest “legal” values.
	 * picks a variable that is most likely to cause a failure soon,
	 * thereby pruning the search tree.
	 * 
	 * @param available - available variables
	 */
	public void mrvHeuristic(ArrayList<Variable> available) {
		// sort available by length of domains
		Collections.sort(available);
	}

	/**
	 * attempts to reduce the branching factor on future choices by selecting
	 *  the variable that is involved in the largest number of constraints 
	 *  on other unassigned variables.
	 *  
	 * @param var the variable with the fewest “legal” values.
	 */
	public void orderDomainValues(Variable var) {
		// broken, to be updated
		
//		ArrayList<Variable> neighbours = sudoku.getNeighbours(var);
//		
//		System.out.println("\n\nBEFORE variable domain: ");
//		var.printDomain();
//		System.out.println();
//		System.out.println("\nneighbours domains: ");
//		for (int i  = 0; i< neighbours.size(); i++) {
//			neighbours.get(i).printDomain();
//			System.out.println();
//		}
//		// <key, value> => key: domain value to be sorted
//		//			    => value: number of times value shows up in neighbours domains 
//		Map<Integer, Integer> domainValues = new HashMap<Integer, Integer>();
//
//		for (Integer varValue: var.domain) {
//			int count = 0;
//			for (Variable neighbour : neighbours) {
//				for (Integer neighbourValue : neighbour.domain) {
//					if (neighbour.domain.size() > 1 && (varValue != neighbourValue)) {
//						count++;
//					}
//				}
//			}
//			domainValues.put(count, varValue );
////			domainValues.put(varValue, count );
//		} 
//		
//		Map<Integer, Integer> orderedDomainValues = new TreeMap<Integer, Integer>(domainValues);
//
//		var.domain = new ArrayList<Integer>(orderedDomainValues.values());
//		
//		System.out.println("\nAFTER variable domain: ");
//		var.printDomain();
//		System.out.println();
	}

	/**
	 * Return a list of variables not found yet
	 * 
	 * @param assignment - an assignment
	 * @return array list of available variables, i.e not found yet
	 */
	public ArrayList<Variable> retrieveAvailableVariables(Variable[][] assignment) {
		ArrayList<Variable> available = new ArrayList<Variable>();

		for (int row = 0; row < Sudoku.ROWS; row++) {
			for (int col = 0; col < Sudoku.COLUMNS; col++) {
				if (sudoku.grid[row][col].value == 0 && assignment[row][col].value == 0) {
					available.add(sudoku.grid[row][col]);
				}
			}
		}
		return available;
	}


	/**
	 * Print the sudoku puzzle gird in a nice format
	 * 0 indicates an empty unit. 
	 */
	public void printSolution() {
		for (int row = 0; row < Sudoku.ROWS; row++) {
			if (row % 3 == 0 && row != 0)
				System.out.println(" |\n ------------------------");
			else if (row % 3 == 0)
				System.out.println(" -------------------------");
			else
				System.out.println(" | ");
			for (int col = 0; col < Sudoku.COLUMNS; col++) {
				if (col % 3 == 0)
					System.out.print(" | ");
				else
					System.out.print(" ");
				System.out.print(solution[row][col]);
			}
		}
		System.out.println(" |\n -------------------------");
	}
} // end of BackTrack