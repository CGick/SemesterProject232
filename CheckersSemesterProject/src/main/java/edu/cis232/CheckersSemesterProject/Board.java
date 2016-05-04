package edu.cis232.CheckersSemesterProject;

public class Board 
{
	private boolean gameInProgress;
	private int currentPlayer, selectedRow, selectedCol;
	private CheckersData board;
	public CheckersMove[] legalMoves;
	private Player player1, player2;
	
	public Board()
	{
		player1 = new Player("Player1");
		player2 = new Player("Player2");
	}
	
	public void newGame()
	{
		gameInProgress = true;
	}
	
	public void resign()
	{
		if (currentPlayer == PawnPiece.RED)
		{
			
		}
		else if (currentPlayer == PawnPiece.BLACK)
		{
			
		}
		gameInProgress = false;
	}
	
	public void gameOver()
	{
		gameInProgress = false;
	}
	
	public void makeMove (CheckersMove move)
	{
		
	}
}
