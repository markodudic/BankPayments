package si.halcom.bankpayments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;


public class ListActivity extends ActionBarActivity implements ListListFragment.OnDataPass {

	protected ListFragment lFrag;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_frame);
		
		ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayShowCustomEnabled(true);
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    actionBar.setTitle(R.string.header_title);

		
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

	
	@Override
	public void onDataPass(String data) {
	    Intent resultIntent = new Intent();
	    resultIntent.putExtra(Step1Activity.RESULT_SELECTION_IDENT, data);
	    setResult(Activity.RESULT_OK, resultIntent);
	    finish();
	}
}
