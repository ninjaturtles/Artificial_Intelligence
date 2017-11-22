package com.wlu.ai.khalil;

import java.util.ArrayList;

/**
 * Main.java
 * @author Akanksha Malik - Johnny Khalil - Marko Mihailovic - Jose Romero
 * @since October 1, 2017
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("Missionaries and Cannibals - River Crossing");

		State initialState = new State (3, 3, River_Bank.L, 0, 0);

		BreadthFirstSearch bfs = new BreadthFirstSearch();

		State goalState = bfs.startBFSSearch(initialState);

		printSolution(goalState);

	}

	/**
	 * helper method to print out the results
	 * @param goalState - the goal state if a solution is available
	 */
	private static void printSolution(State goalState) {
		int pathCost;
		State state;
		
		if (goalState == null) {
			System.out.print("\nUnable to find solution.");
		} else {
			System.out.println("\nSolution: \n(ML,CL,BP,MR,CR) ");
			ArrayList<State> path = new ArrayList<State>();
			
			state = goalState;
			
			// retrieve solution states, store in List
			while(state != null) {
				path.add(state);
				state = state.getParentNode();
			}

			pathCost = path.size() - 1;

			// print last item first
			for (int i = 0; i <= pathCost; i++) {
				System.out.println(path.get(pathCost - i));
			}
			
			System.out.println("\nPath Cost = " + pathCost);
			System.out.println("\nCannibals and Missionsaries crossed successfully and everyone is happy..");
		}
	}
}  // end of Main
