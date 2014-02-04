package si.halcom.bankpayments;

import java.util.ArrayList;
import java.util.Calendar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class Step3Activity extends Activity {


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_step3);
		
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
