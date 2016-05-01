package edu.cis232.CheckersSemesterProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CreatePlayerDB {

	public static void main(String[] args) {
		final String DB_URL = "jdbc:hsqldb:file:Player/player;hsqldb.lock_file=false";

		try {
			Connection conn = DriverManager.getConnection(DB_URL);

			dropTables(conn);

			createPlayerTable(conn);

			conn.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void dropTables(Connection conn) {
		System.out.println("Checking for existing tables.");

		try {
			Statement stmt = conn.createStatement();

			try {
				stmt.execute("DROP TABLE Player");
				System.out.println("Player table dropped.");
			} catch (SQLException e) {
				System.out.println("Player table did not exist.");
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void createPlayerTable(Connection conn) {

		try {
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE Player ("
					     				+    "PlayerName VARCHAR(25) NOT NULL PRIMARY KEY,"
					     				+ 	 "Wins Integer ,"
					     				+ 	 "Loses Integer)");
			
			String sql = "INSERT INTO Player(PlayerName, Wins, Loses) VALUES (?, ?, ?)";
			
			PreparedStatement createTable = conn.prepareStatement(sql);
			
			// create row #1.
			createTable.setString(1, "Dan Rusk");
			createTable.setString(2, "0");
			createTable.setString(3, "0");

			createTable.execute();

			// create row #2.
			createTable.setString(1, "Chris Gick");
			createTable.setString(2, "0");
			createTable.setString(3, "0");

			createTable.execute();

			// create row #3.
			createTable.setString(1, "James ");
			createTable.setString(2, "0");
			createTable.setString(3, "0");
			
			createTable.execute();
			
			System.out.println("Employee table created.");
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
