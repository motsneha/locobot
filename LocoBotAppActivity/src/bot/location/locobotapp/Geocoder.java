package bot.location.locobotapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;
import android.util.Log;

public class Geocoder 
{
	private static String localityName = ""; 
	public static String Address;
    public  double GeoLattitude;
    public  double GeoLongitude;
    public  String server_result;
	


	public  static void reverseGeocode(Location loc)
	{
	
		
	    HttpURLConnection connection = null;
	    URL serverAddress = null;

	    try 
	    {
	 	        
	        serverAddress = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng="+Double.toString(loc.getLatitude())+","+Double.toString(loc.getLongitude())+"&sensor=true");       
	        connection = null;
		   	connection = (HttpURLConnection)serverAddress.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setReadTimeout(10000);
		    connection.connect();
		    
			try
			{
				InputStreamReader isr = new InputStreamReader(connection.getInputStream());
				BufferedReader reader = new BufferedReader(isr);
                StringBuilder builder = new StringBuilder();
                for (String line = null; (line = reader.readLine()) != null;)
                {
                    builder.append(line).append("\n");
                }
                localityName = builder.toString();
                Log.d("Geocoder reverse geocode server result",localityName );			
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			
	    }
	    catch (Exception ex)
	    {
	        ex.printStackTrace();
	    }
	    
	    JSONObject object;
		if(localityName!=null)
		{
			try
			{
				object = new JSONObject(localityName);
				JSONArray jArray = object.getJSONArray("results");
				for (int i=0; i < jArray.length(); i++)
				{
					JSONObject oneObject = jArray.getJSONObject(i);
					Address = oneObject.getString("formatted_address"); 
					Log.d("Geocoder reverse geocode JSON parsing",Address );
				} 
		
			}
			catch (JSONException e) 
			{
		
			e.printStackTrace();
			}
		}
		else
		{
			localityName="Failed to get location details from google";
		}
	   
	
	}
	
	public  void forwardGeocode(String locaddress)
	{
	    
		String s[];
		String url="";
		s= locaddress.split(" ");
		int i,array_size;
		array_size=s.length;
		Log.d("Geocoder",locaddress);
		for(i=0; i<array_size; i++)
		{
			
			Log.d("Geocoder Address array elements",s[i] );
		}
		switch(array_size)
		{
			case 1:
				url="http://maps.googleapis.com/maps/api/geocode/json?address="+s[0]+"&sensor=false";
				break;
			case 2:
				url="http://maps.googleapis.com/maps/api/geocode/json?address="+s[0]+s[1]+"&sensor=false";
				break;
			case 3:
				url="http://maps.googleapis.com/maps/api/geocode/json?address="+s[0]+s[1]+s[2]+"&sensor=false";
				break;
			case 4:
				url="http://maps.googleapis.com/maps/api/geocode/json?address="+s[0]+s[1]+s[2]+s[3]+"&sensor=false";
				break;
			case 5:
				url="http://maps.googleapis.com/maps/api/geocode/json?address="+s[0]+s[1]+s[2]+s[3]+s[4]+"&sensor=false";
				break;
			case 6:
				url="http://maps.googleapis.com/maps/api/geocode/json?address="+s[0]+s[1]+s[2]+s[3]+s[4]+s[5]+"&sensor=false";
				break;	
		}
	    HttpURLConnection connection = null;
	    URL serverAddress = null;
	    Log.d("Geocoder SEnding url to google",url);
	    try 
	    {	    
	        
	        serverAddress = new URL(url);	       
	        connection = null;
			connection = (HttpURLConnection)serverAddress.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setReadTimeout(10000);
		                  
			connection.connect();
		    
			try
			{
				InputStreamReader isr1 = new InputStreamReader(connection.getInputStream());
				BufferedReader reader1 = new BufferedReader(isr1);
                StringBuilder builder = new StringBuilder();
                for (String line = null; (line = reader1.readLine()) != null;)
                {
                    builder.append(line).append("\n");
                }
                server_result = builder.toString();
                Log.d("Geocoder forward geocode results",server_result);	 		
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			
	    }
	    catch (Exception ex)
	    {
	        ex.printStackTrace();
	    }
	    
	    JSONObject object;
	    if(server_result!=null)
	    {
	    	try
	    	{
			
			object = new JSONObject(server_result);
			JSONArray jArray = object.getJSONArray("results");
			for (int j=0; j < jArray.length(); j++)
			{
				JSONObject temp = jArray.optJSONObject(j);
				JSONObject loc = temp.optJSONObject("geometry").optJSONObject("location");
				GeoLattitude = loc.getDouble("lat"); //Double.valueOf(loc.getString("lat"));
				GeoLongitude = loc.getDouble("lng");// Double.valueOf(loc.getString("lng"));
				Log.d("Geocoder forward geocode JSON parsing", String.valueOf(GeoLattitude));
				Log.d("Geocoder forward geocode JSON parsing", String.valueOf(GeoLongitude));
			}
		
	    	}
	    	catch (JSONException e) 
	    	{
	    		e.printStackTrace();
	    	}
	    }
	    else
	    {
	    	GeoLattitude=0.0;
	    	GeoLongitude=0.0;
	    }
	}
	
}
