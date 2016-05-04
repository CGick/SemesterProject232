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
	
	/**
	* Resets the board to begin a new game
	*/
	public void gameOver(String str) 
	{
		gameInProgress = false;
	}
	
	
	/**
	* Called when the user clicked on a valid move location
	*/
	public void makeMove(CheckersMove move) 
	{
		board.makeMove(move);        

		//If the player can jump more, the game forces the user to.
		if (move.isJump()) 
		{
			legalMoves = board.getValidJumps(currentPlayer,move.nextRow,move.nextCol);
			if (legalMoves != null) 
			{
				if (currentPlayer == PawnPiece.RED)
					System.out.println("");
					//message.setText("Player1:  You must continue jumping.");
				else
					System.out.println("");
					//message.setText("Player2:  You must continue jumping.");
				selectedRow = move.nextRow; 
				selectedCol = move.nextCol;
				return;
			}
		}
		/* The current player's turn is ended, so change to the other player.
		Get that player's legal moves.  If the player has no legal moves,
		then the game ends. */

		if (currentPlayer == PawnPiece.RED) 
		{
			currentPlayer = PawnPiece.BLACK;
			legalMoves = board.getValidMoves(currentPlayer);
			if (legalMoves == null)
			{
				gameOver("Player2 has no moves.  Player1 wins.");
				//start.updateScorePlayer1(p1.getId(), true); //WILL NOT WORK CURRENTLY
				//start.updateScorePlayer2(p2.getId(), false); //WILL NOT WORK CURRENTLY
			}
			else if (legalMoves[0].isJump())
				System.out.println("");
				//message.setText("Player2:  Make your move.  You must jump.");
			else
				System.out.println("");
				//message.setText("Player2:  Make your move.");
		}
		else 
		{
			currentPlayer = PawnPiece.RED;
			legalMoves = board.getValidMoves(currentPlayer);
			if (legalMoves == null)
			{
				gameOver("Player1 has no moves.  Player2 wins.");
				//start.updateScorePlayer1(p1.getId(), false); //WILL NOT WORK CURRENTLY
				//start.updateScorePlayer2(p2.getId(), true); //WILL NOT WORK CURRENTLY
			}
			else if (legalMoves[0].isJump())
				System.out.println("");
				//message.setText("Player1:  Make your move.  You must jump.");
			else
				System.out.println("");
				//message.setText("Player1:  Make your move.");
		}

		selectedRow = -1; //User hasn't selected move

		//Checks if there is more moves to be made
		if (legalMoves != null) 
		{
		boolean sameStartSquare = true;
			for (int i = 1; i < legalMoves.length; i++)
				if (legalMoves[i].prevRow != legalMoves[0].prevRow || legalMoves[i].prevCol != legalMoves[0].prevCol) 
				{
					sameStartSquare = false;
					break;
				}
			if (sameStartSquare) 
			{
				selectedRow = legalMoves[0].prevRow;
				selectedCol = legalMoves[0].prevCol;
			}
		}
	}
}
