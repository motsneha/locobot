package bot.location.locobotapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

 public class GeoActivity extends Activity implements OnClickListener {
	
	private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
	private Button b11,b12,b13,b14,b15,b16,b17,b18,b19,b20;
	private Button b21,b22,b23,b24,b25,b26,b27,b28,b29;
	@SuppressWarnings("unused")
	private double latt,longt;
	private String parameter;
	 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo);
        
   //     Intent intent = this.getIntent();
    //    Bundle bundle = intent.getExtras();
     //   latt=bundle.getDouble("lattitude");
     //   longt=bundle.getDouble("longitude");
        
       b1= (Button) findViewById(R.id.airport);
       b2= (Button) findViewById(R.id.atm);
       b3= (Button) findViewById(R.id.bank);
       b4= (Button) findViewById(R.id.busstation);
       b5= (Button) findViewById(R.id.cafe);
       b6= (Button) findViewById(R.id.convenience_store); 
       b7= (Button) findViewById(R.id.doctor);
       b8= (Button) findViewById(R.id.electronics_store);
       b9= (Button) findViewById(R.id.fire_station);
       b10= (Button) findViewById(R.id.gas_station);
       b11= (Button) findViewById(R.id.grocery_or_supermarket);
       b12= (Button) findViewById(R.id.hindu_temple);
       b13= (Button) findViewById(R.id.hospital);
       b14= (Button) findViewById(R.id.laundry);
       b15= (Button) findViewById(R.id.library);
       b16= (Button) findViewById(R.id.local_government_office);
       b17= (Button) findViewById(R.id.lodging);
       b18= (Button) findViewById(R.id.mosque);
       b19= (Button) findViewById(R.id.movie_theater);
       b20= (Button) findViewById(R.id.pharmacy);       
       b21= (Button) findViewById(R.id.police);
       b22= (Button) findViewById(R.id.post_office);
       b23= (Button) findViewById(R.id.restaurant);
       b24= (Button) findViewById(R.id.school);
       b25= (Button) findViewById(R.id.shopping_mall);
       b26= (Button) findViewById(R.id.taxi_stand);
       b27= (Button) findViewById(R.id.train_station);
       b28= (Button) findViewById(R.id.travel_agency);
       b29= (Button) findViewById(R.id.university);
       
       b1.setOnClickListener(this);	b2.setOnClickListener(this);
       b3.setOnClickListener(this);	b4.setOnClickListener(this);
       b5.setOnClickListener(this);	b6.setOnClickListener(this);
       b7.setOnClickListener(this);	b8.setOnClickListener(this);
       b9.setOnClickListener(this);	b10.setOnClickListener(this);
       b11.setOnClickListener(this);	b12.setOnClickListener(this);
       b13.setOnClickListener(this);	b14.setOnClickListener(this);
       b15.setOnClickListener(this);	b16.setOnClickListener(this);
       b17.setOnClickListener(this);	b18.setOnClickListener(this);
       b19.setOnClickListener(this);	b20.setOnClickListener(this);
       b21.setOnClickListener(this);	b22.setOnClickListener(this);
       b23.setOnClickListener(this);	b24.setOnClickListener(this);
       b25.setOnClickListener(this);	b26.setOnClickListener(this);
       b27.setOnClickListener(this);	b28.setOnClickListener(this);
       b29.setOnClickListener(this);	
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_geo, menu);
        return true;
    }

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		
	switch(view.getId())
	{
		case R.id.airport:
			parameter="airport";
			Log.d("GeoActivity", parameter);
			break;
		case R.id.atm:
			parameter="atm";
			break;
		case R.id.bank:
			parameter="bank";
			break;
		case R.id.busstation:
			parameter="bus_station";
			break;
		case R.id.cafe:
			parameter="cafe";
			break;
		case R.id.convenience_store:
			parameter="convenience_store";
			break;
		case R.id.doctor:
			parameter="doctor";
			break;
		case R.id.electronics_store:
			parameter="electronics_store";
			break;
		case R.id.fire_station:
			parameter="fire_station";
			break;
		case R.id.gas_station:
			parameter="gas_station";
			break;
		case R.id.grocery_or_supermarket:
			parameter="grocery_or_supermarket";
			break;
		case R.id.hospital:
			parameter="hospital";
			break;
		case R.id.laundry:
			parameter="laundry";
			break;
		case R.id.library:
			parameter="library";
			break;
		case R.id.local_government_office:
			parameter="local_government_office";
			break;
		case R.id.mosque:
			parameter="mosque";
			break;
		case R.id.movie_theater:
			parameter="movie_theater";
			break;
		case R.id.pharmacy:
			parameter="pharmacy";
			break;
		case R.id.police:
			parameter="police";
			break;
		case R.id.post_office:
			parameter="post_office";
			break;
		case R.id.university:
			parameter="university";
			
		}
		Intent intent = new Intent(this,GoogleApi.class);
	Bundle bundle = new Bundle();
		//bundle.putDouble("lattitude",latt);
	//	bundle.putDouble("longitude",longt);
		bundle.putString("type",parameter);
		Log.d("GeoActivity", parameter);
		intent.putExtras(bundle);
		startActivity(intent);
	
	}
	
}
