package locationbot.pack.loc;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class PetrolStationActivity extends Activity {

	private String name;
	private String address;
	private ArrayList<PetrolStationAddress> petrolArray = new ArrayList<PetrolStationAddress>();
	
	public void onCreate(Bundle savedInstanceState) { 
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.petrolstationlist_layout);
	
	PetrolStationListAdapter petrolStationListAdapterObj;
	ListView listView = (ListView)findViewById(R.id.petrolstation_address_list);
	petrolStationListAdapterObj = new PetrolStationListAdapter(); 
  	listView.setAdapter(petrolStationListAdapterObj);	 
	
  	petrolArray=petrolStationListAdapterObj.getPetrolList();
	
	
  	listView.setOnItemClickListener( new OnItemClickListener()
  	{

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			PetrolStationAddress petrolAddress=petrolArray.get(position);
			
			Bundle bundle=new Bundle();
			name=petrolAddress.getPetrolStationName(); 
			address=petrolAddress.getPetrolStationAddress();
			bundle.putString("param1", name);
			bundle.putString("param2", address);
			Intent intent = new Intent(view.getContext(),BigItemView.class);
  	    	
			intent.putExtras(bundle);
			startActivityForResult(intent,0);
		}
  		
  	});
	
	
	
	}
}
