package com.wlu.ai.khalil;

import java.util.ArrayList;
import java.util.List;

// boat position
enum River_Bank {R, L}

/**
 * State class manages and validates states.
 * @author Akanksha Malik - Johnny Khalil - Marko Mihailovic - Jose Romero
 * @since October 1, 2017
 */
public class State {

	private State parentNode;
	private int missionariesLeft;
	private int cannibalsLeft;
	private River_Bank boatPosition;
	private int missionariesRight;
	private int cannibalsRight;

	/**
	 * Class Constructor - initialize new State object
	 * @param missionariesLeft - number of missionaries on the left bank of the river.
	 * @param cannibalsLeft - number of cannibals on the left bank of the river.
	 * @param boatPosition - position of the boat
	 * @param missionariesRight - number of missionaries on the right bank of the river.
	 * @param cannibalsRight - number of cannibals on the right bank of the river.
	 */
	public State (int missionariesLeft, int cannibalsLeft, River_Bank boatPosition,
			int missionariesRight, int cannibalsRight) {

		this.missionariesLeft = missionariesLeft;
		this.cannibalsLeft = cannibalsLeft;
		this.boatPosition = boatPosition;
		this.missionariesRight = missionariesRight;
		this.cannibalsRight = cannibalsRight;
	}

	@Override
	public String toString() {
		return "(" + missionariesLeft + ", " +  cannibalsLeft + ", " + boatPosition
		+ ", " +  missionariesRight + ", " + cannibalsRight + ")";
	}

	@Override
	public boolean equals(Object object) {
		State state = (State) object;
		return (state.missionariesLeft == missionariesLeft 
				&& state.cannibalsLeft == cannibalsLeft
				&& state.boatPosition == boatPosition 
				&& state.missionariesRight == missionariesRight
				&& state.cannibalsRight == cannibalsRight);
	}

	/**
	 * checks if state is goal state.
	 * @return true if state is goal state, false otherwise.
	 */
	public boolean goalTest() {
		return missionariesLeft == 0 && cannibalsLeft == 0 && boatPosition == River_Bank.R
				&& missionariesRight == 3 && cannibalsRight == 3;
	}

