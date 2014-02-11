package si.halcom.bankpayments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListListFragment extends ListFragment {

	private String[] listItems;
	
	private int arrayId;

	OnDataPass dataPasser;
    
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

	public interface OnDataPass {
	    public void onDataPass(String data);
	}
	
	@Override
	public void onAttach(Activity a) {
	    super.onAttach(a);
	    dataPasser = (OnDataPass) a;
	}
	
	public void passData(String data) {
	    dataPasser.onDataPass(data);
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
		            	TextView tv = (TextView)((LinearLayout) v).getChildAt(0);
		            	passData(tv.getText().toString());
		            }
		        });
			}
			return convertView;
		}

	}
}
