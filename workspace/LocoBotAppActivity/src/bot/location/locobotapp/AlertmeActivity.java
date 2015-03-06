package bot.location.locobotapp;



import java.util.ArrayList;


import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AlertmeActivity extends FragmentActivity {

    private EditText address;
	private EditText number;
	private EditText message;
	private Button save;
	private LocationManager locationManager;
	private String foradd;
	private ProgressDialog dialog;
	
	private IntentFilter filter;
	
	private double lat;
 	private double longt;
 	public String phoneno;
	public String formessage;
	private double clat;
	private double clog;
	Location location;
	private Button getcoordinate;
	Geocoder geocoder;
	 ArrayList <Double>slat = new ArrayList<Double>();
	    ArrayList <Double>slong = new ArrayList<Double>();
	    ArrayList <String>messagesave = new ArrayList<String>();
		int i=0;
		int n=0;
		private String temp;
 	 
	
 //	private static final long POINT_RADIUS = 100; // in Meters
//	private static final long PROX_ALERT_EXPIRATION = -1; 
//	private static final String POINT_LATITUDE_KEY = "POINT_LATITUDE_KEY";
//	private static final String POINT_LONGITUDE_KEY = "POINT_LONGITUDE_KEY";
	private static final String PROX_ALERT_INTENT1 = "com.example.alertme.ProximityAlert";
	
	@Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertme);
        
        address = (EditText) findViewById(R.id.address);
        
        message = (EditText) findViewById(R.id.message);
        save = (Button) findViewById(R.id.save); 
        getcoordinate = (Button) findViewById(R.id.getcord); 
        
            filter = new IntentFilter(PROX_ALERT_INTENT1); 
        
            
        getcoordinate.setOnClickListener(
            	new OnClickListener() {
            		public void onClick(View v)
            		{
            		
            			clat = location.getLatitude();
          	            clog = location.getLongitude(); 
          	        
          	        showToast("current location" + clat+","+clog);
          	        
            		        
            		       handleforwardGeocodeClick();
            			
            		
            		}
            	}
        );
             
        
             save.setOnClickListener(
            	new OnClickListener() {
            		public void onClick(View v)
            		{
            		      if(lat !=0 && longt !=0)
            		      {
            			showToast("coordinate of location" + lat+","+longt);	 
            			
            		//	handleforwardGeocodeClick();
            		//	saveProximityAlertPoint();
            			savepoint();
        				showpoint();
        				
        				registerIntents();
            		      }
            		      else
            		      {
            		    	  showToast("UNABLE TO TAKE LATLONG");
            		      }
            		
            		}
            	}
        );
             
             
             
             
             this.locationManager = 
            			(LocationManager) getSystemService(Context.LOCATION_SERVICE);
              location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
             
              final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

              if (!gpsEnabled) {
                  // Build an alert dialog here that requests that the user enable
                  // the location services, then when the user clicks the "OK" button,
                  // call enableLocationSettings()
                  new EnableGpsDialogFragment().show(getSupportFragmentManager(), "enableGpsDialog");
              }
          

         
            		
            		// Subscribe to the location manager's updates on the current location
            		this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, (long)30000, (float) 1.0, new LocationListener()
            			{
            				public void onLocationChanged(Location location) 
            				{
            				//	handleLocationChanged(location);
            					clat = location.getLatitude();
            					clog = location.getLongitude();
            					
            				}
            			
            				public void onProviderDisabled(String arg0) {
            					// TODO Auto-generated method stub
            					
            				}
            			
            				public void onProviderEnabled(String arg0) {
            					// TODO Auto-generated method stub
            					
            				}
            			
            				public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
            					// TODO Auto-generated method stub
            				}
            			});
            		
            		
    }
	
	private void handleforwardGeocodeClick()
	    {
	    	 foradd = address.getText().toString();
	    	 formessage = message.getText().toString();
	    	
	    	
	  //  Log.v(" forward message ", "no ="+ formessage+phoneno);
	   	
	    		ForwardGeocodeLookupTask task = new ForwardGeocodeLookupTask();
	    		 task.applicationContext = this;
	    	   	task.execute();	
	    		
	    	
	    	
	    }
	 
	 public void showToast(CharSequence message)
	    {
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(getApplicationContext(), message, duration);
			toast.show();
	    }
	 public class ForwardGeocodeLookupTask extends AsyncTask <Void, Void, Double>
	    {
	    	
	    	protected Context applicationContext;
			
			
		
		    @Override
	    	protected void onPreExecute()
	    	{
	    		dialog = ProgressDialog.show(applicationContext, "Please wait...reterive coordinate", 
	                    "Requesting Forward geocode lookup", true);
	    	dialog.setCancelable(false);
	    	dialog.setCanceledOnTouchOutside(false);
	    	
	    	}
	    	
			@Override
			protected Double doInBackground(Void... params) 
			{
				
				
				if (foradd != null)
				{
					geocoder = new Geocoder();
					 geocoder.forwardGeocode(foradd);
				}
				    lat=geocoder.GeoLattitude;
				    longt=geocoder.GeoLongitude;
				    
				    Log.v("background", "latlong"+ lat+","+longt);
				      
				   	  return  lat;
			}
			
			@Override
			protected void onPostExecute(Double latt)
			   {
				
				if(latt!= 0.0)
				     {
				//	  Log.d("Main server result", result);
					 // Main.this.txtLatitude.setText(String.valueOf(lat));
					 // Main.this.txtLongitude.setText(String.valueOf(longt));
					  dialog.dismiss();
					  }
				else {
					
					showToast("can't able to get latlong");
					dialog.dismiss();
				}
				}
	    }
		
	
	
	 private void savepoint()
     
     {
   	  
		        	slat.add(lat);
		        	slong.add(longt);
		        	messagesave.add(formessage);
		        	
		        	Log.d("lat" , String.valueOf(slat));
					Log.d("long", String.valueOf(slong));
					Log.d("long", messagesave.get(i)); 
		    	i=i+1;
     }    
     
     private void showpoint()
     {
   	  for(int j=0;j<slat.size();j++)
   	  {
   		  Log.d("latlong"+ slat.get(j)+","+slong.get(j), "value of"+j+","+"message"+ messagesave.get(j) );	 
   		  
   		  
   	  }
     }
     
     private void registerIntents() {
     	for(int k = n; k < slat.size(); k++) {
     	//	LatLonPair latLon = mPositions.get(i);
     		Log.d("value of k ", String.valueOf(k));
     		
     		temp = messagesave.get(k); 
     		setProximityAlert(slat.get(k), 
     				slong.get(k), 
     				k+1, 
     				k,temp);
     	}
     	
     	n++;
     }
     
     
     private void setProximityAlert(double lat, double lon, final long eventID, int requestCode, String m)
     {
     	// 100 meter radius
     	float radius = 500f;
     	
     	// Expiration is 10 Minutes (10mins * 60secs * 1000milliSecs)
     	long expiration = -1;
     	
  //   	LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
   //  	locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
    //             0, this);
     	
     	Log.d("inside setpro", lat+","+lon);
     	
     	Intent intent = new Intent(PROX_ALERT_INTENT1);
     	intent.putExtra(ProximityIntentReceiver1.EVENT_ID_INTENT_EXTRA, eventID);
     	intent.putExtra(ProximityIntentReceiver1.EVENT_ID_INTENT_MESSAGE_EXTRA, m);
     	PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
     	
     	locationManager.addProximityAlert(lat, lon, radius, expiration, pendingIntent);
    //	IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);  
      registerReceiver(new ProximityIntentReceiver1(), filter);
       
       Log.d("alert added","latlong"+ lat+","+lon+","+eventID);
       Toast.makeText(getApplicationContext(),"Alert Added"+ eventID ,Toast.LENGTH_SHORT).show(); 
     }
   
     @SuppressLint("ValidFragment")
 	private class EnableGpsDialogFragment extends DialogFragment {

         @Override
         public Dialog onCreateDialog(Bundle savedInstanceState) {
             return new AlertDialog.Builder(getActivity())
                     .setTitle(R.string.enable_gps)
                     .setMessage(R.string.enable_gps_dialog)
                     .setPositiveButton(R.string.enable_gps, new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             enableLocationSettings();
                         }
                     })
                     .create();
         }
     }
   
     // Method to launch Settings
     private void enableLocationSettings() {
         Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
         startActivity(settingsIntent);
     }
}
