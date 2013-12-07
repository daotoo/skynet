package com.example.cybite;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.DatabaseAPI.DatabaseAPI;
import com.DatabaseAPI.Restaurant;

public class RestaurantActivity extends Activity{

	ArrayList<String> costList;
	ArrayList<String> genreList;
	ArrayList<String> ratingList;
	ArrayList<Restaurant> searchResultsList;
	ArrayList<String> listItems;
	Restaurant r;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant);
		
		initLists();
		createAndSetAdapters();
		
		//name search will query our database for restaurants with the given
		//search string in their name and takes users to restaurant results
		//which will display all appropriate restaurants based off search
		Button nameSearchBtn = (Button) findViewById(R.id.nameSearchButton);
        nameSearchBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ExecutorService pool = Executors.newFixedThreadPool(3);
				final String st = getNameSearchFieldText();
				if (st == "")
				{
					return;
				}
		        Callable task = new Callable(){
					@Override
					public Object call() throws Exception {
						return getSearchResults(st);
					}
					
					/**
					 * Gets search results from the Database based off of searchText
					 * @param searchText the entered search term
					 * @return arrayList of Food items
					 */
					private ArrayList<Restaurant> getSearchResults(String searchText){
						
							DatabaseAPI d = new DatabaseAPI();
							ArrayList<Restaurant> searchResultsList = d.getRestaurantSearchResults(0, 0, "", st, "");
							return searchResultsList;
						
					}
					
		        };
		        Future<ArrayList<Restaurant>> future = pool.submit(task);
		        try {
					searchResultsList = future.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}

		       toRestaurantResults();
		        
			}
        });
        
        //filter search will allow users to search for a restaurant based off cost, genre, and rating
        //and takes user to restaurant results page that displays list of all restaurants matching
        //the given filter values
        Button filterSearchButton = (Button) findViewById(R.id.filterSearchButton);
        filterSearchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ExecutorService pool = Executors.newFixedThreadPool(10);
				final String st = getNameSearchFieldText();
				
				Spinner costSpinner = (Spinner) findViewById(R.id.costSpinner);
				Spinner genreSpinner = (Spinner) findViewById(R.id.genreSpinner);
				Spinner ratingSpinner = (Spinner) findViewById(R.id.ratingSpinner);
				String costFilterStr = (String) costSpinner.getSelectedItem();
				String ratingFilterStr = (String) ratingSpinner.getSelectedItem();

				//cost filter
				int costFilter = 0;
				if(costFilterStr.equals("Cheap")){
					costFilter = 1;
				}
				else if (costFilterStr.equals("Moderate")){
					costFilter = 2;
				}
				else if (costFilterStr.equals("Expensive")){
					costFilter = 3;
				}
				final int finalCostFilter = costFilter;
				
				//rating filter
				int ratingFilter = 0;
				if(ratingFilterStr.contains("5")){
					ratingFilter = 5;
				}
				else if (ratingFilterStr.contains("4")){
					ratingFilter = 4;
				}
				else if (ratingFilterStr.contains("3")){
					ratingFilter = 3;
				}
				else if (ratingFilterStr.contains("2")){
					ratingFilter = 2;
				}
				else if (ratingFilterStr.contains("1")){
					ratingFilter = 1;
				}
				final int finalRatingFilter = ratingFilter;
				
				//genre filter
				String genreFilter = (String) genreSpinner.getSelectedItem();
				if (genreFilter.contains("Any"))
				{
					genreFilter = "";
				}			
				final String finalGenreFilter = genreFilter;

		        
				
				Callable task = new Callable(){
					@Override
					public Object call() throws Exception {
						return getSearchResults(finalCostFilter, finalRatingFilter, finalGenreFilter);
					}
					
					/**
					 * Gets search results from the Database based off of searchText
					 * @param searchText the entered search term
					 * @return arrayList of Food items
					 */
					private ArrayList<Restaurant> getSearchResults(int costFilter, int ratingFilter, String genreFilter){
							DatabaseAPI d = new DatabaseAPI();
							ArrayList<Restaurant> searchResultsList = d.getRestaurantSearchResults(costFilter, ratingFilter, genreFilter, "", "");
							return searchResultsList;
					}
					
		        };
		        Future<ArrayList<Restaurant>> future = pool.submit(task);
		        try {
					searchResultsList = future.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
		        
				toRestaurantResults();
		        
			}
        });
        
        //random button will query our database and take user to restaurant info page
        Button randomButton = (Button) findViewById(R.id.randomButton);
        randomButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ExecutorService pool = Executors.newFixedThreadPool(10);
				Callable task = new Callable(){
					@Override
					public Object call() throws Exception {
						return getSearchResults();
					}
					
					/**
					 * Gets search results from the Database based off of searchText
					 * @param searchText the entered search term
					 * @return arrayList of Food items
					 */
					private Restaurant getSearchResults(){
							DatabaseAPI d = new DatabaseAPI();
							Restaurant r = d.getRandomRestaurant();
							return r;
					}
					
		        };
		        Future<Restaurant> future = pool.submit(task);
		        try {
					r = future.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
		        
				Intent i = new Intent(RestaurantActivity.this, RestaurantInfoActivity.class);
				i.putExtra("restaurant", r);
				startActivity(i);
		        
			}
        });
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.restaurant, menu);
		return true;
	}

	/**
	 * creates and starts intent to RestaurantResultsActivity, also sending our searchResultsList
	 */
	private void toRestaurantResults(){
		 Intent i = new Intent(RestaurantActivity.this, RestaurantResultsActivity.class);
	     i.putExtra("searchResultsList", searchResultsList);
	     startActivity(i);
		
	}
	/**
	 * gets the text input of the name search field
	 * @return text input of the name search field
	 */
	private String getNameSearchFieldText(){
		String st = "";
		EditText searchField = (EditText)findViewById(R.id.nameSearchField);
		if(searchField.length() > 0)
			return searchField.getText().toString();
		else
			st = "";
		return st;
	}
	

	/**
	 * initializes and populates costList, genreList, and ratingList
	 */
	private void initLists(){
		listItems = new ArrayList<String>();
		r = new Restaurant();
		costList = new ArrayList<String>(); 
		genreList = new ArrayList<String>(); 
		ratingList = new ArrayList<String>();
		costList.add("Any");
		costList.add("Cheap");
		costList.add("Moderate");
		costList.add("Expensive");
		
		genreList.add("Any");
		genreList.add("Mexican");
		genreList.add("Fast Food");
		genreList.add("Italian");
		genreList.add("Chinese");
		genreList.add("American");
		genreList.add("Bar and Grill");
		genreList.add("Japanese");
		genreList.add("Breakfast");
		genreList.add("Deli");
		
		ratingList.add("Any");
		ratingList.add("1 or above");
		ratingList.add("2 or above");
		ratingList.add("3 or above");
		ratingList.add("4 or above");
		ratingList.add("5");
	}
	
	/**
	 * creates and sets adapters to their correct spinners
	 */
	private void createAndSetAdapters(){
		Spinner costSpinner = (Spinner) findViewById(R.id.costSpinner);
		Spinner genreSpinner = (Spinner) findViewById(R.id.genreSpinner);
		Spinner ratingSpinner = (Spinner) findViewById(R.id.ratingSpinner);
		
		ArrayAdapter<String> costSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, costList);
		costSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		costSpinner.setAdapter(costSpinnerAdapter);
		
		ArrayAdapter<String> genreSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genreList);
		genreSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		genreSpinner.setAdapter(genreSpinnerAdapter);
		
		ArrayAdapter<String> ratingSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ratingList);
		ratingSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ratingSpinner.setAdapter(ratingSpinnerAdapter);
	}
	
}
