package edu.cis232.CheckersSemesterProject;
//REQ #6 Subclass that inherits superclass
//REQ #10 Polymorphism
public class PawnPiece extends Piece 
{
	protected static final int RED = 1, BLACK = 3;
	
	public PawnPiece(String piece)
	{
		super (piece);
	}
	
	@Override
	public String toString()
	{
		return getName();
	}
}
