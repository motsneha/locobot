package bot.location.locobotapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class LocoBotAppActivity extends Activity implements OnClickListener{

	private Button slideButton,b1, b2,b3,b4,b5,b6;
	private SlidingDrawer slidingDrawer;
	private AlertManager alert=new AlertManager();
	private InternetConnection cd;
	Boolean isInternetPresent = false;
	private Button aboutus;
	private Button help;
	
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_loco_bot_app);
	 
//	        cd = new InternetConnection(getApplicationContext());
	        
	        slideButton = (Button) findViewById(R.id.slideButton);
	        slidingDrawer = (SlidingDrawer) findViewById(R.id.SlidingDrawer);
	        slideButton.setBackgroundResource(R.drawable.openarrow);
	        
	        aboutus = (Button) findViewById(R.id.aboutusView1);
	        help = (Button) findViewById(R.id.helpView1);
	        
	       
	        b1 = (Button) findViewById(R.id.Button01);
	        b2 = (Button) findViewById(R.id.Button02);
	        b3 = (Button) findViewById(R.id.Button03);
	        b4 = (Button) findViewById(R.id.Button04);
	        b5 = (Button) findViewById(R.id.Button05);
	        b6 = (Button) findViewById(R.id.Button06);
	        
	        aboutus.setOnClickListener(this);
	        help.setOnClickListener(this);
	        
	        b1.setOnClickListener(this);
	        b2.setOnClickListener(this);
	        b3.setOnClickListener(this);
	        b4.setOnClickListener(this);
	        b5.setOnClickListener(this);
	        b6.setOnClickListener(this);
	   	 
	 
	        slidingDrawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {
	            @Override
	            public void onDrawerOpened() {
	                slideButton.setBackgroundResource(R.drawable.closearrow1);
	            
	            }
	        });
	 
	        slidingDrawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {
	            @Override
	            public void onDrawerClosed() {
	                slideButton.setBackgroundResource(R.drawable.openarrow);
	            }
	        });

	    }

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch(view.getId())
			{
				case R.id.Button01:
					Intent intent01=new Intent(this,GeoActivity.class);
					startActivity(intent01);
					break;
				case R.id.Button02:
					Intent intent02=new Intent(this,CityBusActivity.class);
					startActivity(intent02);
					break;
				case R.id.Button03:
					Intent intent03=new Intent(this,StBusActivity.class);
					startActivity(intent03);
					break;
				case R.id.Button04:
					Intent intent04=new Intent(this,SMSActivity.class);
					startActivity(intent04);
					break;
				case R.id.Button05:
					Intent intent05=new Intent(this,AlertmeActivity.class);
					startActivity(intent05);
					break;
				case R.id.Button06: 
					finish();
					break;
				case R.id.aboutusView1:
					Intent intent06 = new Intent(this,AboutUs.class);
					startActivity(intent06);
					break;
				case R.id.helpView1:
					Intent intent07 = new Intent(this,Help.class);
					startActivity(intent07);
					break;
				
			}
		}
}
