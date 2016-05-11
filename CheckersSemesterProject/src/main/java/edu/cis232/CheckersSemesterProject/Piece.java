package edu.cis232.CheckersSemesterProject;
//REQ #5 Superclass

/**
 * NOT IN USE CURRENTLY, WILL IMPLIMENT INTO BOARD ARRAY ONCE GAME WORKS!
 * 
 */
public class Piece 
{
	private String pieceName;
	
	public Piece(String pieceName)
	{
		this.pieceName = pieceName;
	}
	
	public String getName()
	{
		return "Piece name: " + pieceName;
	}
}
