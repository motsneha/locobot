package locationbot.pack.loc;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class RestaurentAddressActivity extends Activity{
	
	private String name;
	private String address;
	private ArrayList<RestaurentAddress> restArray = new ArrayList<RestaurentAddress>();
	
	
	public void onCreate(Bundle savedInstanceState) { 
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.restaurentlist_layout);

		  RestaurentListAdapter restListAdapterObj;
			ListView listView = (ListView)findViewById(R.id.restaurent_address_list);
			restListAdapterObj = new RestaurentListAdapter(); 
		  	listView.setAdapter(restListAdapterObj);	 

			restArray=restListAdapterObj.getRestaurentList();
			listView.setOnItemClickListener( new OnItemClickListener()
			{
		
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
		
			RestaurentAddress restAddress=restArray.get(position);
			
			Bundle bundle=new Bundle();
			name=restAddress.getRestaurentName(); 
			address=restAddress.getRestaurentAddress();
			bundle.putString("param1", name);
			bundle.putString("param2", address);
			Intent intent = new Intent(view.getContext(),BigItemView.class);
  	    	
			intent.putExtras(bundle);
			startActivityForResult(intent,0);
		}

			
		});
		
	
	
	}
}
