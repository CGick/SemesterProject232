package edu.cis232.CheckersSemesterProject;

public class CheckersMove 
{
	private int prevRow, prevCol;  // Position of piece to be moved.
	private int nextRow, nextCol;      // Square it is to move to.
	
	/**
	* @param int r1
	* @param int c1
	* @param int r2
	* @param int c2
	*/
	public CheckersMove(int r1, int c1, int r2, int c2) 
	{
		prevRow = r1;
		prevCol = c1;
		nextRow = r2;
		nextCol = c2;
	}

	public boolean isJump() 
	{
		return (prevRow - nextRow == 2 || prevRow - nextRow == -2);
	}
}
