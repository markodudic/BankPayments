package si.halcom.bankpayments;

import java.util.Calendar;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class Step1Activity extends ActionBarActivity {
/*
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
 
    // nav drawer title
    private CharSequence mDrawerTitle;
 
    // used to store app title
    private CharSequence mTitle;
 
    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
 
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    */ 
    private TextView tvDisplayDate;
	
	private int year;
	private int month;
	private int day;
	static final int DATE_DIALOG_ID = 0;
	
	private boolean isOptionalData = false;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_step1);
		
		ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayShowCustomEnabled(true);
	    //actionBar.setDisplayHomeAsUpEnabled(true);
	    
		LayoutInflater inflator = getLayoutInflater();
		View v = inflator.inflate(R.layout.action_bar_icons, null);
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.RIGHT | Gravity.CENTER_VERTICAL);

		actionBar.setCustomView(v, lp);
		
		
		/*
		
		mTitle = mDrawerTitle = getTitle();
		 
        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
 
        // nav drawer icons from resources
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
 
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        Log.d("Step1Activity", "mDrawerList="+mDrawerList);
        
        navDrawerItems = new ArrayList<NavDrawerItem>();
 
        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Find People
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Photos
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Communities, Will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        // Pages
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // What's hot, We  will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
         
 
        // Recycle the typed array
        navMenuIcons.recycle();
 
        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
        Log.d("Step1Activity", "mDrawerList="+mDrawerList);
        Log.d("Step1Activity", "adapter="+adapter);
        mDrawerList.setAdapter(adapter);
 
        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.menu, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ){
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }
 
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
 
        if (savedInstanceState == null) {
            // on first time display view for first nav item
            //displayView(0);
        }
        
        
        
        
        
        */

		
		addListenerOnSpinnerItemSelection();
		
		setCurrentDateOnView();


		
		tvDisplayDate = (TextView) findViewById(R.id.payment_date);
		tvDisplayDate.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});

		
		final LinearLayout lOptionalData = (LinearLayout) findViewById(R.id.optional_data_layout);
		ImageView ivOptionalData = (ImageView) findViewById(R.id.optional_data_arrow);
		ivOptionalData.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isOptionalData) {
					lOptionalData.setVisibility(LinearLayout.GONE);
					ImageView ivOptionalData = (ImageView) findViewById(R.id.optional_data_arrow);
					ivOptionalData.setImageResource(R.drawable.arrow_down);
					isOptionalData = false;
				} else {
					lOptionalData.setVisibility(LinearLayout.VISIBLE);
					ImageView ivOptionalData = (ImageView) findViewById(R.id.optional_data_arrow);
					ivOptionalData.setImageResource(R.drawable.arrow_up);
					isOptionalData = true;
				}
			}
		});
		

		setHeaderStepImages();
	}

	
	
	@Override
	protected void onResume() {
		super.onResume();
		ScrollView scrollPage = (ScrollView) findViewById(R.id.scroll_page);
		scrollPage.fullScroll(View.FOCUS_UP);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.step1, menu);
        return true;
    }
 /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
        case R.id.action_settings:
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
 
   
   @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    
   
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }
 
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
 
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }   
    
	*/
	// display current date
	public void setCurrentDateOnView() {
 
		tvDisplayDate = (TextView) findViewById(R.id.payment_date);
 
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		updateDisplay();
	}
	
	private void updateDisplay() {
		// set current date into textview
		tvDisplayDate.setText(new StringBuilder()
			// Month is 0 based, just add 1
			.append(day).append(".").append(month + 1).append(".")
			.append(year).append(" "));
	}


	
	
	public void addListenerOnSpinnerItemSelection(){
	    Spinner spinner = (Spinner) findViewById(R.id.spinner_payment_types);
	    ArrayAdapter<CharSequence> mAdapter = ArrayAdapter.createFromResource(this, R.array.payment_types, R.layout.spinner_item);
	    mAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
	    spinner.setAdapter(mAdapter);

	    spinner = (Spinner) findViewById(R.id.spinner_pay_from);
	    mAdapter = ArrayAdapter.createFromResource(this, R.array.pay_from, R.layout.spinner_item);
	    mAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
	    spinner.setAdapter(mAdapter);

	    spinner = (Spinner) findViewById(R.id.spinner_pay_to_account);
	    mAdapter = ArrayAdapter.createFromResource(this, R.array.pay_to_account, R.layout.spinner_item_disabled);
	    mAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
	    spinner.setAdapter(mAdapter);
	    
	    spinner = (Spinner) findViewById(R.id.spinner_pay_to);
	    mAdapter = ArrayAdapter.createFromResource(this, R.array.pay_to, R.layout.spinner_item_error);
	    mAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
	    spinner.setAdapter(mAdapter);
	    
	    spinner = (Spinner) findViewById(R.id.spinner_payment_currency);
	    mAdapter = ArrayAdapter.createFromResource(this, R.array.payment_currency, R.layout.spinner_item);
	    mAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
	    spinner.setAdapter(mAdapter);	    
	} 	
	
	@Override
	protected Dialog onCreateDialog(int id) {
	   switch (id) {
	   case DATE_DIALOG_ID:
	      return new DatePickerDialog(this,
	                mDateSetListener,
	                year, month, day);
	   }
	   return null;
	}
	
	private DatePickerDialog.OnDateSetListener mDateSetListener  = new DatePickerDialog.OnDateSetListener() {
	
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
			int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
			
			updateDisplay();
		}
	};
	
	private void setHeaderStepImages() {
		TextView textStep1 = (TextView) findViewById(R.id.textStep1);
		TextView textStep2 = (TextView) findViewById(R.id.textStep2);
		TextView textStep3 = (TextView) findViewById(R.id.textStep3);

		textStep1.setBackgroundResource(R.drawable.dr_step);
		textStep2.setBackgroundResource(R.drawable.dr_step_disabled);
		textStep3.setBackgroundResource(R.drawable.dr_step_disabled);

	}
	

	public void showNextStep(View view) {
		Intent intent = new Intent(this, Step2Activity.class);
		startActivity(intent);
	}
	
	
}
