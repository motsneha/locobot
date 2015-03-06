package locationbot.pack.loc;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AtmListAdapter extends BaseAdapter {

	private ArrayList<AtmAddress> atms = new ArrayList<AtmAddress>();
	
	public AtmListAdapter()
	{
		//atms.add(new AtmAddress("Union Bank Of India", "Rajkamal"));
		//atms.add(new AtmAddress("State bank Of India", "Khatri Plaza,Badnera Road"));
		//atms.add(new AtmAddress("IDBI", "Shivaji Market,Near Biyani Science College,Ravinagar"));
	}
	public AtmListAdapter(String s)
	{
		atms.add(new AtmAddress(s, "Rajkamal"));
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		return atms.size();
	}

	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return getItem(index);
	}

	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	public View getView(int index, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if (view == null) {
			LayoutInflater inflater = 
					  LayoutInflater.from(parent.getContext());
			view = inflater.inflate(
					  R.layout.atmadapter_view_layout, parent, false);
		}
		AtmAddress atmAdd = atms.get(index);
		TextView atmNameTextView = (TextView)
				   view.findViewById(R.id.atmNameView);
		atmNameTextView.setText(atmAdd.getAtmName());
		
		TextView atmAddressTextView = (TextView) 
				  view.findViewById(R.id.atmAddressView);
		atmAddressTextView.setText(atmAdd.getAtmAddress());
			
		return view;
			
		}
	public ArrayList<AtmAddress> getAtmList()
	{
		return atms;
	}
	
	
	}


