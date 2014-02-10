package si.halcom.bankpayments;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

 
public class Step1Activity extends ActionBarActivity {

    private TextView tvDisplayDate;
	
	private int year;
	private int month;
	private int day;
	static final int DATE_DIALOG_ID = 0;
	protected ListFragment mFrag;

	private boolean isOptionalData = false;
	
	private Button bPaymentTypes;
	private Button bPayTo;
	private Button bPayFrom;
	private Button bPayToAccount;
	private Button bPaymentCurrency;
 
	private SlidingMenu slidingMenu;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_step1);
		
		ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayShowCustomEnabled(true);
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    actionBar.setTitle(R.string.header_title);
	     
		LayoutInflater inflator = getLayoutInflater();
		View v = inflator.inflate(R.layout.action_bar_icons, null);
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.RIGHT | Gravity.CENTER_VERTICAL);

		actionBar.setCustomView(v, lp);
		
		
		//addSpinners();
		
		setCurrentDateOnView();
		
		
		slidingMenu = new SlidingMenu(this);
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //menu.setShadowWidthRes(R.dimen.shadow_width);
        //menu.setShadowDrawable(R.drawable.shadow);
        //menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //menu.setFadeDegree(0.35f);
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW );
        //menu.setMenu(R.layout.menu);
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		slidingMenu.setMenu(R.layout.menu_frame);
		slidingMenu.setSlidingEnabled(true);
		
		FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
		mFrag = new MenuListFragment();
		t.replace(R.id.menu_frame, mFrag);
		t.commit();
		
		
		bPaymentTypes = (Button) findViewById(R.id.button_payment_types);
		bPaymentTypes.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
				Bundle bundle=new Bundle();
				bundle.putInt("arrayId", R.array.payment_types);
				Intent intent = new Intent(Step1Activity.this, ListActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
		    }
		});		
		
		bPayTo = (Button) findViewById(R.id.button_pay_to);
		bPayTo.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
				Bundle bundle=new Bundle();
				bundle.putInt("arrayId", R.array.pay_to);
				Intent intent = new Intent(Step1Activity.this, ListActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
		    }
		});	
		 
		bPayFrom = (Button) findViewById(R.id.button_pay_from);
		bPayFrom.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
				Bundle bundle=new Bundle();
				bundle.putInt("arrayId", R.array.pay_from);
				Intent intent = new Intent(Step1Activity.this, ListActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
		    }
		});
		
		/*bPayToAccount = (Button) findViewById(R.id.button_pay_to_account);
		bPayToAccount.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
				Bundle bundle=new Bundle();
				bundle.putInt("arrayId", R.array.pay_to_account);
				Intent intent = new Intent(Step1Activity.this, ListActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
		    }
		});	*/
		
		bPaymentCurrency = (Button) findViewById(R.id.button_payment_currency);
		bPaymentCurrency.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
				Bundle bundle=new Bundle();
				bundle.putInt("arrayId", R.array.payment_currency);
				Intent intent = new Intent(Step1Activity.this, ListActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
		    }
		});			
		
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
		/*Intent intent = getIntent();
		String value = intent.getStringExtra("value");
		bPaymentTypes.setText(value);
		*/
		ScrollView scrollPage = (ScrollView) findViewById(R.id.scroll_page);
		scrollPage.fullScroll(View.FOCUS_UP);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.step1, menu);
        return true;
    }

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


	/*
	
	public void addSpinners(){
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
	} 	*/
	
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
	
	
	@Override
	public Intent getSupportParentActivityIntent() {
		slidingMenuToggle();
		return null;
	}	
	
	

	public void slidingMenuToggle() {
		slidingMenu.toggle();
    }
		
}
