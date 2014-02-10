package si.halcom.bankpayments;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class Step3Activity extends ActionBarActivity {

	private Button menuButton1;
	private Button menuButton2;
	private Button menuButton3;
	private Button menuButton4;
	private boolean isSystemData = false;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_step3);
		

		ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayShowCustomEnabled(true);
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    actionBar.setDisplayShowHomeEnabled(false);
	    actionBar.setTitle(R.string.header_title);
	    
		LayoutInflater inflator = getLayoutInflater();
		View v = inflator.inflate(R.layout.action_bar_icons, null);
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.RIGHT | Gravity.CENTER_VERTICAL);

		actionBar.setCustomView(v, lp);

		setHeaderStepImages();
		
		menuButton1 = (Button) findViewById(R.id.menu_button_1);
		menuButton2 = (Button) findViewById(R.id.menu_button_2);
		menuButton3 = (Button) findViewById(R.id.menu_button_3);
		menuButton4 = (Button) findViewById(R.id.menu_button_4);
		menuButton1.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	setButtonCliecked(menuButton1);
		    }
		});		
		menuButton2.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	setButtonCliecked(menuButton2);
		    }
		});		
		menuButton3.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	setButtonCliecked(menuButton3);
		    }
		});		
		menuButton4.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	setButtonCliecked(menuButton4);
		    }
		});	
		
		final LinearLayout lSystemData = (LinearLayout) findViewById(R.id.system_data_layout);
		ImageView ivSystemData = (ImageView) findViewById(R.id.optional_data_arrow);
		ivSystemData.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isSystemData) {
					lSystemData.setVisibility(LinearLayout.GONE);
					ImageView ivOptionalData = (ImageView) findViewById(R.id.optional_data_arrow);
					ivOptionalData.setImageResource(R.drawable.arrow_down);
					isSystemData = false;
				} else {
					lSystemData.setVisibility(LinearLayout.VISIBLE);
					ImageView ivOptionalData = (ImageView) findViewById(R.id.optional_data_arrow);
					ivOptionalData.setImageResource(R.drawable.arrow_up);
					isSystemData = true;
				}
			}
		});			
		
	}

	
	private void setHeaderStepImages() {
		TextView textStep1 = (TextView) findViewById(R.id.textStep1);
		TextView textStep2 = (TextView) findViewById(R.id.textStep2);
		TextView textStep3 = (TextView) findViewById(R.id.textStep3);

		textStep1.setBackgroundResource(R.drawable.dr_step_disabled);
		textStep2.setBackgroundResource(R.drawable.dr_step_disabled);
		textStep3.setBackgroundResource(R.drawable.dr_step);
	}
	
	public void setButtonCliecked(Button menuButton){
		menuButton1.setBackgroundResource(0);
		menuButton2.setBackgroundResource(0);
		menuButton3.setBackgroundResource(0);
		menuButton4.setBackgroundResource(0);
		menuButton.setBackgroundResource(R.drawable.menu_button_clicked);
	}
}
