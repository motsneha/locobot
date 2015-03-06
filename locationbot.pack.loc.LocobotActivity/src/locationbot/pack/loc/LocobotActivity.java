package locationbot.pack.loc;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class LocobotActivity extends Activity {
    /** Called when the activity is first created. */
	private LocationManager locationManager;
	private LocationListener locationListener;
	private double lat;
	private double longt;
	private TextView lattext;
	private TextView longttext;
	private String slat,slong;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        lattext=(TextView)findViewById(R.id.LattextView);
		longttext=(TextView)findViewById(R.id.LongttextView);
        
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        
		locationListener = new LocationListener() {
			   
			public void onLocationChanged(Location location) {
			      // Called when a new location is found by the network location provider.
			      makeUseOfNewLocation(location);
			      locationManager.removeUpdates(locationListener);
			    }
			 private void makeUseOfNewLocation(Location location) {
					lat=location.getLatitude();
					longt=location.getLongitude();
					slat=String.valueOf(lat);
					slong=String.valueOf(longt);
					lattext.setText(slat);
					longttext.setText(slong);
					}

					public void onStatusChanged(String provider, int status, Bundle extras) {}

				    public void onProviderEnabled(String provider) {}

				    public void onProviderDisabled(String provider) {}
				  };
				  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        
        }

	
    public boolean onCreateOptionsMenu(Menu menu) { 
    super.onCreateOptionsMenu(menu);
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.menu.startmenu_menu, menu );
    return true;
    }
    public boolean onMenuItemSelected(int featureId, MenuItem item) { 
    	  if (item.getItemId() == R.id.startmenu_menu_search) 
    	  { 
    		  Intent intent = new Intent(this,SearchOptionsActivity.class);
    		  startActivity(intent);
    		  return true;
    	  }
    	  if (item.getItemId() == R.id.startmenu_menu_myLocation) 
    	  { 
    		  Intent intent = new Intent(this,MyLocationActivity.class);
    		  Bundle bundle=new Bundle();
  			  bundle.putDouble("latparam",lat);
  			  bundle.putDouble("longparam", longt);
  			  intent.putExtras(bundle);
  			  startActivity(intent);
    		  return true;
    	  }
    	  if (item.getItemId() == R.id.startmenu_menu_exit) 
    	  { 
    		  finish();
    		  return true;
    	  }
    	  return super.onOptionsItemSelected(item);
}
    
}