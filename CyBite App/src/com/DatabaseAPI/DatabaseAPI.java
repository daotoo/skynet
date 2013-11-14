package com.DatabaseAPI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DatabaseAPI {

	
	//Class variables
	
	/**
	 * Connection to the database being used
	 */
	private Connection connection;
	
	/**
	 * User ID from the database being used
	 */
	private String UserID = "u31916";
	
	/**
	 * Password for the User ID
	 */
	private String Password = "zKDd4gaBe";
	
	/**
	 * URL location of the database
	 */
	private String Url = "jdbc:mysql://mysql.cs.iastate.edu:3306/db31916";
	
	//Constructors
	
	/**
	 * Create a new DatabaseAPI Object. This will automatically connect to the database. Remember to call the disconnect method when done with this object!
	 */
	public DatabaseAPI()
	{
		connectToDatabase();
	}
	
	//Public get methods
	
	/**
	 * Disconnections to the database being used for the ShoppingSidekick application.
	 */
 	public void disconnect()
	{
		try {
			connection.close();
			System.out.println ("*** Disconnected to the database ***"); 
		} catch (SQLException e) {
			System.out.println("There was an error in the disconnect method and the creation was unsuccessful. Error message: " + e.getMessage());
		}
	}
	
	/**
	 * Drops all tables for the ShopppingSidekick application. WARNING: This method should NEVER be called.
	 */
	public void dropTables()
	{
		try {
			connection.createStatement().executeUpdate("DROP TABLE Restaurant");
	    	connection.createStatement().executeUpdate("DROP TABLE Maps");

		} catch (SQLException e) {
	    	  System.out.println("There was an error in the dropTables method. Error message: " + e.getMessage());
		}
	}
	
	//Private methods
	
	/**
	 * Connections to the database being used for the ShoppingSidekick application.
	 */
	private void connectToDatabase()
	{
		try {   
	      Class.forName ("com.mysql.jdbc.Driver");
	      } 
	   catch (Exception e) {
	         System.err.println ("Unable to load the JDBC driver. Error message: " + e.getMessage());
	   } 	
	   try { 
	      connection = DriverManager.getConnection (Url, UserID, Password);
	      System.out.println ("*** Connected to the database ***"); 
	   }
	      catch (SQLException e) {
	         System.out.println ("SQLException: " + e.getMessage());
	         System.out.println ("SQLState: " + e.getSQLState());
	         System.out.println ("VendorError: " + e.getErrorCode());
	      }
	}
}