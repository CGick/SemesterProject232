/**
 * REQ#7 & REQ#8 & REQ#11
 * class hold static methods for storing, accessing, and modifying data in the player database
 */
package edu.cis232.CheckersSemesterProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDBAccessor {

	private final static String DB_URL = "jdbc:hsqldb:file:Player/player;hsqldb.lock_file=false";

	/**
	 * Adds a player
	 * 
	 * @param player String
	 */
	public static void addPlayer(String player) {
		try {
			Connection conn = DriverManager.getConnection(DB_URL);

			String sql = "INSERT INTO Player (PlayerName, Wins, Loses) values (?, ?, ?)";

			PreparedStatement insertPlayer = conn.prepareStatement(sql);

			insertPlayer.setString(1, player);
			insertPlayer.setString(2, "0");
			insertPlayer.setString(3, "0");

			insertPlayer.execute();
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Looks for a player and returns it
	 * 
	 * @param player
	 * @return ResultSet
	 */
	public static ResultSet lookUpPlayer(String player){
		ResultSet result;
		try{
		Connection conn = DriverManager.getConnection(DB_URL);
		
		String sql = "SELECT * FROM Player WHERE PlayerName = ?";
		
		PreparedStatement lookUp = conn.prepareStatement(sql);
		
		lookUp.setString(1, player);
		
		result = lookUp.executeQuery();
		
		conn.close();
		
		}catch (SQLException e){
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	/**
	 * Updates the player
	 * 
	 * @param player String
	 * @param wins int
	 * @param loses int
	 */
	public static void updatePlayer(String player, int wins, int loses){
		try{
			Connection conn = DriverManager.getConnection(DB_URL);
			
			String sql = "UPDATE Player SET Wins = ?, Loses = ? WHERE PlayerName = ?";
			
			PreparedStatement update = conn.prepareStatement(sql);
			
			update.setString(1, String.valueOf(wins));
			update.setString(2, String.valueOf(loses));
			update.setString(3, player);
			
			update.execute();
			
			conn.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
}
