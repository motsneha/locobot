package locationbot.pack.loc;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MedicalsListAdapter extends BaseAdapter {
	private ArrayList<MedicalsAddress> medicals = new ArrayList<MedicalsAddress>();
	
	
	public MedicalsListAdapter()
	{
	medicals.add(new MedicalsAddress("Archana", "Rajkamal"));
	medicals.add(new MedicalsAddress("Satguru", "Khatri Plaza,Badnera Road"));
	medicals.add(new MedicalsAddress("Kiran", "Shivaji Market,Near Biyani Science College,Ravinagar"));
	}
	
	
	
	public int getCount() {
		// TODO Auto-generated method stub
		return medicals.size();
	}

	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return getItem(index);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater inflater = 
					  LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(
					  R.layout.medicalsadapter_view_layout, parent, false);
		
		}
		MedicalsAddress med_add = medicals.get(position);		
		TextView medicalNameTextView = (TextView)
				   convertView.findViewById(R.id.medicalsNameView);
		
		medicalNameTextView.setText(med_add.getMedicals_name());
		
		TextView medicalAddressTextView = (TextView) 
				  convertView.findViewById(R.id.medicalsAddressView);
		medicalAddressTextView.setText(med_add.getMedicals_address());
		
			
		return convertView;
	}
	public ArrayList<MedicalsAddress> getMedicalsList(){
		return medicals;
	}

}