	/**
	 * checks if state is a valid state, i.e. missionaries are not out-numbered by cannibals 
	 * at any given time.
	 * @return true if state is valid, false otherwise.
	 */
	public boolean isValidState() {
		if (missionariesLeft >= 0 && cannibalsLeft >=0 
				&& missionariesRight >=0 && cannibalsRight >= 0
				&& (missionariesLeft == 0 || missionariesLeft >= cannibalsLeft)
				&& (missionariesRight == 0 || missionariesRight >= cannibalsRight)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Actions - generates a List of all possible and valid next states, given current state.
	 * @return List of all possible and valid/legal next states.
	 */
	public List<State> generateSuccessorStates() {
		List<State> nextValidStates = new ArrayList<State>();
		if (boatPosition == River_Bank.L) {
			nextValidStates = perform_right_action();
		} else {
			nextValidStates = perform_left_action();
		}

		return nextValidStates;
	}
	
	private List<State> perform_left_action() {
		List<State> nextValidStates = new ArrayList<State>();
		// Two missionaries sail to left bank.
		validateAndAddtoList(nextValidStates, two_m_left()); 
		// Two cannibals sail to left bank.
		validateAndAddtoList(nextValidStates, two_c_left());
		// One missionary and one cannibal sail to left bank.
		validateAndAddtoList(nextValidStates, one_m_one_c_left());
		// One missionary sails to left bank.
		validateAndAddtoList(nextValidStates, one_m_left()); 
		// One cannibal sails to left bank.
		validateAndAddtoList(nextValidStates, one_c_left());
		
		return nextValidStates;
	}
	
	private List<State> perform_right_action() {
		List<State> nextValidStates = new ArrayList<State>();
		// Two missionaries sail to right bank.
		validateAndAddtoList(nextValidStates, two_m_right()); 
		// Two cannibals sail to right bank.
		validateAndAddtoList(nextValidStates, two_c_right()); 
		// One missionary and one cannibal sail to right bank.
		validateAndAddtoList(nextValidStates, one_m_one_c_right()); 
		// One missionary sails to right bank.
		validateAndAddtoList(nextValidStates, one_m_right());
		// One cannibal sails to right bank.
		validateAndAddtoList(nextValidStates, one_c_right());
		
		return nextValidStates;
	}

	/**
	 * helper method that verifies if a state is a valid state, if so it adds it to the 
	 * list of valid next states.
	 * @param nextValidStates - List of all all possible and valid next states
	 * @param newState - new state to be validated and added.
	 */
	private void validateAndAddtoList(List<State> nextValidStates, State newState) {
		if (newState.isValidState()) {
			newState.setParentNode(this);
			nextValidStates.add(newState);
		}
	}

	/**
	 * @return a state with two missionaries moved to right bank
	 */
	private State two_m_right() {
		State state = new State(missionariesLeft - 2, cannibalsLeft, 
				River_Bank.R, missionariesRight + 2, cannibalsRight);
		return state;
	}
	
	/**
	 * @return a state with two cannibals moved to right bank
	 */
	private State two_c_right() {
		State state = new State(missionariesLeft, cannibalsLeft - 2, 
				River_Bank.R, missionariesRight, cannibalsRight + 2);
		return state;
	}
	
	/**
	 * @return a state with one missionary and one cannibal moved to right bank
	 */
	private State one_m_one_c_right() {
		State state = new State(missionariesLeft - 1, cannibalsLeft - 1, 
				River_Bank.R, missionariesRight + 1, cannibalsRight + 1);
		return state;
	}
	
	/**
	 * @return a state with one missionary moved to right bank
	 */
	private State one_m_right() {
		State state = new State(missionariesLeft, cannibalsLeft - 1,
				River_Bank.R, missionariesRight, cannibalsRight + 1);
		return state;
	}
	
	/**
	 * @return a state with one cannibal moved to right bank
	 */
	private State one_c_right() {
		State state = new State(missionariesLeft, cannibalsLeft - 1, 
				River_Bank.R, missionariesRight + 1, cannibalsRight);
		return state;
	}
	
	/**
	 * @return a state with two missionaries moved to left bank
	 */
	private State two_m_left() {
		State state = new State(missionariesLeft + 2, cannibalsLeft, 
				River_Bank.L, missionariesRight - 2, cannibalsRight);
		return state;
	}
	
	/**
	 * @return a state with two cannibals moved to left bank
	 */
	private State two_c_left() {
		State state = new State(missionariesLeft, cannibalsLeft + 2, 
				River_Bank.L, missionariesRight, cannibalsRight - 2);
		return state;
	}
	
	/**
	 * @return a state with one missionary and one cannibal moved to left bank
	 */
	private State one_m_one_c_left() {
		State state = new State(missionariesLeft + 1, cannibalsLeft + 1, 
				River_Bank.L, missionariesRight - 1, cannibalsRight - 1);
		return state;
	}
	
	/**
	 * @return a state with one missionary moved to left bank
	 */
	private State one_m_left() {
		State state = new State(missionariesLeft + 1, cannibalsLeft, 
				River_Bank.L, missionariesRight - 1, cannibalsRight);
		return state;
	}
	
	/**
	 * @return a state with one cannibal moved to left bank
	 */
	private State one_c_left() {
		State state = new State(missionariesLeft, cannibalsLeft + 1, 
				River_Bank.L, missionariesRight, cannibalsRight - 1);
		return state;
	}
	
	/*------------------- Getters and Setters -------------------*/
	public State getParentNode() {
		return parentNode;
	}

	public void setParentNode(State parentNode) {
		this.parentNode = parentNode;
	}
}