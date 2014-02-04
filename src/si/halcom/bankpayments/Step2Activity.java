package si.halcom.bankpayments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class Step2Activity extends Activity {

	private Dialog dialogDiscard;
	private Dialog dialogPassword;
	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_step2);
		
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
		dialogPassword.setTitle(R.string.password_title);

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
							
		
	}

	
	private void setHeaderStepImages() {
		ImageView imageStep1 = (ImageView) findViewById(R.id.imageStep1);
		ImageView imageStep2 = (ImageView) findViewById(R.id.imageStep2);
		ImageView imageStep3 = (ImageView) findViewById(R.id.imageStep3);
		
		imageStep1.setImageResource(R.drawable.step1);
		imageStep2.setImageResource(R.drawable.step2_pressed);
		imageStep3.setImageResource(R.drawable.step3);
	}
	
	public void showEditStep(View view) {
		dialogDiscard.show();
	}
	
	public void showPayStep(View view) {
		dialogPassword.show();
	}	
}
