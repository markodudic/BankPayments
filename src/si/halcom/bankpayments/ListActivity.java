package si.halcom.bankpayments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;


public class ListActivity extends ActionBarActivity {

	protected ListFragment lFrag;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_frame);
		
		ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayShowCustomEnabled(true);
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    actionBar.setTitle(R.string.header_title);
	    
		/*LayoutInflater inflator = getLayoutInflater();
		View v = inflator.inflate(R.layout.action_bar_icons, null);
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.RIGHT | Gravity.CENTER_VERTICAL);

		actionBar.setCustomView(v, lp);
		 */
		
		Intent intent = getIntent();
		int arrayId = intent.getIntExtra("arrayId", 0);
		
		FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
		Bundle bundle=new Bundle();
		bundle.putInt("arrayId", arrayId);
		lFrag = new ListListFragment();
		lFrag.setArguments(bundle);
		t.replace(R.id.list_frame, lFrag);
		t.commit();
		
		
	}

	
	
	@Override
	protected void onResume() {
		super.onResume();

	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.step1, menu);
        return true;
    }

	
}
