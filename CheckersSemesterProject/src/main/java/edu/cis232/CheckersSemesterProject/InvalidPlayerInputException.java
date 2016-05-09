package edu.cis232.CheckersSemesterProject;

public class InvalidPlayerInputException extends RuntimeException
{
	public InvalidPlayerInputException ()
	{
		super ("You must enter a player name!");
	}
}
