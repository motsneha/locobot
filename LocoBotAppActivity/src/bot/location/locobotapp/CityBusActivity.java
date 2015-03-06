package bot.location.locobotapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class CityBusActivity extends Activity implements OnItemSelectedListener,OnClickListener{
	private String destItem;
	private static final String SERVICE_URL ="http://env-3776051.jelastic.servint.net/locobotb/city";//"http://10.0.2.2:8080/com.locobot.busservice/city";//http://env-3776051.jelastic.servint.net/locobotb/city/down/badnera
	private String service_url_add;
	private ProgressDialog dialog;
	private Button searchButton;
	private String direction="up";
	private String server_result;
	private String temp_url;
	private AlertManager alert=new AlertManager();
	private InternetConnection cd;
	Boolean isInternetPresent = false;
	 
	private OnClickListener radio_listener = new OnClickListener() {
		    public void onClick(View view) {
		        // Perform action on clicks
		        RadioButton rb = (RadioButton) view;
		       if(rb.getId()==R.id.upRadioButton)
		       {
		    	   direction="up";
		    	   Log.d("CityBusActivity",direction);
		       }
		       if(rb.getId()==R.id.downRadioButton)
		       {
		    	   direction="down";
		    	   Log.d("CityBusActivity",direction);
		       } 
		       
		    }
		};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_bus);
	
		searchButton=(Button)findViewById(R.id.busSearch);
	    searchButton.setOnClickListener(this);
        dialog=new ProgressDialog(this);
        cd = new InternetConnection(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
		if (!isInternetPresent) {
			// Internet Connection is not present
			alert.showAlertDialog(CityBusActivity.this, "Internet Connection Error",
					"Please connect to working Internet connection", false);
			// stop executing code by return
			return;
		}

        
        final RadioButton upradio = (RadioButton) findViewById(R.id.upRadioButton);
        final RadioButton downradio = (RadioButton) findViewById(R.id.downRadioButton);
        upradio.setOnClickListener(radio_listener);
        downradio.setOnClickListener(radio_listener);
  
        
        Spinner destinSpinner = (Spinner)findViewById(R.id.destinSpinner);
        ArrayAdapter<CharSequence> destAdapt = ArrayAdapter.createFromResource(this, R.array.destinations, R.layout.spinner_item);
        //destAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destinSpinner.setAdapter(destAdapt);
        destinSpinner.setOnItemSelectedListener(this);
  
	
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view.getId()==R.id.busSearch)
		{
			
			if(direction!=null && destItem!=null)
			{
				service_url_add=SERVICE_URL+"/"+direction+"/"+destItem;
				Log.d("CityBusActivity " ,service_url_add);
				
				try {
					temp_url=new String(service_url_add.trim().replace(" ", "%20"));
					Log.d("CityBusActivity " ,temp_url);
				}catch (Exception e) 
				{
					// TODO Auto-generated catch block
			    	Log.d("CityBusActivity ",service_url_add);
					e.printStackTrace();
				}
				(new LongRunningGetIO(temp_url)).execute();
			}
			else
			{
				if(direction==null)
					Toast.makeText(this, "Select direction up/down", Toast.LENGTH_SHORT).show();
				if(destItem==null)
					Toast.makeText(this, "Select destination", Toast.LENGTH_SHORT).show();
					
			}
			}
		}
		
	

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
		// TODO Auto-generated method stub
		     Spinner spin2 = (Spinner)parent;
	        
	        if(spin2.getId() == R.id.destinSpinner)
	        {
	            destItem=(String)spin2.getSelectedItem();
	            Log.d("CityBusActivity", destItem);
	               
	        }
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Select city",Toast.LENGTH_SHORT).show();
	}
	
private class LongRunningGetIO extends AsyncTask <Void, Void,String> {
		
		private String service_url;	
		
		LongRunningGetIO(String url)
		{
			service_url=url;
			
		}		
		public void onPreExecute()
		{
			searchButton.setClickable(false);
			dialog.setMessage("Loading results");
			dialog.setCancelable(false);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			
		}
			
			@Override
			protected String doInBackground(Void... params) {
				// TODO Auto-generated method stub
				String text = null;
				
				HttpClient httpClient = new DefaultHttpClient();
				HttpContext localContext = new BasicHttpContext();
	            HttpGet httpGet = new HttpGet(service_url);
	            
	            try {
	                  HttpResponse response = httpClient.execute(httpGet, localContext);
	                  BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	                  StringBuilder builder = new StringBuilder();
	                  for (String line = null; (line = reader.readLine()) != null;) {
	                      builder.append(line).append("\n");
	                  }
	                  
	                  text=builder.toString();
	                  
	                  Log.d("CityBusActivity", text);
	                  return text;
	               } catch (Exception e) {
	           	  e.getLocalizedMessage();
	           }
	            
	            return text;
			}
			public void onPostExecute(String result)
			{
				if(result!=null)
				{	
					 Log.d("CityBusActivity ",result);
					 CityBusActivity.this.server_result=result;
				}
				if (dialog.isShowing()) 
				{
						dialog.dismiss();
				}
					searchButton.setClickable(true);
					 Intent intent = new Intent(CityBusActivity.this,CityBusResultActivity.class);
						Bundle bundle = new Bundle();
						bundle.putString("destination",destItem);
						bundle.putString("serverresult", server_result);
						intent.putExtras(bundle);
						startActivity(intent);
			}
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_city_bus, menu);
		return true;
	}

}
