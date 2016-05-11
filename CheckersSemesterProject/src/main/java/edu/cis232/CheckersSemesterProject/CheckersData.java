package edu.cis232.CheckersSemesterProject;

import java.util.ArrayList;
//REQ #5 interface usage
public class CheckersData implements GameBoard
{
	private KingPiece king; //Holds pawn constants for pieces
	private PawnPiece pawn; //Holds king constants for peices
	private int[][] board;
	
	/**
	 * Creates a new Checkerdata board;
	 */
	public CheckersData()
	{
		board = new int[8][8];
		newGame();
	}
	
	/**
	 * Checks if the player can jump, temporary test method.
	 * 
	 * @param row int
	 * @param col int
	 * @return boolean
	 */
	public boolean canJump (int row, int col)
	{
		if (board[row][col] == 0)
		{
			System.out.println("Valid move!");
			return true;
		}
		return false;
	}
	
	/**
	 * Makes the jump, sets the cordinates given to the array.
	 * 
	 * @param row int
	 * @param col int
	 * @param player boolean
	 */
	public void makeJump(int row, int col, boolean player)
	{
		if (player)
			board[row][col] = pawn.RED;
		else
			board[row][col] = pawn.BLACK;
	}
	
	/**
	 * Tests to see if the board is generated correctly.
	 */
	public void getBoard()
	{
		for (int i=0; i<board.length; i++)
		{
			for (int j=0; j<board[i].length; j++)
			{
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	/**
	 * Restarts the board to the default state
	 */
	public void newGame()
	{
        for (int row = 0; row < 8; row++) 
        {
            for (int col = 0; col < 8; col++) 
            {
                if ( row % 2 == col % 2 ) 
                {
                    if (row < 3)
                            board[row][col] = PawnPiece.BLACK;
                    else if (row > 4)
                            board[row][col] = PawnPiece.RED;
                    else
                            board[row][col] = 0;
                }
                else 
                {
                	board[row][col] = 0;
                }
            }
        }		
	}
	
	/**
	 * Returns the piece at the selected row and column
	 * 
	 * @param row int
	 * @param col int
	 * @return int
	 */
	public int pieceAt(int row, int col)
	{
		return board[row][col];
	}
	
	/**
	 * Makes the move using the previous selected row and column
	 * and moves it to the next row and column
	 * 
	 * Helper method
	 * 
	 * @param move CheckersMove
	 */
	//REQ #5 interface usage
	public void makeMove (CheckersMove move)
	{
		makeMove(move.prevRow, move.prevCol, move.nextRow, move.nextCol);
	}
	
	/**
	 * Makes the move
	 * 
	 * @param prevRow int
	 * @param prevCol int 
	 * @param nextRow int
	 * @param nextCol int
	 */
	public void makeMove(int prevRow, int prevCol, int nextRow, int nextCol) 
	{
		board[nextRow][nextCol] = board[prevRow][prevCol];
		board[prevRow][prevCol] = 0;
		if (prevRow - nextRow == 2 || prevRow - nextRow == -2) 
		{		
			// The move is a jump.  Remove the jumped piece from the board.
			int jumpRow = (prevRow + nextRow) / 2;  // Row of the jumped piece.
			int jumpCol = (prevCol + nextCol) / 2;  // Column of the jumped piece.
			board[jumpRow][jumpCol] = 0;
		}
		if (nextRow == 0 && board[nextRow][nextCol] == pawn.RED)
			board[nextRow][nextCol] = king.RED_KING;
		if (nextRow == 7 && board[nextRow][nextCol] == pawn.BLACK)
			board[nextRow][nextCol] = king.BLACK_KING;
	}
	
	/**
	* @return CheckersMove[] moveArray
	* Returns all avaliable moves, including jumps.
	*/
	public CheckersMove[] getValidMoves(int player) 
	{
		if (player != pawn.RED && player != pawn.BLACK)
			return null;

		int playerKing;  //Represents a king
		if (player == pawn.RED)
			playerKing = king.RED_KING;
		else
			playerKing = king.BLACK_KING;
		ArrayList moves = new ArrayList();  // Stores avaliable moves
		
		for (int row = 0; row < 8; row++) 
		{
			for (int col = 0; col < 8; col++) 
			{
				if (board[row][col] == player || board[row][col] == playerKing) 
				{
					if (canJump(player, row, col, row+1, col+1, row+2, col+2)) //Looks to jump NorthEast
						moves.add(new CheckersMove(row, col, row+2, col+2));
					if (canJump(player, row, col, row-1, col+1, row-2, col+2)) //Looks to jump NorthWest
						moves.add(new CheckersMove(row, col, row-2, col+2));
					if (canJump(player, row, col, row+1, col-1, row+2, col-2)) //Looks to jump SouthEast
						moves.add(new CheckersMove(row, col, row+2, col-2));
					if (canJump(player, row, col, row-1, col-1, row-2, col-2)) //Lookst to jump SouthWest
						moves.add(new CheckersMove(row, col, row-2, col-2));
				}
			}
		}
         
		/*  
		* If jumps are found, the user is forced to take them
		*/        
		if (moves.size() == 0) 
		{
			for (int row = 0; row < 8; row++) 
			{
				for (int col = 0; col < 8; col++) 
				{
					if (board[row][col] == player || board[row][col] == playerKing) 
					{
						if (canMove(player,row,col,row+1,col+1)) //Looks to move NorthEast
							moves.add(new CheckersMove(row,col,row+1,col+1)); 
						if (canMove(player,row,col,row-1,col+1)) //Looks to move NorthWest
							moves.add(new CheckersMove(row,col,row-1,col+1));
						if (canMove(player,row,col,row+1,col-1))
							moves.add(new CheckersMove(row,col,row+1,col-1)); //Looks to move SouthEast
						if (canMove(player,row,col,row-1,col-1))
							moves.add(new CheckersMove(row,col,row-1,col-1)); //Looks to move SouthWest
					}
				}
			}
		}        
		/*
		* If no legal moves, returns null
		* else returns a custom size array containing moves
		*/

		if (moves.size() == 0)
			return null;
		else 
		{
			CheckersMove[] moveArray = new CheckersMove[moves.size()];
			for (int i = 0; i < moves.size(); i++)
				moveArray[i] = (CheckersMove)moves.get(i);
			return moveArray;
		}
	}
	
	/**
	 * Returns all possible jumps with a CheckersMove array.
	 * 
	 * @param player int
	 * @param row int
	 * @param col int
	 * @return CheckersMove
	 */
	public CheckersMove[] getValidJumps(int player, int row, int col) 
	{
		if (player != pawn.RED && player != pawn.BLACK)
			return null;
		int playerKing;  //Represents a king
		if (player == pawn.RED)
			playerKing = king.RED_KING;
		else
			playerKing = king.BLACK_KING;
		ArrayList moves = new ArrayList();  // Stores legal jumps
		if (board[row][col] == player || board[row][col] == playerKing) 
		{
			if (canJump(player, row, col, row+1, col+1, row+2, col+2)) //Looks to move NorthEast
				moves.add(new CheckersMove(row, col, row+2, col+2));
			if (canJump(player, row, col, row-1, col+1, row-2, col+2)) //Looks to move NorthWest
				moves.add(new CheckersMove(row, col, row-2, col+2));
			if (canJump(player, row, col, row+1, col-1, row+2, col-2)) //Looks to move SouthEast
				moves.add(new CheckersMove(row, col, row+2, col-2));
			if (canJump(player, row, col, row-1, col-1, row-2, col-2)) //Looks to move SouthWest
				moves.add(new CheckersMove(row, col, row-2, col-2));
		}
		if (moves.size() == 0)
			return null;
		else 
		{
			CheckersMove[] moveArray = new CheckersMove[moves.size()];
			for (int i = 0; i < moves.size(); i++)
				moveArray[i] = (CheckersMove)moves.get(i);
			return moveArray;
		}
	}
	
	/**
	* Determines the player can jump
	* Looks ahead 2 spaces in directions NE, NW, SE, SW. 
	 * 
	 * @param player int
	 * @param r1 int
	 * @param c1 int
	 * @param r2 int
	 * @param c2 int
	 * @param r3 int
	 * @param c3 int
	 * @return boolean
	 */
	public boolean canJump(int player, int r1, int c1, int r2, int c2, int r3, int c3) 
	{    
		if (r3 < 0 || r3 >= 8 || c3 < 0 || c3 >= 8)
			return false; //Off the board

		if (board[r3][c3] != 0)
			return false; 
         
         if (player == pawn.RED)
		{
            if (board[r1][c1] == pawn.RED && r3 > r1)
               return false;  
            if (board[r2][c2] != pawn.BLACK && board[r2][c2] != king.BLACK_KING)
               return false;
            return true; 
        }
		else 
		{
			if (board[r1][c1] == pawn.BLACK && r3 < r1)
				return false;  
			if (board[r2][c2] != pawn.RED && board[r2][c2] != king.RED_KING)
				return false;
			return true; 
		}

	}   
	
	/**
	* Looks to see if any avaliable moves
	* Looks ahead 1 space to see if there is an empty spot or not off the grid
	 * 
	 * @param player int
	 * @param r1 int
	 * @param c1 int
	 * @param r2 int
	 * @param c2 int 
	 * @return boolean
	 */
	public boolean canMove(int player, int r1, int c1, int r2, int c2) 
	{     
		if (r2 < 0 || r2 >= 8 || c2 < 0 || c2 >= 8)
			return false;  // Off the board

		if (board[r2][c2] != 0)
			return false; 
		if (player == pawn.RED) 
		{
			if (board[r1][c1] == pawn.RED && r2 > r1)
				return false;  
			return true;  
		}
		else 
		{
			if (board[r1][c1] == pawn.BLACK && r2 < r1)
				return false;
			return true;  
		}
	}
}
