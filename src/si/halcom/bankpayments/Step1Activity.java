package si.halcom.bankpayments;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class Step1Activity extends Activity {

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
		addListenerOnSpinnerItemSelection();
		
		setCurrentDateOnView();
		
		tvDisplayDate = (TextView) findViewById(R.id.payment_date);
		tvDisplayDate.setOnClickListener(new View.OnClickListener() {
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
					isOptionalData = false;
				} else {
					lOptionalData.setVisibility(LinearLayout.VISIBLE);
					isOptionalData = true;
				}
			}
		});
		
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


	
	public void addListenerOnSpinnerItemSelection(){

	    Spinner spinner = (Spinner) findViewById(R.id.spinner_payment_types);
	    ArrayAdapter<CharSequence> mAdapter = ArrayAdapter.createFromResource(this, R.array.payment_types, R.layout.spinner_item);
	    mAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
	    spinner.setAdapter(mAdapter);

	    spinner = (Spinner) findViewById(R.id.spinner_pay_from);
	    mAdapter = ArrayAdapter.createFromResource(this, R.array.pay_from, R.layout.spinner_item);
	    mAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
	    spinner.setAdapter(mAdapter);

	    spinner = (Spinner) findViewById(R.id.spinner_pay_to);
	    mAdapter = ArrayAdapter.createFromResource(this, R.array.pay_to, R.layout.spinner_item);
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

}
