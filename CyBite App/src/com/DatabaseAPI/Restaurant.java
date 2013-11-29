package com.DatabaseAPI;


	public class Restaurant
	{

		private String ID;

		private String Name;
		
		private String Genre;
		
		private int Cost;
		
		private double Rating;
		
		private String Review;
		
		private float Lat;
		
		private float Lgt;
		
		private String Address;

		/**
		 * @return the iD
		 */
		public String getID() {
			return ID;
		}

		/**
		 * @param iD the iD to set
		 */
		public void setID(String iD) {
			ID = iD;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return Name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			Name = name;
		}

		/**
		 * @return the genre
		 */
		public String getGenre() {
			return Genre;
		}

		/**
		 * @param genre the genre to set
		 */
		public void setGenre(String genre) {
			Genre = genre;
		}

		/**
		 * @return the cost
		 */
		public int getCost() {
			return Cost;
		}

		/**
		 * @param cost the cost to set
		 */
		public void setCost(int cost) {
			Cost = cost;
		}

		/**
		 * @return the rating
		 */
		public double getRating() {
			return Rating;
		}

		/**
		 * @param rating the rating to set
		 */
		public void setRating(double rating) {
			Rating = rating;
		}
		
		/**
		 * 
		 * @return review 
		 */
		public String getReview(){
			return Review;
		}
		
		/***
		 *
		 * @param review
		 */
		public void setReview(String review){
			Review = review;
			
		}
		
		/**
		 * Returns longitude of the restaurant.
		 * 
		 * @return
		 */
		public float getLongitude(){
			return Lgt;
		}
		
		/**
		 * Set longitude of a restaurant.
		 * 
		 * @param longitude
		 */
		public void setLong(float longitude){
			Lgt = longitude;
		}
		
		/**
		 * Get latitude of a restaurant.
		 * 
		 * @return
		 */
		public float getLat(){
			return Lat;
		}
		
		/**
		 * Set latitude of the restaurant.
		 * 
		 * @param latitude
		 */
		public void setLat(float latitude){
			Lat = latitude;
		}
		
		/**
		 *Returns the address of the restaurant. 
		 *
		 * @return
		 */
		public String getAddress(){
			return Address;
		}
		
		/**
		 * Sets the address of the restaurant
		 * 
		 * @param address
		 */
		public void setAddress(String address){
			Address = address;
		}

	}
