Constraints Satisfaction Sudoku puzzle solver using AC3 and BackTrack algorithm.

  CSP REPRESENTATION

Consider a Sudoku puzzle as a CSP with 81 variables, one for each square. We use the variable names A1 through A9 for the top row (left to right), down to I1 through I9 for the bottom row. The empty squares have the domain {1, 2, 3, 4, 5, 6, 7, 8, 9} and the prefilled squares have a domain consisting of a single value. Let’s call a row, column, or box a unit. 

 

A variable, say A1, cannot be equal to any other variable in row A, column 1, and box 1. This produces 20 binary constrains per variable. A Sudoku puzzle yields a total of 1620 binary constrains. The following is how the constrains would look like:

A1
<(A1, A2), A1 != A2>
<(A1, A3), A1 != A3>
<(A1, A4), A1 != A4>
<(A1, A5), A1 != A5>
<(A1, A6), A1 != A6>
<(A1, A7), A1 != A7>
<(A1, A8), A1 != A8>
<(A1, A9), A1 != A9>
<(A1, B1), A1 != B1>
<(A1, C1), A1 != C1>
<(A1, D1), A1 != D1>
<(A1, E1), A1 != E1>
<(A1, F1), A1 != F1>
<(A1, G1), A1 != G1>
<(A1, H1), A1 != H1>
<(A1, I1), A1 != I1>
<(A1, F1), A1 != F1>
<(A1, G1), A1 != G1>
<(A1, H1), A1 != H1>
<(A1, I1), A1 != I1>

...


I9
<(I9, I1), I9 != I1>
<(I9, I2), I9 != I2>
<(I9, I3), I9 != I3>
<(I9, I4), I9 != I4>
<(I9, I5), I9 != I5>
<(I9, I6), I9 != I6>
<(I9, I7), I9 != I7>
<(I9, I8), I9 != I8>	
<(I9, A9), I9 != A9>
<(I9, B9), I9 != B9>
<(I9, C9), I9 != C9>
<(I9, D9), I9 != D9>
<(I9, E9), I9 != E9>
<(I9, F9), I9 != F9>
<(I9, G9), I9 != G9>
<(I9, H9), I9 != H9>	
<(I9, G7), I9 != G7>
<(I9, G8), I9 != G8>
<(I9, H7), I9 != H7>
<(I9, H8), I9 != H8>

One might prefer using global constrains as oppose to binary constrains. There are 27 different global Alldiff constrains; one for each row, column, and box of 9 squares. These constrains are:

Rows 
Alldiff(A1, A2, A3, A4, A5, A6, A7, A8, A9)
Alldiff(B1, B2, B3, B4, B5, B6, B7, B8, B9)
…
Alldiff(I1, I2, I3, I4, I5, I6, I7, I8, I9)

Columns
Alldiff(A1, B1, C1, D1, E1, F1, G1, H1, I1)
Alldiff(A2, B2, C2, D2, E2, F2, G2, H2, I2)
…
Alldiff(A9, B9, C9, D9, E9, F9, G9, H9, I9)

Boxes
Alldiff(A1, A2, A3, B1, B2, B3, C1, C2, C3)
Alldiff(A4, A5, A6, B4, B5, B6, C4, C5, C6)
…
Alldiff(G7, G8, G9, H7, H8, H9, I7, I8, I9)

 
  IMPLEMENTATION

Input file format :
530070000
600195000
098000060
800060003
400803001
700020006
060000280
000419005
000080079

where 0 represents an empty square.

  Classes:
Main.java
Variable.java
Arc.java
Constraint.java
Sudoku.java
AC3.java
BackTrck.java
