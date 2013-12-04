package com.example.cybite;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		//to nearbyMap activity
		Button goToNearbyActivity = (Button) findViewById(R.id.homeToNearbyMap);
		goToNearbyActivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(HomeActivity.this, NearByActivity.class);
				startActivity(i);
			}
		});
		
		//to restaurant
		Button goToRestaurantActivity = (Button) findViewById(R.id.homeToRestaurant);
		goToRestaurantActivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(HomeActivity.this, RestaurantActivity.class);
				startActivity(i);
			}
		});
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
