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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class StBusActivity extends Activity implements OnItemSelectedListener,OnClickListener{
	private String[] sources = {"Amravati"};
	private String sourceItem;
	private String destItem;
	private static final String SERVICE_URL ="http://env-3776051.jelastic.servint.net/locobota/st";//"http://10.0.2.2:8080/com.locobot.busservice/st";//http://env-3776051.jelastic.servint.net/locobota/st/amravati/nagpur	
	private String service_url_add;
	private ProgressDialog dialog;
	private String server_result;
	private Button searchButton;
	private AlertManager alert=new AlertManager();
	private InternetConnection cd;
	Boolean isInternetPresent = false;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st_bus);
      
        searchButton=(Button)findViewById(R.id.busSearch);
        searchButton.setOnClickListener(this);
        dialog=new ProgressDialog(this);
        cd = new InternetConnection(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
		if (!isInternetPresent) {
			// Internet Connection is not present
			alert.showAlertDialog(StBusActivity.this, "Internet Connection Error",
					"Please connect to working Internet connection", false);
			// stop executing code by return
			return;
		}
        Spinner sourceSpinner = (Spinner)findViewById(R.id.sourceSpinner);
        ArrayAdapter<String> sourceAdapt = new ArrayAdapter<String>(this,R.layout.spinner_item,sources);
        //sourceAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceSpinner.setAdapter(sourceAdapt);
        sourceSpinner.setOnItemSelectedListener(this);
        
        Spinner destinSpinner = (Spinner)findViewById(R.id.destinSpinner);
        ArrayAdapter<CharSequence> destAdapt = ArrayAdapter.createFromResource(this, R.array.destinations2,R.layout.spinner_item);
        //destAdapt.setDropDownViewResource(R.layout.spinner_item);
        destinSpinner.setAdapter(destAdapt);
        destinSpinner.setOnItemSelectedListener(this);
  
        
       
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
		// TODO Auto-generated method stub
		 Spinner spin = (Spinner)parent;
	        Spinner spin2 = (Spinner)parent;
	        if(spin.getId() == R.id.sourceSpinner)
	        {
	            sourceItem=(String)spin.getSelectedItem();  
	            Log.d("StBusActivity",sourceItem);
	            
	        }
	        if(spin2.getId() == R.id.destinSpinner)
	        {
	            destItem=(String)spin2.getSelectedItem();
	            Log.d("StBusActivity", destItem);
     
	        }

	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Select city",Toast.LENGTH_SHORT).show();
	}
	public void SetResult(String result){
		this.server_result=result;
		Log.d("StBusActivity",server_result);
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
	                  
	                  Log.d("StBusActivity",text);
	                  StBusActivity.this.SetResult(text);
	                 //
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
					Log.d("StBusActivity",result);
					StBusActivity.this.SetResult(result);
					
				}
				
				if (dialog.isShowing()) {
						dialog.dismiss();
				}
					
				searchButton.setClickable(true);
			
				Intent intent =new Intent(StBusActivity.this,StBusResultActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("src",sourceItem);
				bundle.putString("dest",destItem);
				bundle.putString("result",server_result);
				intent.putExtras(bundle);
				startActivity(intent);
			
			
			
			}
}
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				if(view.getId()==R.id.busSearch)
				{
					if((sourceItem!=null)&& (destItem!=null))
					{
						service_url_add=SERVICE_URL+"/"+sourceItem+"/"+destItem;
					 Log.d("StBusActivity",service_url_add);
					(new LongRunningGetIO(service_url_add)).execute();
					}
					else
					{
						if(sourceItem==null)
						{Toast.makeText(this,"Select source",Toast.LENGTH_SHORT).show();
						}
						if(destItem==null)
						{Toast.makeText(this,"Select destination",Toast.LENGTH_SHORT).show();
						}
					}
				}
			}

}
