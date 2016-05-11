package edu.cis232.CheckersSemesterProject;
//REQ #12 Custom Exception
public class InvalidPlayerInputException extends RuntimeException
{
	public InvalidPlayerInputException ()
	{
		super ("You must enter a player name!");
	}
}
