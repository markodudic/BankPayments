package si.halcom.bankpayments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class Step3Activity extends ActionBarActivity {


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_step3);
		

		ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayShowCustomEnabled(true);
	    
		LayoutInflater inflator = getLayoutInflater();
		View v = inflator.inflate(R.layout.action_bar_icons, null);
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.RIGHT | Gravity.CENTER_VERTICAL);

		actionBar.setCustomView(v, lp);

		setHeaderStepImages();
		
	}

	
	private void setHeaderStepImages() {
		TextView textStep1 = (TextView) findViewById(R.id.textStep1);
		TextView textStep2 = (TextView) findViewById(R.id.textStep2);
		TextView textStep3 = (TextView) findViewById(R.id.textStep3);

		textStep1.setBackgroundResource(R.drawable.dr_step_disabled);
		textStep2.setBackgroundResource(R.drawable.dr_step_disabled);
		textStep3.setBackgroundResource(R.drawable.dr_step);
	}
	

}
