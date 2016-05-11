package edu.cis232.CheckersSemesterProject;
//REQ #6 Subclass that inherits superclass
public class PawnPiece extends Piece 
{
	protected static final int RED = 1, BLACK = 3;
	
	public PawnPiece(String piece)
	{
		super (piece);
	}
	
	public String toString()
	{
		return "";
	}
}
