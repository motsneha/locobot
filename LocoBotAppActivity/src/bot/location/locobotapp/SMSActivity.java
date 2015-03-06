package bot.location.locobotapp;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMSActivity extends FragmentActivity {

    private EditText address;
	private EditText number;
	private EditText message;
	private Button save;
	private LocationManager locationManager;
	private String foradd;
	private ProgressDialog dialog; 
	
	private double lat;
 	private double longt;
 	public String phoneno;
	public String formessage;
	private double clat;
	private double clog;
	Location location;
	private Button getcoordinate;
	Geocoder geocoder;
 	 
	
 	private static final long POINT_RADIUS = 500; // in Meters
	private static final long PROX_ALERT_EXPIRATION = -1; 
	private static final String POINT_LATITUDE_KEY = "POINT_LATITUDE_KEY";
	private static final String POINT_LONGITUDE_KEY = "POINT_LONGITUDE_KEY";
	private static final String PROX_ALERT_INTENT = "com.example.smsalert.SMSProximityAlert";
	
	@Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        
        address = (EditText) findViewById(R.id.address);
        number = (EditText) findViewById(R.id.number);
        message = (EditText) findViewById(R.id.message);
        save = (Button) findViewById(R.id.savebutton); 
        getcoordinate = (Button) findViewById(R.id.getcoordinate); 
        
       
        
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
            			saveProximityAlertPoint();
            		      }
            		      else
            		      {
            		    	  showToast("UNABLE TO GET LATLONG");
            		      }
            		
            		}
            	}
        );
             
             
             this.locationManager = 
            			(LocationManager) getSystemService(Context.LOCATION_SERVICE);
             
            final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            if (!gpsEnabled) {
                // Build an alert dialog here that requests that the user enable
                // the location services, then when the user clicks the "OK" button,
                // call enableLocationSettings()
                new EnableGpsDialogFragment().show(getSupportFragmentManager(), "enableGpsDialog");
            }
              location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
             
            		
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
	    	 phoneno = number.getText().toString();
	    	
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
					
					showToast("can't able to find latlong");
					dialog.dismiss();
				}
				}
	    }
		
	 private void saveProximityAlertPoint(){
		 
		 Log.v("save proxi latlon", "latlong"+ lat+","+longt);
		
	   saveCoordinatesInPreferences((float)lat,(float)longt);
     	 addProximityAlert(lat,longt,formessage,phoneno);
		
		
	 }
	 
	 private void addProximityAlert(double latitude, double longitude, String mess, String pn) {
			
	        Intent intent1 = new Intent(PROX_ALERT_INTENT);
	      intent1.putExtra(ProximityIntentReceiver.EVENT_ID_INTENT_SMSMESSAGE , mess);
            intent1.putExtra( ProximityIntentReceiver.EVENT_ID_INTENT_PHONENO , pn);
	        
      PendingIntent proximityIntent = PendingIntent.getBroadcast(this, 0, intent1, PendingIntent.FLAG_CANCEL_CURRENT);
	        
	        Log.v("Inside pro","message"+mess+","+ pn );  
	        
	        locationManager.addProximityAlert(
	    		latitude,longitude,POINT_RADIUS ,PROX_ALERT_EXPIRATION,proximityIntent);
	        
	       IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);  
	       registerReceiver(new ProximityIntentReceiver(), filter);
	       
	       // dialog box
	   	Toast.makeText(getApplicationContext(),"Alert set for message ",Toast.LENGTH_SHORT).show();
	       
		}

	private void saveCoordinatesInPreferences(float latitude, float longitude) {
		SharedPreferences prefs = this.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE);
		SharedPreferences.Editor prefsEditor = prefs.edit();
		prefsEditor.putFloat(POINT_LATITUDE_KEY, latitude);
		prefsEditor.putFloat(POINT_LONGITUDE_KEY, longitude);
//		prefsEditor.putString(POINT_PHONENO_KEY, phoneno);
//		prefsEditor.putString(POINT_MESSAGE_KEY, message);
		prefsEditor.commit();
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
	
	 private void enableLocationSettings() {
	        Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	        startActivity(settingsIntent);
	    }
}
