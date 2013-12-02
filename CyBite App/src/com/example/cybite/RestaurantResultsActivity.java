package com.example.cybite;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.DatabaseAPI.Restaurant;

public class RestaurantResultsActivity extends Activity{

	ListView resultsListView;
	ArrayList<String> listItems;
	ArrayList<Restaurant> searchResultsList;
	ArrayAdapter<String> adapter;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant_results);
		listItems = new ArrayList<String>();
		Intent i = getIntent();
		searchResultsList = (ArrayList<Restaurant>) i.getExtras().get("searchResultsList");
		for(int j = 0; j < searchResultsList.size(); j++){
			Restaurant r = searchResultsList.get(j);
			String name = r.getName();
			listItems.add(name);
		}
		
		if(listItems.size() == 0)
			listItems.add("No search results found");
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
		resultsListView = (ListView) findViewById(R.id.resultsListView);
		resultsListView.setAdapter(adapter);
		resultsListView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String str = (String) resultsListView.getItemAtPosition(position);
				if(!str.equals("No search results found")){
					Restaurant r = searchResultsList.get(position);
					Intent i = new Intent(RestaurantResultsActivity.this, RestaurantInfoActivity.class);
					i.putExtra("restaurant", r);
					startActivity(i);
				}
			}
		});
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.restaurant_results, menu);
		return true;
	}

}
