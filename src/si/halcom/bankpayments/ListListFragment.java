package si.halcom.bankpayments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListListFragment extends ListFragment {

	private String[] listItems;
	
	private int arrayId;

    
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		arrayId=getArguments().getInt("arrayId");
		return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		
		SampleAdapter adapter = new SampleAdapter(getActivity());
		
		listItems = getResources().getStringArray(arrayId);
		
		for (int i = 0; i < listItems.length; i++) {
			adapter.add(new SampleItem(listItems[i]));
		} 
		setListAdapter(adapter);
	}

	private class SampleItem {
		public String tag;
		public SampleItem(String tag) {
			this.tag = tag; 
		}
	}


	
	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (position == 0) {
				if (convertView == null) {
					convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_list_header, null);
				}
				TextView title = (TextView) convertView.findViewById(R.id.row_title);
				title.setText(getItem(position).tag);			
				//unclicable
				convertView.setOnClickListener(new OnClickListener() {
		            @Override
		            public void onClick(View v) {
		            }
		        });
			} else {
				if (convertView == null) {
					convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_list, null);
				}
				TextView title = (TextView) convertView.findViewById(R.id.row_title);
				if (title != null) title.setText(getItem(position).tag);
				convertView.setOnClickListener(new OnClickListener() {
		            @Override
		            public void onClick(View v) {
		            	v.setBackgroundColor(getResources().getColor(R.color.list_selected));
		            	//Bundle bundle=new Bundle();
						//bundle.putString("value", ((TextView) v).getText().toString());
						Intent intent = new Intent(getActivity().getBaseContext(), Step1Activity.class);
						//intent.putExtras(bundle);
		        		startActivity(intent);
		            }
		        });
			}
			return convertView;
		}

	}
}
