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
	    if(status!=ConnectionResult.SUCCESS)
	    { // Google Play Services are not available

	        int requestCode = 10;
	        Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
	        dialog.show();

	    }
	    else { // Google Play Services are available

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
	        	  MoveToUser(location);
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
	        	MoveToUser(location);
	        	drawRoutes();
	        }
	        locationManager.requestLocationUpdates(provider, 20000, 0, locationListener);        
	    }
	}

	private void MoveToUser(Location location){
		//Create pin on current location
		//googleMap.clear();
		LatLng currentPosition = new LatLng(location.getLatitude(), location.getLongitude());
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
				route.add(new LatLng(42.020168, -93.616927));
				route.add(new LatLng(42.02012, -93.610275));
				route.add(new LatLng(42.017362, -93.610339));
				route.add(new LatLng(42.01741, -93.616841));
				route.add(new LatLng(42.020168, -93.616927));
				route.add(new LatLng(42.019339, -93.619502));
				route.add(new LatLng(42.019061, -93.627505));
				route.add(new LatLng(42.016383, -93.630788));
				route.add(new LatLng(42.016208, -93.639393));
				route.add(new LatLng(42.022671, -93.639479));
				route.add(new LatLng(42.02275, -93.650143));
				route.add(new LatLng(42.024073, -93.649242));
				route.add(new LatLng(42.025333, -93.651173));
				route.add(new LatLng(42.025333, -93.651881));
				route.add(new LatLng(42.028903, -93.651881));
				route.add(new LatLng(42.028871, -93.641474));
				route.add(new LatLng(42.030529, -93.641539));
				route.add(new LatLng(42.030545, -93.644607));
				route.add(new LatLng(42.038928, -93.644822));
				route.add(new LatLng(42.041573, -93.644822));
				route.add(new LatLng(42.041494, -93.643019));
				route.add(new LatLng(42.040458, -93.643706));
				route.add(new LatLng(42.039055, -93.643513));
				route.add(new LatLng(42.038928, -93.644822));
				route.add(new LatLng(42.039055, -93.643513));
				route.add(new LatLng(42.040458, -93.643706));
				route.add(new LatLng(42.041494, -93.643019));
				route.add(new LatLng(42.040777, -93.641002));
				route.add(new LatLng(42.043087, -93.641281));
				route.add(new LatLng(42.043454, -93.640487));
				route.add(new LatLng(42.044888, -93.640273));
				route.add(new LatLng(42.045222, -93.640745));
				route.add(new LatLng(42.045111, -93.642204));
				route.add(new LatLng(42.045446, -93.642247));
				route.add(new LatLng(42.045621, -93.625853));
				route.add(new LatLng(42.047676, -93.626025));
				route.add(new LatLng(42.048951, -93.627141));
				route.add(new LatLng(42.048951, -93.627162));
				route.add(new LatLng(42.05115, -93.623407));
				route.add(new LatLng(42.05397, -93.623428));
				route.add(new LatLng(42.053938, -93.620596));
				route.add(new LatLng(42.053938, -93.620499));
				route.add(new LatLng(42.049369, -93.620499));
				route.add(new LatLng(42.049369, -93.621808));
				route.add(new LatLng(42.05115, -93.623407));
				options.addAll(route);
				options.color(Color.BLUE);
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
				route.add(new LatLng(41.998181, -93.63626));
				route.add(new LatLng(42.000733, -93.633599));
				route.add(new LatLng(42.001131, -93.634222));
				route.add(new LatLng(42.00102, -93.635852));
				route.add(new LatLng(42.000254, -93.637033));
				route.add(new LatLng(41.999058, -93.637161));
				route.add(new LatLng(41.998181, -93.63626));
				route.add(new LatLng(41.99756, -93.637354));
				route.add(new LatLng(41.997384, -93.639393));
				route.add(new LatLng(41.995391, -93.639393));
				route.add(new LatLng(41.995056, -93.639393));
				route.add(new LatLng(41.995056, -93.639758));
				route.add(new LatLng(41.995391, -93.639393));
				route.add(new LatLng(41.997384, -93.639393));
				route.add(new LatLng(42.00837, -93.639393));
				route.add(new LatLng(42.008466, -93.640809));
				route.add(new LatLng(42.009024, -93.642397));
				route.add(new LatLng(42.008801, -93.644607));
				route.add(new LatLng(42.009431, -93.645605));
				route.add(new LatLng(42.011304, -93.646667));
				route.add(new LatLng(42.01183, -93.648663));
				route.add(new LatLng(42.011854, -93.651656));
				route.add(new LatLng(42.016135, -93.65171));
				route.add(new LatLng(42.016135, -93.650057));
				route.add(new LatLng(42.02275, -93.650143));
				route.add(new LatLng(42.024073, -93.649242));
				route.add(new LatLng(42.025333, -93.651173));
				route.add(new LatLng(42.025333, -93.651881));
				route.add(new LatLng(42.028903, -93.651881));
				route.add(new LatLng(42.028871, -93.641474));
				route.add(new LatLng(42.030529, -93.641539));
				route.add(new LatLng(42.030545, -93.644607));
				route.add(new LatLng(42.048537, -93.64495));
				route.add(new LatLng(42.049557, -93.645444));
				route.add(new LatLng(42.050481, -93.64495));
				route.add(new LatLng(42.056264, -93.644907));
				route.add(new LatLng(42.056408, -93.634222));
				route.add(new LatLng(42.05585, -93.632655));
				route.add(new LatLng(42.056025, -93.620725));
				route.add(new LatLng(42.051182, -93.620596));
				route.add(new LatLng(42.049369, -93.620499));
				route.add(new LatLng(42.049369, -93.621808));
				route.add(new LatLng(42.05115, -93.623407));
				route.add(new LatLng(42.051182, -93.620596));
				options.addAll(route);
				options.color(Color.rgb(139,69,19));
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
			googleMap.addPolyline(options);
		}	
	}
	
	
	
	
	
	
	
}
