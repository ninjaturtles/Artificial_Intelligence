package com.wlu.ca.jkhalil.ai;

/**
 * file name: Constraint.java
 * Constraint class implementation
 * 
 * @author Akanksha Malik - Johnny Khalil - Marko Mihailovic - Jose Romero
 * @since Nov 6th, 2017
 */
public class Constraint {

    public int row;
    public int col;

    /**
     * Binary Constraint constructor
     * Need only the position of the constraint on the grid
     * @param rhsRow - the row of the second variable
     * @param rhsCol - the column of the second variable
     */
    public Constraint(int rhsRow, int rhsCol) {
        this.row = rhsRow;
        this.col = rhsCol;
    }
    
    @Override
    public String toString() {
    		return "("+row+","+col+")"; 
    }
} // end of Constraint
