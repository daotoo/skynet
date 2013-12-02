package com.example.cybite;
import java.util.ArrayList;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class CyRideMapsActivity extends FragmentActivity {

	GoogleMap googleMap;
	
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_cyridemaps);
	    // Getting Google Play availability status
	    int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

	    // Showing status
	    if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available

	        int requestCode = 10;
	        Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
	        dialog.show();

	    }else { // Google Play Services are available

	        // Getting reference to the SupportMapFragment of activity_main.xml
	        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

	        // Getting GoogleMap object from the fragment
	        googleMap = fm.getMap();

	        // Enabling MyLocation Layer of Google Map
	        googleMap.setMyLocationEnabled(true);

	        // Getting LocationManager object from System Service LOCATION_SERVICE
	        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

	        // Creating a criteria object to retrieve provider
	        Criteria criteria = new Criteria();

	        // Getting the name of the best provider
	        String provider = locationManager.getBestProvider(criteria, true);

	        // Getting Current Location
	        Location location = locationManager.getLastKnownLocation(provider);

	        LocationListener locationListener = new LocationListener() {
	          public void onLocationChanged(Location location) {
	          // redraw the marker when get location update.
	        	  drawMarkerAndMove(location);
	        }

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub
				
			}};

	        if(location!=null){
	           //PLACE THE INITIAL MARKER              
	        	drawMarkerAndMove(location);
	        	drawRoutes();
	        }
	        locationManager.requestLocationUpdates(provider, 20000, 0, locationListener);        
	    }
	}

	private void drawMarkerAndMove(Location location){
		//Create pin on current location
		//googleMap.clear();
		LatLng currentPosition = new LatLng(location.getLatitude(), location.getLongitude());
		googleMap.addMarker(new MarkerOptions()
		 	.position(currentPosition)
		 	.snippet("Lat:" + location.getLatitude() + "Lng:"+ location.getLongitude())
		 	.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
		    .title("ME"));
		//Move camera to that location
		CameraUpdate center = CameraUpdateFactory.newLatLng(currentPosition);
		googleMap.moveCamera(center);
		//Zoom in to location
		CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
		googleMap.animateCamera(zoom);
		}
	
	private void drawRoutes()
	{
		ArrayList<String> chosenRoutes = getIntent().getStringArrayListExtra("CheckedBoxes");
		for(int i = 0; i < chosenRoutes.size(); i++)
		{
			ArrayList<LatLng> route = new ArrayList<LatLng>();
			PolylineOptions options = new PolylineOptions();
			options.width(5);
			if(chosenRoutes.get(i).equals("1 Red"))
			{
				route.add(new LatLng(42.011962, -93.671343));
				route.add(new LatLng(42.011994, -93.678682));
				route.add(new LatLng(42.012201, -93.681707));
				route.add(new LatLng(42.014561, -93.680956));
				route.add(new LatLng(42.014513, -93.678703));
				route.add(new LatLng(42.022898, -93.678639));
				route.add(new LatLng(42.022729, -93.655583));
				route.add(new LatLng(42.025267, -93.655668));			
				route.add(new LatLng(42.025338, -93.651248));
				route.add(new LatLng(42.024446, -93.649596));
				route.add(new LatLng(42.0238, -93.649338));
				route.add(new LatLng(42.022804, -93.650175));
				route.add(new LatLng(42.022796, -93.62021));
				route.add(new LatLng(42.025825, -93.620135));
				route.add(new LatLng(42.025833, -93.615392));			
				route.add(new LatLng(42.026885, -93.61536));
				route.add(new LatLng(42.026988, -93.610425));
				route.add(new LatLng(42.042686, -93.610736));
				route.add(new LatLng(42.043483, -93.611412));
				route.add(new LatLng(42.044479, -93.613697));
				route.add(new LatLng(42.045674, -93.614169));
				route.add(new LatLng(42.049785, -93.614277));
				route.add(new LatLng(42.050909, -93.615403));
				route.add(new LatLng(42.051172, -93.621776));	
				route.add(new LatLng(42.049344, -93.621846));
				route.add(new LatLng(42.049368, -93.620596));
				route.add(new LatLng(42.051192, -93.620548));
				options.addAll(route);
				options.color(Color.RED);
			}
			else if(chosenRoutes.get(i).equals("1A Red"))
			{
				
			}
			else if(chosenRoutes.get(i).equals("1B Red"))
			{
				
			}
			else if(chosenRoutes.get(i).equals("2 Green"))
			{
				
			}
			else if(chosenRoutes.get(i).equals("3 Blue"))
			{
				
			}
			else if(chosenRoutes.get(i).equals("4 Gray"))
			{
				
			}
			else if(chosenRoutes.get(i).equals("4A Gray"))
			{
				
			}
			else if(chosenRoutes.get(i).equals("5 Yellow"))
			{
				
			}
			else if(chosenRoutes.get(i).equals("6 Brown"))
			{
				
			}
			else if(chosenRoutes.get(i).equals("6A Brown"))
			{
				
			}
			else if(chosenRoutes.get(i).equals("6B Brown"))
			{
				
			}
			else if(chosenRoutes.get(i).equals("7 Purple"))
			{
				
			}
			else if(chosenRoutes.get(i).equals("8 Aqua"))
			{
				
			}
			else if(chosenRoutes.get(i).equals("10 Pink"))
			{
				
			}
			else if(chosenRoutes.get(i).equals("21 Cardinal"))
			{
				
			}
			else if(chosenRoutes.get(i).equals("22 Gold"))
			{
				
			}
			else if(chosenRoutes.get(i).equals("23 Orange"))
			{
				
			}
			else if(chosenRoutes.get(i).equals("24 Silver"))
			{
				
			}
			Polyline line = googleMap.addPolyline(options);
		}	
	}
	
	
	
	
	
	
	
}
