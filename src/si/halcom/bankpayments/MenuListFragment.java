package si.halcom.bankpayments;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MenuListFragment extends ListFragment {

	private String[] menuTitles;
	private TypedArray menuIcons;
	private String[] menuType;
	
	private final String MENU_TYPE_HEADER = "HEADER";
	private final String MENU_TYPE_ITEM = "ITEM";
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		SampleAdapter adapter = new SampleAdapter(getActivity());
		
		menuTitles = getResources().getStringArray(R.array.menu_items);
		menuIcons = getResources().obtainTypedArray(R.array.menu_icons);
		menuType = getResources().getStringArray(R.array.menu_type);
		
		for (int i = 0; i < menuTitles.length; i++) {
			adapter.add(new SampleItem(menuTitles[i], menuIcons.getResourceId(i, -1), menuType[i]));
		} 
		setListAdapter(adapter);
	}

	private class SampleItem {
		public String tag;
		public int iconRes;
		public String menuType;
		public SampleItem(String tag, int iconRes, String menuType) {
			this.tag = tag; 
			this.iconRes = iconRes;
			this.menuType = menuType;
		}
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (position == 0) {
				if (convertView == null) {
					convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_top, null);
				}
			} else {
				String menuType = getItem(position-1).menuType;
				if (menuType.equals(MENU_TYPE_HEADER)) {
					if (convertView == null) {
						convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_header, null);
					}
					TextView title = (TextView) convertView.findViewById(R.id.row_title);
					title.setText(getItem(position-1).tag);			
					//unclicable
					convertView.setOnClickListener(new OnClickListener() {
			            @Override
			            public void onClick(View v) {
			            }
			        });
				} else if (menuType.equals(MENU_TYPE_ITEM)) {		
					if (convertView == null) {
						convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, null);
					}
					ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
					if (icon != null) icon.setImageResource(getItem(position-1).iconRes);
					TextView title = (TextView) convertView.findViewById(R.id.row_title);
					if (title != null) title.setText(getItem(position-1).tag);
					convertView.setOnClickListener(new OnClickListener() {
			            @Override
			            public void onClick(View v) {
			            	v.setBackgroundColor(getResources().getColor(R.color.menu_selected));
			            }
			        });
				}
			}
			return convertView;
		}

	}
}
