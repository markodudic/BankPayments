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
		ImageView imageStep1 = (ImageView) findViewById(R.id.imageStep1);
		ImageView imageStep2 = (ImageView) findViewById(R.id.imageStep2);
		ImageView imageStep3 = (ImageView) findViewById(R.id.imageStep3);
		
		imageStep1.setImageResource(R.drawable.step1);
		imageStep2.setImageResource(R.drawable.step2);
		imageStep3.setImageResource(R.drawable.step3_pressed);
	}
	

}
