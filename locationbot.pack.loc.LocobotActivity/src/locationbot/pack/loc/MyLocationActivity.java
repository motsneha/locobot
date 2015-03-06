package locationbot.pack.loc;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MyLocationActivity extends Activity{
	private GoogleMap mMap;
	private Double lat;
	private Double longt;
	private String slat;
	private String slong;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylocation_map);
        
        Intent receivedIntent=this.getIntent(); 
        Bundle bundle= receivedIntent.getExtras();
        
        lat=bundle.getDouble("latparam");
        longt=bundle.getDouble("longparam");
                      
        mMap=((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap(); 
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        
        slat=String.valueOf(lat);
        slong=String.valueOf(longt);
        
        final LatLng myLocation = new LatLng(lat,longt);
        Marker myMarker =mMap.addMarker(new MarkerOptions()
        					.position(myLocation)
        					.title("Location")
        					.snippet("My Location:Lat"+slat+",Long"+slong)
        					.icon(BitmapDescriptorFactory.fromResource(R.drawable.markericon) ));
        myMarker.showInfoWindow();
	}
}
