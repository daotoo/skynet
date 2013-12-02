package com.example.cybite;

import java.text.DecimalFormat;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.DatabaseAPI.DatabaseAPI;
import com.DatabaseAPI.Restaurant;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RestaurantInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant_info);
	
		Intent i = getIntent();
		final Restaurant r = (Restaurant) i.getExtras().get("restaurant");
		
		Button goToMaps= (Button) findViewById(R.id.restInfoToMaps);
		goToMaps.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(RestaurantInfoActivity.this, CyrideMapsActivity.class);
				startActivity(i);
			}
		});
		
		
		double ratValue = r.getRating();
		String strRat = new DecimalFormat("#.#").format(ratValue);
	
		TextView name = (TextView) findViewById(R.id.restaurantName);
		TextView genre = (TextView) findViewById(R.id.restaurantGenre);
		TextView cost = (TextView) findViewById(R.id.restaurantCost);
		TextView rating = (TextView) findViewById(R.id.restaurantRating);
		TextView review = (TextView) findViewById(R.id.restaurantReview);
		TextView address = (TextView) findViewById(R.id.restaurantName);
		
		name.setText(r.getName());
		genre.setText(r.getGenre());
		cost.setText(r.getCost());
		rating.setText(strRat);
		review.setText(r.getReview());
		address.setText(r.getAddress());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.restaurant_info, menu);
		return true;
	}

}
