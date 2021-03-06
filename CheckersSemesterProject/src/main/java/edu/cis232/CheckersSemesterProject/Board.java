package edu.cis232.CheckersSemesterProject;
//REQ #5 interface usage
public class Board implements GameBoard
{
	private boolean gameInProgress;
	private int currentPlayer, selectedRow, selectedCol, previousRow, previousColumn;
	private CheckersData board;
	private PawnPiece pawn;
	private KingPiece king;
	public CheckersMove[] legalMoves;
	
	public Board()
	{
		board = new CheckersData();
		newGame();
	}
	
	public void newGame()
	{
		if (gameInProgress)
		{
			System.out.println("Game in progress");
		}
		board.newGame();
		currentPlayer = pawn.RED;
		legalMoves = board.getValidMoves(pawn.RED);
		gameInProgress = true;
	}
	
	public void getBoard()
	{
		board.getBoard();
	}
	
	public void setPrevious(int row, int col)
	{
		previousRow = row;
		previousColumn = col;
	}
	
	public void getPrevious()
	{
		System.out.println("Previous row: " + previousRow + "\nPrevious column: " + previousColumn);
	}
	
	/**
	* Resets the board to begin a new game
	*/
	public void gameOver() 
	{
		gameInProgress = false;
	}
	
	/**
	 * Assigns the selected row and column
	 * 
	 * @param row int
	 * @param col int
	 */
	public void clickSquare(int row, int col)
	{		
		for (int i = 0; i < legalMoves.length; i++)
		{
			if (legalMoves[i].prevRow == row && legalMoves[i].prevCol == col) 
			{
				selectedRow = row;
				selectedCol = col;
				System.out.println("Selected row: " + selectedRow);
				System.out.println("Selected column: " + selectedCol);
			}
		}

		//If the piece can move, it proceeds to move
//		for (int i = 0; i < legalMoves.length; i++)
//		if (legalMoves[i].prevRow == selectedRow && legalMoves[i].prevCol == selectedCol && legalMoves[i].nextRow == row && legalMoves[i].nextCol == col) 
//		{
//			makeMove(legalMoves[i]);
//			
//		}
	}
	
	
	/**
	* Called when the user clicked on a valid move location
	*/
	//REQ #5 interface usage
	public void makeMove(CheckersMove move) 
	{
		board.makeMove(move);        

		//If the player can jump more, the game forces the user to.
		if (move.canJump()) 
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

		switchPlayer();

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

	/**
	 * Switches the player with the logical board.
	 */
	private void switchPlayer() {
		if (currentPlayer == PawnPiece.RED) 
		{
			currentPlayer = PawnPiece.BLACK;
			legalMoves = board.getValidMoves(currentPlayer);
			if (legalMoves == null)
			{
				gameOver();
				
			}
			else if (legalMoves[0].canJump())
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
				gameOver();
				
			}
			else if (legalMoves[0].canJump())
				System.out.println("");
				//message.setText("Player1:  Make your move.  You must jump.");
			else
				System.out.println("");
				//message.setText("Player1:  Make your move.");
		}
	}
}
