package bot.location.locobotapp;


import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class GoogleApi extends Activity {

	Boolean isInternetPresent = false;
	InternetConnection cd;
	AlertManager alert = new AlertManager();
	GooglePlaces googlePlaces;
	PlacesList nearPlaces;
	GpsTracker gps;
	Button btnShowOnMap;
	ProgressDialog pDialog;
	ListView lv;
	ArrayList<HashMap<String, String>> placesListItems = new ArrayList<HashMap<String,String>>();
	public static String KEY_REFERENCE = "reference"; // id of the place
	public static String KEY_NAME = "name"; // name of the place
	public static String KEY_VICINITY = "vicinity"; // Place area name
	private String types;
	private double latt;
	private double longt;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.api_google);

		Intent intent1 =this.getIntent();
		Bundle bundle=intent1.getExtras();
	//	latt=bundle.getDouble("lattitude");
	//	longt=bundle.getDouble("longitude");
		types = bundle.getString("type");
		Log.d("GoogleApi", types);

		cd = new InternetConnection(getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();
		if (!isInternetPresent) {
			alert.showAlertDialog(GoogleApi.this, "Internet Connection Error",
					"Please connect to working Internet connection", false);
			return;
		}
		 gps = new GpsTracker(this);

			// check if GPS location can get
			if (gps.canGetLocation()) {
				latt=gps.getLatitude();
				longt=gps.getLongitude();
				//title.setText("Current Location:");
				//lattxt.setText(String.valueOf(lat)+",");
				//lontxt.setText(String.valueOf(longt));
				Log.d("Your Location", "latitude:" + latt + ", longitude: " + longt);
			} else {
				// Can't get user's current location
				alert.showAlertDialog(GoogleApi.this, "GPS Status",
						"Couldn't get location information. Please enable GPS",
						false);
				// stop executing code by return
				return;
			}
		
		lv = (ListView) findViewById(R.id.list);
		btnShowOnMap = (Button) findViewById(R.id.btn_show_map);
		new LoadPlaces().execute();
		
		btnShowOnMap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(),PlacesMapActivity.class);
				i.putExtra("user_latitude", Double.toString(gps.getLatitude()));
				i.putExtra("user_longitude", Double.toString(gps.getLongitude()));
				i.putExtra("near_places", nearPlaces);
				startActivity(i);
			}
		});
		
		lv.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
    
                String reference = ((TextView) view.findViewById(R.id.reference)).getText().toString();
                Intent in = new Intent(getApplicationContext(),
                        SinglePlaceActivity.class);
                in.putExtra(KEY_REFERENCE, reference);
                startActivity(in);
            }
        });
	}
	class LoadPlaces extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(GoogleApi.this);
			pDialog.setMessage(Html.fromHtml("<b>Search</b><br/>Loading Places..."));
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			googlePlaces = new GooglePlaces();
			try {
				double radius = 1000; // 1000 meters 
				nearPlaces = googlePlaces.search(latt,longt,radius,types);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String file_url) {
	
			pDialog.dismiss();
			runOnUiThread(new Runnable() {
				public void run() {
					String status = nearPlaces.status;
					
	
					if(status.equals("OK")){
	
						if (nearPlaces.results != null) {
	
							for (Place p : nearPlaces.results) {
								HashMap<String, String> map = new HashMap<String, String>();
								
								map.put(KEY_REFERENCE, p.reference);
								
								// Place name
								map.put(KEY_NAME, p.name);
	
								placesListItems.add(map);
							}
	
							ListAdapter adapter = new SimpleAdapter(GoogleApi.this, placesListItems,
					                R.layout.list_item,
					                new String[] { KEY_REFERENCE, KEY_NAME}, new int[] {
					                        R.id.reference, R.id.name });
						
							lv.setAdapter(adapter);
						}
					}
					else if(status.equals("ZERO_RESULTS")){
	
						alert.showAlertDialog(GoogleApi.this, "Near Places",
								"Sorry no places found. Try to change the types of places",
								false);
					}
					else if(status.equals("UNKNOWN_ERROR"))
					{
						alert.showAlertDialog(GoogleApi.this, "Places Error",
								"Sorry unknown error occured.",
								false);
					}
					else if(status.equals("OVER_QUERY_LIMIT"))
					{
						alert.showAlertDialog(GoogleApi.this, "Places Error",
								"Sorry query limit to google places is reached",
								false);
					}
					else if(status.equals("REQUEST_DENIED"))
					{
						alert.showAlertDialog(GoogleApi.this, "Places Error",
								"Sorry error occured. Request is denied",
								false);
					}
					else if(status.equals("INVALID_REQUEST"))
					{
						alert.showAlertDialog(GoogleApi.this, "Places Error",
								"Sorry error occured. Invalid Request",
								false);
					}
					else
					{
						alert.showAlertDialog(GoogleApi.this, "Places Error",
								"Sorry error occured.",
								false);
					}
				}
			});

		}

	}

}
