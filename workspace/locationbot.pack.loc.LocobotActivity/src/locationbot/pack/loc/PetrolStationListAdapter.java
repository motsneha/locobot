package locationbot.pack.loc;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PetrolStationListAdapter extends BaseAdapter {

	private ArrayList<PetrolStationAddress> petrols = new ArrayList<PetrolStationAddress>();
	
	public PetrolStationListAdapter()
	{
		petrols.add(new PetrolStationAddress("Indian Oil", "Rajkamal"));
		petrols.add(new PetrolStationAddress("Bharat Petrolium", "Khatri Plaza,Badnera Road"));
		petrols.add(new PetrolStationAddress("Reliance", "Shivaji Market,Near Biyani Science College,Ravinagar"));
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		return petrols.size();
	}

	public Object getItem(int index ) {
		// TODO Auto-generated method stub
		return getItem(index);
	}

	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		convertView = inflater.inflate(R.layout.petrolstationadapter_view_layout, parent, false);
		}
		PetrolStationAddress petrolAdd = petrols.get(position);
		TextView petrolStationNameTextView = (TextView)
				   convertView.findViewById(R.id.petrolStationNameView);
		petrolStationNameTextView.setText(petrolAdd.getPetrolStationName());
		
		TextView petrolStationAddressTextView = (TextView) 
				  convertView.findViewById(R.id.petrolStationAddressView);
		petrolStationAddressTextView.setText(petrolAdd.getPetrolStationAddress());
		return convertView;
	}

	public ArrayList<PetrolStationAddress> getPetrolList(){
	return petrols;
	}
}
