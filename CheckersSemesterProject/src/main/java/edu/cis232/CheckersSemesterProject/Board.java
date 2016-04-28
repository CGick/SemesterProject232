package edu.cis232.CheckersSemesterProject;

public class Board 
{
	private boolean gameInProgress;
	private String currentPlayer;
	private CheckersData board;
	private PawnPiece pawn;
	private KingPiece king;
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
		if (currentPlayer.equals(player1.getPlayer()))
		{
			
		}
		else if (currentPlayer.equals(player2.getPlayer()))
		{
			
		}
		gameInProgress = false;
	}
	
	public void gameOver()
	{
		gameInProgress = false;
	}
}
