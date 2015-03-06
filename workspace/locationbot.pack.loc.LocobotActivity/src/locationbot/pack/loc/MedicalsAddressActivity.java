package locationbot.pack.loc;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MedicalsAddressActivity extends Activity {

	private String name;
	private String address;
	private ArrayList<MedicalsAddress> medicalArray = new ArrayList<MedicalsAddress>();
	
	
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.medicalslist_layout);
	
	ListView listView = (ListView)findViewById(R.id.medicals_list);
	MedicalsListAdapter medicalListAdapter = new MedicalsListAdapter(); 
	listView.setAdapter(medicalListAdapter);
	
	medicalArray=medicalListAdapter.getMedicalsList();
	
	listView.setOnItemClickListener( new OnItemClickListener()
	{

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			MedicalsAddress medicalAddress=medicalArray.get(position);
			
			Bundle bundle=new Bundle();
			name=medicalAddress.getMedicals_name(); 
			address=medicalAddress.getMedicals_address();
			bundle.putString("param1", name);
			bundle.putString("param2", address);
			Intent intent = new Intent(view.getContext(),BigItemView.class);
  	    	
			intent.putExtras(bundle);
			startActivityForResult(intent,0);
			
		}});
	
	}
	
}
