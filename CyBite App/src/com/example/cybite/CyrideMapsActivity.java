package com.example.cybite;
import java.util.ArrayList;

import com.DatabaseAPI.Restaurant;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import android.app.Dialog;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

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
	        googleMap.clear();

	        // Initialize CyRide Routes
	        initializeRoutes();
	        
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
	        	drawRoutes(location);
	        }
	        //locationManager.requestLocationUpdates(provider, 20000, 0, locationListener);        
	    }
	}

	private void MoveToUser(Location location){
		Restaurant rest = (Restaurant) getIntent().getExtras().get("restaurant");
		googleMap.addMarker(new MarkerOptions()
	 	.position(new LatLng(rest.getLat(), rest.getLongitude()))
	 	.snippet(rest.getName() + "\na " + rest.getGenre() + " restaurant")
	 	.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
	    .title(rest.getName()));
		LatLng currentPosition = new LatLng(location.getLatitude(), location.getLongitude());
		//Move camera to that location
		CameraUpdate center = CameraUpdateFactory.newLatLng(currentPosition);
		googleMap.moveCamera(center);
		//Zoom in to location
		CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
		googleMap.animateCamera(zoom);
		}
	
	ArrayList<LatLng> route1Red = new ArrayList<LatLng>();
	PolylineOptions options1Red = new PolylineOptions();
	
	ArrayList<LatLng> route3Blue = new ArrayList<LatLng>();
	PolylineOptions options3Blue = new PolylineOptions();
	
	ArrayList<LatLng> route6Brown = new ArrayList<LatLng>();
	PolylineOptions options6Brown = new PolylineOptions();
	
	private void initializeRoutes() {
		/* Initialize each route */
		
		// 1 Red
		route1Red.add(new LatLng(42.011962, -93.671343));
		route1Red.add(new LatLng(42.011994, -93.678682));
		route1Red.add(new LatLng(42.012201, -93.681707));
		route1Red.add(new LatLng(42.014561, -93.680956));
		route1Red.add(new LatLng(42.014513, -93.678703));
		route1Red.add(new LatLng(42.022898, -93.678639));
		route1Red.add(new LatLng(42.022729, -93.655583));
		route1Red.add(new LatLng(42.025267, -93.655668));			
		route1Red.add(new LatLng(42.025338, -93.651248));
		route1Red.add(new LatLng(42.024446, -93.649596));
		route1Red.add(new LatLng(42.0238, -93.649338));
		route1Red.add(new LatLng(42.022804, -93.650175));
		route1Red.add(new LatLng(42.022796, -93.62021));
		route1Red.add(new LatLng(42.025825, -93.620135));
		route1Red.add(new LatLng(42.025833, -93.615392));			
		route1Red.add(new LatLng(42.026885, -93.61536));
		route1Red.add(new LatLng(42.026988, -93.610425));
		route1Red.add(new LatLng(42.042686, -93.610736));
		route1Red.add(new LatLng(42.043483, -93.611412));
		route1Red.add(new LatLng(42.044479, -93.613697));
		route1Red.add(new LatLng(42.045674, -93.614169));
		route1Red.add(new LatLng(42.049785, -93.614277));
		route1Red.add(new LatLng(42.050909, -93.615403));
		route1Red.add(new LatLng(42.051172, -93.621776));	
		route1Red.add(new LatLng(42.049344, -93.621846));
		route1Red.add(new LatLng(42.049368, -93.620596));
		route1Red.add(new LatLng(42.051192, -93.620548));
		options1Red.addAll(route1Red);
		options1Red.color(Color.RED);
		
		// 1A Red
		
		// 1B Red
		
		// 2 Green
		
		// 3 Blue
		route3Blue.add(new LatLng(42.020168, -93.616927));
		route3Blue.add(new LatLng(42.02012, -93.610275));
		route3Blue.add(new LatLng(42.017362, -93.610339));
		route3Blue.add(new LatLng(42.01741, -93.616841));
		route3Blue.add(new LatLng(42.020168, -93.616927));
		route3Blue.add(new LatLng(42.019339, -93.619502));
		route3Blue.add(new LatLng(42.019061, -93.627505));
		route3Blue.add(new LatLng(42.016383, -93.630788));
		route3Blue.add(new LatLng(42.016208, -93.639393));
		route3Blue.add(new LatLng(42.022671, -93.639479));
		route3Blue.add(new LatLng(42.02275, -93.650143));
		route3Blue.add(new LatLng(42.024073, -93.649242));
		route3Blue.add(new LatLng(42.025333, -93.651173));
		route3Blue.add(new LatLng(42.025333, -93.651881));
		route3Blue.add(new LatLng(42.028903, -93.651881));
		route3Blue.add(new LatLng(42.028871, -93.641474));
		route3Blue.add(new LatLng(42.030529, -93.641539));
		route3Blue.add(new LatLng(42.030545, -93.644607));
		route3Blue.add(new LatLng(42.038928, -93.644822));
		route3Blue.add(new LatLng(42.041573, -93.644822));
		route3Blue.add(new LatLng(42.041494, -93.643019));
		route3Blue.add(new LatLng(42.040458, -93.643706));
		route3Blue.add(new LatLng(42.039055, -93.643513));
		route3Blue.add(new LatLng(42.038928, -93.644822));
		route3Blue.add(new LatLng(42.039055, -93.643513));
		route3Blue.add(new LatLng(42.040458, -93.643706));
		route3Blue.add(new LatLng(42.041494, -93.643019));
		route3Blue.add(new LatLng(42.040777, -93.641002));
		route3Blue.add(new LatLng(42.043087, -93.641281));
		route3Blue.add(new LatLng(42.043454, -93.640487));
		route3Blue.add(new LatLng(42.044888, -93.640273));
		route3Blue.add(new LatLng(42.045222, -93.640745));
		route3Blue.add(new LatLng(42.045111, -93.642204));
		route3Blue.add(new LatLng(42.045446, -93.642247));
		route3Blue.add(new LatLng(42.045621, -93.625853));
		route3Blue.add(new LatLng(42.047676, -93.626025));
		route3Blue.add(new LatLng(42.048951, -93.627141));
		route3Blue.add(new LatLng(42.048951, -93.627162));
		route3Blue.add(new LatLng(42.05115, -93.623407));
		route3Blue.add(new LatLng(42.05397, -93.623428));
		route3Blue.add(new LatLng(42.053938, -93.620596));
		route3Blue.add(new LatLng(42.053938, -93.620499));
		route3Blue.add(new LatLng(42.049369, -93.620499));
		route3Blue.add(new LatLng(42.049369, -93.621808));
		route3Blue.add(new LatLng(42.05115, -93.623407));
		options3Blue.addAll(route3Blue);
		options3Blue.color(Color.BLUE);
			
		// 4 Gray

		// 4A Gray
			
		// 5 Yellow
		
		// 6 Brown
		route6Brown.add(new LatLng(41.998181, -93.63626));
		route6Brown.add(new LatLng(42.000733, -93.633599));
		route6Brown.add(new LatLng(42.001131, -93.634222));
		route6Brown.add(new LatLng(42.00102, -93.635852));
		route6Brown.add(new LatLng(42.000254, -93.637033));
		route6Brown.add(new LatLng(41.999058, -93.637161));
		route6Brown.add(new LatLng(41.998181, -93.63626));
		route6Brown.add(new LatLng(41.99756, -93.637354));
		route6Brown.add(new LatLng(41.997384, -93.639393));
		route6Brown.add(new LatLng(41.995391, -93.639393));
		route6Brown.add(new LatLng(41.995056, -93.639393));
		route6Brown.add(new LatLng(41.995056, -93.639758));
		route6Brown.add(new LatLng(41.995391, -93.639393));
		route6Brown.add(new LatLng(41.997384, -93.639393));
		route6Brown.add(new LatLng(42.00837, -93.639393));
		route6Brown.add(new LatLng(42.008466, -93.640809));
		route6Brown.add(new LatLng(42.009024, -93.642397));
		route6Brown.add(new LatLng(42.008801, -93.644607));
		route6Brown.add(new LatLng(42.009431, -93.645605));
		route6Brown.add(new LatLng(42.011304, -93.646667));
		route6Brown.add(new LatLng(42.01183, -93.648663));
		route6Brown.add(new LatLng(42.011854, -93.651656));
		route6Brown.add(new LatLng(42.016135, -93.65171));
		route6Brown.add(new LatLng(42.016135, -93.650057));
		route6Brown.add(new LatLng(42.02275, -93.650143));
		route6Brown.add(new LatLng(42.024073, -93.649242));
		route6Brown.add(new LatLng(42.025333, -93.651173));
		route6Brown.add(new LatLng(42.025333, -93.651881));
		route6Brown.add(new LatLng(42.028903, -93.651881));
		route6Brown.add(new LatLng(42.028871, -93.641474));
		route6Brown.add(new LatLng(42.030529, -93.641539));
		route6Brown.add(new LatLng(42.030545, -93.644607));
		route6Brown.add(new LatLng(42.048537, -93.64495));
		route6Brown.add(new LatLng(42.049557, -93.645444));
		route6Brown.add(new LatLng(42.050481, -93.64495));
		route6Brown.add(new LatLng(42.056264, -93.644907));
		route6Brown.add(new LatLng(42.056408, -93.634222));
		route6Brown.add(new LatLng(42.05585, -93.632655));
		route6Brown.add(new LatLng(42.056025, -93.620725));
		route6Brown.add(new LatLng(42.051182, -93.620596));
		route6Brown.add(new LatLng(42.049369, -93.620499));
		route6Brown.add(new LatLng(42.049369, -93.621808));
		route6Brown.add(new LatLng(42.05115, -93.623407));
		route6Brown.add(new LatLng(42.051182, -93.620596));
		options6Brown.addAll(route6Brown);
		options6Brown.color(Color.rgb(139,69,19));
		
		// 6A Brown

		// 6B Brown
				
		// 7 Purple
		
		// 8 Aqua

		// 10 Pink
		
		// 21 Cardinal
		
		// 22 Gold
		
		// 23 Orange
		
		// 24 Silver
		
	}
	
	private void drawRoutes(Location location)
	{
		ArrayList<String> chosenRoutes = getIntent().getStringArrayListExtra("CheckedBoxes");
		boolean closestRoute = false;
		for(int i = 0; i < chosenRoutes.size(); i++)
		{
			if(chosenRoutes.get(i).equals("1 Red"))
			{
				googleMap.addPolyline(options1Red);
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
				googleMap.addPolyline(options3Blue);
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
				googleMap.addPolyline(options6Brown);
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
			else if(chosenRoutes.get(i).equals("View Closest Route")) {
				closestRoute = true;
			}
		}
		if(closestRoute) {
			Restaurant rest = (Restaurant) getIntent().getExtras().get("restaurant");
		 	LatLng locationRest = new LatLng(rest.getLat(), rest.getLongitude());
		 	LatLng locationYou = new LatLng(location.getLatitude(), location.getLongitude());
		 	ArrayList<PolylineOptions> options = new ArrayList<PolylineOptions>();
		 	ArrayList<Double> shortestDist = new ArrayList<Double>();
		 	
			for(int i = 0; i < 18; i++) {
				double totalDist = Double.MAX_VALUE;
				PolylineOptions option = null;
				switch(i) {
				case 0:
					totalDist = findTotalDistance(locationRest, locationYou, route1Red, 0);
					option = options1Red;
					break;
				case 4:
					totalDist = findTotalDistance(locationRest, locationYou, route3Blue, 0);
					option = options3Blue;
					break;
				case 8:
					totalDist = findTotalDistance(locationRest, locationYou, route6Brown, 0);
					option = options6Brown;
					break;
				default:
					break;
				}
				int j = 0;
				while(j < shortestDist.size() && totalDist >= shortestDist.get(j)) {
					j++;
				}
				shortestDist.add(j, totalDist);
				options.add(j, option);
			}
			int j = 0;
			while(j < 1 && j < options.size()) {
				googleMap.addPolyline(options.get(j));
				j++;
			}
			
		}
		
		
	}
	
	private class DistancePoint {
		public LatLng intersectPoint;
		public double distance;
		public DistancePoint(double distance, LatLng intersect) {
			this.distance = distance;
			this.intersectPoint = intersect;
		}
	}
	
	// Direction of Route (int direction): -1 = backward | 1 = forward | 0 = both
	private double findTotalDistance(LatLng rest, LatLng you, ArrayList<LatLng> vertices, int direction) {
		DistancePoint shortestDistRest = new DistancePoint(Double.MAX_VALUE, null);
		DistancePoint shortestDistYou = new DistancePoint(Double.MAX_VALUE, null);
		int shortestI = 0, shortestJ = 0;
		
	    for(int i = 0; i < vertices.size()-1; i++) {
	    	DistancePoint val = LineToPointDistance2D(vertices.get(i), vertices.get(i+1), rest, true);//rayCastIntersect(rest, vertices.get(i), vertices.get(i+1), shortestDist);
	    	if(val.distance < shortestDistRest.distance) {
	    		shortestDistRest.distance = val.distance;
	    		shortestDistRest.intersectPoint = val.intersectPoint;
	    		shortestI = i;
	    	}
	    }
	    
	    for(int j = 0; j < vertices.size()-1; j++) {
	    	DistancePoint val = LineToPointDistance2D(vertices.get(j), vertices.get(j+1), you, true);//rayCastIntersect(rest, vertices.get(i), vertices.get(i+1), shortestDist);
	    	if(val.distance < shortestDistYou.distance) {
	    		shortestDistYou.distance = val.distance;
	    		shortestDistYou.intersectPoint = val.intersectPoint;
	    		shortestJ = j;
	    	}
	    }
	    double totalDist = 0;
	    if(shortestI <= shortestJ && direction != 1) {
	    	totalDist += 10*Distance(shortestDistRest.intersectPoint, vertices.get(shortestI+1));
	    	totalDist += 10*Distance(shortestDistYou.intersectPoint, vertices.get(shortestJ));
	    	while(shortestI < shortestJ) {
	    		totalDist += Distance(vertices.get(shortestI), vertices.get(shortestI+1));
	    		shortestI++;
	    	}
	    } else if(shortestJ <= shortestI && direction != -1){
	    	totalDist += 10*Distance(shortestDistRest.intersectPoint, vertices.get(shortestI));
	    	totalDist += 10*Distance(shortestDistYou.intersectPoint, vertices.get(shortestJ+1));
	    	while(shortestJ < shortestI) {
	    		totalDist += Distance(vertices.get(shortestJ), vertices.get(shortestJ+1));
	    		shortestJ++;
	    	}
	    } else {
	    	totalDist = 10*Distance(you, rest);
	    }
	    
		return totalDist;
	}
	
	//Compute the dot product AB . AC
	private double DotProduct(LatLng pointA, LatLng pointB, LatLng pointC)
	{
	    double[] AB = new double[2];
	    double[] BC = new double[2];
	    AB[0] = pointB.latitude - pointA.latitude;
	    AB[1] = pointB.longitude - pointA.longitude;
	    BC[0] = pointC.latitude - pointB.latitude;
	    BC[1] = pointC.longitude - pointB.longitude;
	    double dot = AB[0] * BC[0] + AB[1] * BC[1];
	    return dot;
	}
	//Compute the cross product AB x AC
	private double CrossProduct(LatLng pointA, LatLng pointB, LatLng pointC)
	{
	    double[] AB = new double[2];
	    double[] AC = new double[2];
	    AB[0] = pointB.latitude - pointA.latitude;
	    AB[1] = pointB.longitude - pointA.longitude;
	    AC[0] = pointC.latitude - pointA.latitude;
	    AC[1] = pointC.longitude - pointA.longitude;
	    double cross = AB[0] * AC[1] - AB[1] * AC[0];
	    return cross;
	}

	//Compute the distance from A to B
	private double Distance(LatLng pointA, LatLng pointB)
	{
	    double d1 = pointA.latitude - pointB.latitude;
	    double d2 = pointA.longitude - pointB.longitude;
	    return Math.sqrt(d1 * d1 + d2 * d2);
	}

	//Compute the distance from AB to C
	//if isSegment is true, AB is a segment, not a line.	
	private DistancePoint LineToPointDistance2D(LatLng pointA, LatLng pointB, LatLng pointC, 
		    boolean isSegment)
		{
		    double dist = CrossProduct(pointA, pointB, pointC) / Distance(pointA, pointB);
		    if (isSegment)
		    {
		        double dot1 = DotProduct(pointA, pointB, pointC);
		        if (dot1 > 0) 
		            return new DistancePoint(Distance(pointB, pointC), pointC);

		        double dot2 = DotProduct(pointB, pointA, pointC);
		        if (dot2 > 0) 
		            return new DistancePoint(Distance(pointA, pointC), pointC);
		    }
		    dist = Math.abs(dist);
		    double u = (((pointC.latitude-pointA.latitude)*(pointB.latitude-pointA.latitude))+((pointC.longitude-pointA.longitude)*(pointB.longitude-pointA.longitude)))/Math.pow(Math.abs(Distance(pointA, pointB)), 2);
		    double[] val = new double[2];
		    val[0] = pointA.latitude + u * (pointB.latitude - pointA.latitude);
		    val[1] = pointA.longitude + u * (pointB.longitude - pointA.longitude);
		    return new DistancePoint(Math.abs(dist), new LatLng(val[0], val[1]));
		}
}
