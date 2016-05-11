package edu.cis232.CheckersSemesterProject;
//REQ #12 Custom Exception
public class InvalidPlayerInputException extends RuntimeException
{
	/**
	 * If the input to get player name is invalid, throw this exception.
	 */
	public InvalidPlayerInputException ()
	{
		super ("You must enter a player name!");
	}
}
