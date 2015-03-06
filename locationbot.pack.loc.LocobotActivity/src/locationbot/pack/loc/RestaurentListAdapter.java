package locationbot.pack.loc;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RestaurentListAdapter extends BaseAdapter{
	private ArrayList<RestaurentAddress> restaurents = new ArrayList<RestaurentAddress>();
	public RestaurentListAdapter()
	{
		restaurents.add(new RestaurentAddress("Kiran", "Rajkamal"));
		restaurents.add(new RestaurentAddress("Rajwada", "Khatri Plaza,Badnera Road"));
		restaurents.add(new RestaurentAddress("Neelam", "Shivaji Market,Near Biyani Science College,Ravinagar"));
	}
	
	
	
	
	
	public int getCount() {
		// TODO Auto-generated method stub
		return restaurents.size();
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
					  R.layout.restaurentadapter_view_layout, parent, false);
		}
		RestaurentAddress restAdd = restaurents.get(index);
		
		TextView restNameTextView = (TextView)
				   view.findViewById(R.id.restNameView);
		restNameTextView.setText(restAdd.getRestaurentName());
		
		TextView restAddressTextView = (TextView) 
				  view.findViewById(R.id.restAddressView);
		restAddressTextView.setText(restAdd.getRestaurentAddress());
			
		return view;
	}
	public ArrayList<RestaurentAddress> getRestaurentList()
	{
		return restaurents;
	}
	
}
