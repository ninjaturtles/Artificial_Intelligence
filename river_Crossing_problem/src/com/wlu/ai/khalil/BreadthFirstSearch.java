package com.wlu.ai.khalil;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Breadth First Search class. Performs BFS to find the optimal
 * next state in the state space
 * @author Johnny Khalil
 * @since October 1, 2017
 */
public class BreadthFirstSearch {
	
	/**
	 * Performs BFS to find the optimal next state.
	 * @param initialState - Initial state (ML, CL, BP, MR, CR)
	 *  = (3, 3, L, 0, 0)
	 * @return goal state, or null on error
	 */
	public State startBFSSearch(State initialState) {
		if (initialState.goalTest()) {
			return initialState;
		}
		
		// The set of all leaf nodes available for expansion
		// at any given point
		LinkedList<State> frontier = new LinkedList<State>();
		// Remembers every expanded/explored node
		HashSet<State> exploredStates = new HashSet<State>();
		frontier.add(initialState);
		
		// expand nodes on the frontier, until either a solution
		// is found or there are no more states to expand
		while (true) {
			if (frontier.isEmpty()) {
				return null;	
			}
			
			State node = frontier.pop();
			exploredStates.add(node);
			
			// generate successors of already-explored states
			List<State> nextValidStates = node.generateSuccessorStates();
			
			for (int i = 0; i < nextValidStates.size(); i++) {
				State nextNode = nextValidStates.get(i);
				if (!exploredStates.contains(nextNode) || !frontier.contains(nextNode)) {
					if (nextNode.goalTest() == true) {
						return nextNode;
					}
					frontier.add(nextNode);
				}
			}
		}
	}
} // end of BreadthFirstSearch
