package locationbot.pack.loc;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class AtmActivity extends Activity{
	
	private String name;
	private String address;
	private String s1;
	private ArrayList<AtmAddress> atmArray = new ArrayList<AtmAddress>();
	
	public void onCreate(Bundle savedInstanceState) { 
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.atmlist_layout);
		  
		  Intent receivedIntent=this.getIntent(); 
	        Bundle bundle= receivedIntent.getExtras();
	         s1=bundle.getString("param1");
	
	AtmListAdapter atmListAdapterObj;
	atmListAdapterObj = new AtmListAdapter(s1);
	ListView listView = (ListView)findViewById(R.id.atm_address_list);
	 
  	listView.setAdapter(atmListAdapterObj);	 

	atmArray=atmListAdapterObj.getAtmList();
  		
  	
  	listView.setOnItemClickListener( new OnItemClickListener()
			{
		
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
		
			AtmAddress atmAddress=atmArray.get(position);
			
			Bundle bundle=new Bundle();
			name=atmAddress.getAtmName(); 
			address=atmAddress.getAtmAddress();
			bundle.putString("param1", name);
			bundle.putString("param2", address);
			Intent intent = new Intent(view.getContext(),BigItemView.class);
  	    	
			intent.putExtras(bundle);
			startActivityForResult(intent,0);
		}

			
		});
		
	
	}

}
