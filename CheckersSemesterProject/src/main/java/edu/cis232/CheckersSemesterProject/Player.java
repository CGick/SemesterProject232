package edu.cis232.CheckersSemesterProject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Player 
{
	private String player;
	private int wins, loses;
	
	public Player(String player)
	{
		setPlayer(player);
	}
	
	public void setPlayer(String player){
		ResultSet player1 = PlayerDBAccessor.lookUpPlayer(player);
	
		try {
			if(player1.next()){
				this.player = player1.getString("PlayerName");
				this.wins = player1.getInt("Wins");
				this.loses = player1.getInt("Loses");
			}else{
				PlayerDBAccessor.addPlayer(player);
				setPlayer(player);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	public String getPlayer()
	{
		return player;
	}
	
	public int getWins(){
		return wins;
	}
	
	public int getLoses(){
		return loses;
	}
	
	public String toString()
	{
		return "";
	}
}
