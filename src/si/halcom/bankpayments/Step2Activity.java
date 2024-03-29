package si.halcom.bankpayments;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class Step2Activity extends ActionBarActivity {

	private Dialog dialogDiscard;
	private Dialog dialogPassword;
	final Context context = this;
	private boolean isSystemData = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_step2);
		
		ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayShowCustomEnabled(true);
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    actionBar.setTitle(R.string.header_title);
	    
		LayoutInflater inflator = getLayoutInflater();
		View v = inflator.inflate(R.layout.action_bar_icons, null);
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.RIGHT | Gravity.CENTER_VERTICAL);

		actionBar.setCustomView(v, lp);

		setHeaderStepImages();
		
		// dialogDiscard
		dialogDiscard = new Dialog(this,R.style.Dialog);
		dialogDiscard.setContentView(R.layout.dialog_discard); 
		//dialogDiscard.setTitle(R.string.discard_title);
		dialogDiscard.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    
	    
		Button dialogButtonYes = (Button) dialogDiscard.findViewById(R.id.button_yes);
		dialogButtonYes.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogDiscard.dismiss();
				finish();
			}
		});
		 
		Button dialogButtonNo = (Button) dialogDiscard.findViewById(R.id.button_no);
		dialogButtonNo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogDiscard.dismiss();
			}
		});
		
		// dialogPassword
		dialogPassword = new Dialog(this,R.style.Dialog);
		dialogPassword.setContentView(R.layout.dialog_password); 
		//dialogPassword.setTitle(R.string.password_title);
		dialogPassword.getWindow().setBackgroundDrawable(new ColorDrawable(0));

		Button dialogPasswordButtonOk = (Button) dialogPassword.findViewById(R.id.button_ok);
		dialogPasswordButtonOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogPassword.dismiss();
				Intent intent = new Intent(context, Step3Activity.class);
				startActivity(intent);
			}
		});
		 
		Button dialogPasswordButtonCancel = (Button) dialogPassword.findViewById(R.id.button_cancel);
		dialogPasswordButtonCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogPassword.dismiss();
			}
		});	
		
		final LinearLayout lSystemData = (LinearLayout) findViewById(R.id.system_data_layout);
		LinearLayout ivSystemData = (LinearLayout) findViewById(R.id.system_data);
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
					final ScrollView scrollPage = (ScrollView) findViewById(R.id.scroll_page_step2);
					//scrollPage.fullScroll(View.FOCUS_DOWN);
					scrollPage.post(new Runnable() {
				        @Override
				        public void run() {
				        	scrollPage.fullScroll(ScrollView.FOCUS_DOWN);
				        }
				    });
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
		textStep2.setBackgroundResource(R.drawable.dr_step);
		textStep3.setBackgroundResource(R.drawable.dr_step_disabled);
	}
	
	public void showEditDialog(View view) {
		dialogDiscard.show();
	}
	
	public void showPayDialog(View view) {
		dialogPassword.show();
	}	
	
	@Override
	public Intent getSupportParentActivityIntent() {
		dialogDiscard.show();
		return null;
	}
}
