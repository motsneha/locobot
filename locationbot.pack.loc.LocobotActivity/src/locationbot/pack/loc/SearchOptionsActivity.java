package locationbot.pack.loc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
//import android.content.Intent;
import android.os.Bundle;
//import android.view.View;
import android.view.View;


public class SearchOptionsActivity extends Activity{
	
	private String asyncResult;
	public void onCreate(Bundle savedInstanceState) { 
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.searchoptions_layout);
	}
	public void setParameter(String results)
	{
		asyncResult=results;
	}
	public void onAtm(View view) {
		
		new LongRunningGetIO().execute();
	
			
		Intent intent = new Intent(this,AtmActivity.class);
		Bundle bundle=new Bundle();
		bundle.putString("param1",asyncResult);
		    	
		intent.putExtras(bundle);
		startActivityForResult(intent,0);
	   
	}
	public void onPetrolStation(View view) { 
		Intent intent = new Intent(this,PetrolStationActivity.class);
		  startActivity(intent);
	}
	public void onMedical(View view) { 
		Intent intent = new Intent(this,MedicalsAddressActivity.class);
		  startActivity(intent);
	}
	public void onRestaurent(View view) { 
		Intent intent = new Intent(this,RestaurentAddressActivity.class);
		  startActivity(intent);
	}
	public void onBack(View view){
					finish();
	}
	


private class LongRunningGetIO extends AsyncTask <Void, Void, String> {
	
	protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
       InputStream in = entity.getContent();
         StringBuffer out = new StringBuffer();
         int n = 1;
         while (n>0) {
             byte[] b = new byte[4096];
             n =  in.read(b);
             if (n>0) out.append(new String(b, 0, n));
         }
         return out.toString();
    }
	
	@Override
	protected String doInBackground(Void... params) {
		 HttpClient httpClient = new DefaultHttpClient();
		 HttpContext localContext = new BasicHttpContext();
         HttpGet httpGet = new HttpGet("http://10.0.2.2:8080/com.locobot.webservice/rest/address/atm");
         String text = null;
         try {
               HttpResponse response = httpClient.execute(httpGet, localContext);
               HttpEntity entity = response.getEntity();
               text = getASCIIContentFromEntity(entity);
         } catch (Exception e) {
        	 return e.getLocalizedMessage();
         }

         
         return text;
	}	
	
	protected void onPostExecute(String results) {
		
	SearchOptionsActivity.this.setParameter(results);			
	}


}



}