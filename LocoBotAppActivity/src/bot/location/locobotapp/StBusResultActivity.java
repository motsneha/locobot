package bot.location.locobotapp;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class StBusResultActivity extends Activity {
	private String sourceItem;
	private String destItem;
	private TextView results,src,dest;
	private String server_result;
	private String temp_result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_st_bus_result);
		
		Intent intent=this.getIntent();
		Bundle bundle= intent.getExtras();
		sourceItem=bundle.getString("src");
		destItem=bundle.getString("dest");
		server_result=bundle.getString("result");
		
		
		Log.d("StBusResultActivity ",sourceItem);
		Log.d("StBusResultActivity ",destItem);
		Log.d("StBusResultActivity ",server_result);
		
		try {
			JSONObject object = new JSONObject(server_result); 
			JSONArray jArray = object.getJSONArray("schedule");
			for (int i=0; i < jArray.length(); i++)
			{
			    JSONObject oneObject = jArray.getJSONObject(i);
			    temp_result=oneObject.getString("time");
				
			}
		}catch (Exception e) {
			Log.e("CityBusActivity", e.getLocalizedMessage(), e);
			temp_result="Unable to retrieve result from server";
		}
		src=(TextView)findViewById(R.id.sourceView9);
        src.setText(sourceItem);
        dest=(TextView)findViewById(R.id.destView11);
        dest.setText(destItem);
        results=(TextView)findViewById(R.id.rsltView12);
        results.setText(temp_result);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_st_bus_result, menu);
		return true;
	}

}
