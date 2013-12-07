package com.DatabaseAPI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.DatabaseAPI.Restaurant;


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
	
	public ArrayList<Restaurant> getRestaurantSearchResults(int costFilter, int ratingFilter, String genreFilter, 
			String nameFilter, String addressFilter)
	{
		ArrayList<Restaurant> res = new ArrayList<Restaurant>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Restaurant r WHERE (r.Cost = ? OR ? = 0) AND (r.Rating = ? OR ? = 0) AND (r.Genre = ? OR ? = '') AND (r.Name LIKE ? OR ? = '') AND (r.Address = ? OR ? = '')");
			ps.setInt(1, costFilter);
			ps.setInt(2, costFilter);
			ps.setInt(3, ratingFilter);
			ps.setInt(4, ratingFilter);
			ps.setString(5, genreFilter);
			ps.setString(6, genreFilter);
			ps.setString(7, "%" + nameFilter +  "%");
			ps.setString(8, nameFilter);
			ps.setString(9, addressFilter);
			ps.setString(10, addressFilter);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Restaurant restaurant = new Restaurant();
				restaurant.setID(rs.getString("ID"));
				restaurant.setCost(rs.getInt("Cost"));
				restaurant.setRating(rs.getFloat("Rating"));
				restaurant.setGenre(rs.getString("Genre"));
				restaurant.setName(rs.getString("Name"));
				restaurant.setReview(rs.getString("Review"));
				restaurant.setLat(rs.getFloat("Lat"));
				restaurant.setLong(rs.getFloat("Lgt"));
				restaurant.setAddress(rs.getString("Address"));
				res.add(restaurant);
			}
		} catch (SQLException e) {
			System.out.println("There was an error in the getRestaurantSearchResult method. Error message: " + e.getMessage());
		}
		return res;
		
		
	}
	
	public Restaurant getRandomRestaurant(){
		Restaurant restaurant = new Restaurant();
		try{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Restaurant ORDER BY RAND() LIMIT 0,1");
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				restaurant.setID(rs.getString("ID"));
				restaurant.setCost(rs.getInt("Cost"));
				restaurant.setRating(rs.getFloat("Rating"));
				restaurant.setGenre(rs.getString("Genre"));
				restaurant.setName(rs.getString("Name"));
				restaurant.setReview(rs.getString("Review"));
				restaurant.setLat(rs.getFloat("Lat"));
				restaurant.setLong(rs.getFloat("Lgt"));
				restaurant.setAddress(rs.getString("Address"));
			}
		} catch (SQLException e){
			System.out.println("There was an error in the getRandomRestaurant method. Error message: " + e.getMessage());
		}
		return restaurant;
	}
	
	public boolean addRating(String id, int newRating)
	{
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Restaurant WHERE ID = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				//get current rating and number of ratings
				int numRatings = rs.getInt("NumRatings");
				float rating = rs.getFloat("Rating");
				rating = (rating*numRatings + newRating)/ ++numRatings;
				//update with new values
				ps = connection.prepareStatement("UPDATE Restaurant SET Rating=?, NumRatings=? WHERE ID = ?");
				ps.setFloat(1, rating);
				ps.setInt(2, numRatings);
				ps.setString(3, id);
				ps.executeUpdate();
				
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			System.out.println("There was an error in the addRating method and the rating was unsuccessful. Error message: " + e.getMessage());
			return false;
		}
		return true;
		
	}
	
	public ArrayList<Restaurant> getRestaurants()
	{
		ArrayList<Restaurant> res = new ArrayList<Restaurant>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Restaurant");

			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Restaurant restaurant = new Restaurant();
				restaurant.setCost(rs.getInt("Cost"));
				restaurant.setRating(rs.getInt("Rating"));
				restaurant.setGenre(rs.getString("Genre"));
				restaurant.setName(rs.getString("Name"));
				restaurant.setReview(rs.getString("Review"));
				restaurant.setLat(rs.getFloat("Lat"));
				restaurant.setLong(rs.getFloat("Lgt"));
				restaurant.setAddress(rs.getString("Address"));
				res.add(restaurant);
			}
		} catch (SQLException e) {
			System.out.println("There was an error in the getRestaurants method. Error message: " + e.getMessage());
		}
		return res;
		
		
	}
	
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