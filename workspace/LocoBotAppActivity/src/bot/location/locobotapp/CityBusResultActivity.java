package bot.location.locobotapp;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class CityBusResultActivity extends Activity {
	private String[] names={" "," "," "," "};
	private String timings[]={" "," "," "," "};
	private TextView r1t1,r1t2;
	private TextView r2t1,r2t2;
	private TextView r3t1,r3t2;
	private TextView r4t1,r4t2;
	private String server_result;
	private TextView text;
	private String destItem;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_bus_result);
		
		Intent intent=this.getIntent();
		Bundle bundle= intent.getExtras();
		destItem=bundle.getString("destination");
		server_result=bundle.getString("serverresult");
		
		Log.d("CityBusResultActivity", destItem);
		Log.d("CityBusResultActivity", server_result);
			
		try {
			JSONObject object = new JSONObject(server_result); 
			JSONArray jArray = object.getJSONArray("schedule");
			for (int i=0; i < jArray.length(); i++)
			{
			    JSONObject oneObject = jArray.getJSONObject(i);
			    names[i]=oneObject.getString("name");
				timings[i] = oneObject.getString("time");
			}
			text=(TextView)findViewById(R.id.sourceView7);
            text.setText(destItem);
            
            r1t1=(TextView)findViewById(R.id.row1text1);
			r1t1.setText(names[0]);
			r1t2=(TextView)findViewById(R.id.row1text2);
			r1t2.setText(timings[0]);

			r2t1=(TextView)findViewById(R.id.row2text1);
			r2t1.setText(names[1]);
			r2t2=(TextView)findViewById(R.id.row2text2);
			r2t2.setText(timings[1]);

			r3t1=(TextView)findViewById(R.id.row3text1);
			r3t1.setText(names[2]);
			r3t2=(TextView)findViewById(R.id.row3text2);
			r3t2.setText(timings[2]);

			r4t1=(TextView)findViewById(R.id.row4text1);
			r4t1.setText(names[3]);
			r4t2=(TextView)findViewById(R.id.row4text2);
			r4t2.setText(timings[3]);
		} catch (Exception e) {
			Log.e("CityBusActivity", e.getLocalizedMessage(), e);
			text=(TextView)findViewById(R.id.sourceView7);
			text.setText(destItem);

			r1t1=(TextView)findViewById(R.id.row1text1);
			r1t1.setText("Unable to retrieve results from server");
			r1t2=(TextView)findViewById(R.id.row1text2);
			r1t2.setText("Unable to retrieve results from server");

		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_city_bus_result, menu);
		return true;
	}

}
