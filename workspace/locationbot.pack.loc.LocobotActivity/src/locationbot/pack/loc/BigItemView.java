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

public class BigItemView extends Activity{
	private GoogleMap itemMap;
	private Double item_lat;
	private Double item_longt;
	private String item_slat;
	private String item_slong;
	private String name;
	private String address;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_linear_layout);
        
        Intent receivedIntent=this.getIntent(); 
        Bundle bundle= receivedIntent.getExtras();
         name=bundle.getString("param1");
         address=bundle.getString("param2");
        
        
         
        itemMap=((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap(); 
        itemMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        
        item_slat=String.valueOf(item_lat);
        item_slong=String.valueOf(item_longt);
        
        final LatLng myLocation = new LatLng(item_lat,item_longt);
        Marker myMarker =itemMap.addMarker(new MarkerOptions()
        					.position(myLocation)
        					.title("Location")
        					.snippet("Name:"+name+"\nAddress"+address+"\nLat"+item_slat+",Long"+item_slong)
        					.icon(BitmapDescriptorFactory.fromResource(R.drawable.markericon) ));
        myMarker.showInfoWindow();
        
                       
    }
}
